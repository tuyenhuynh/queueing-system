/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem;

import com.tuyenhm.queuingsystem.data.InputData;
import com.tuyenhm.queuingsystem.data.QueueData;
import com.tuyenhm.queuingsystem.data.RequestData;
import com.tuyenhm.queuingsystem.data.ServeTimeData;
import com.tuyenhm.queuingsystem.event.RequestDataChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javafx.util.Pair;
import org.uncommons.maths.random.ExponentialGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

/**
 *
 * @author tuyenhuynh
 */
public class Model {
    private InputData inputData ; 
    private QueueData queueData = new QueueData(); 
    private RequestData requestData = new RequestData() ; 
    private ServeTimeData serveTimeData  = new ServeTimeData(); 
    private RequestDataChangeListener listener ; 
    private int requestCount; 
    private int acceptedCount ; 
    private int abortedCount;
    private double[] arrivalTime;
    private double[] abortedTime; 
    private double[] acceptedTime; 
    private double[] interArrival;
    private double[] serveTime; 
    
    private double[] queueChangeTime; 
    private int[] queueAt; 
    private int queueSize; 
    
    private int firstArrivalDisplayedOrder;
    private int firstAbortedDisplayedOrder;
    private int firstAcceptedDisplayedOrder; 
    
    private double leftTime; 
    private double rightTime ; 
    
    private List<Server> servers = new ArrayList<Server>(); 
    
    private static final Random random  = new Random(); 
    
    public void analyzeInput(){
        requestCount = inputData.getRequestCount(); 
        
        leftTime = inputData.getLeftTime(); 
        rightTime = inputData.getRightTime(); 
        
        int queueLength = inputData.getQueueLength();  
        arrivalTime = new double[requestCount]; 
        interArrival = new double[requestCount]; 
        serveTime = new double[requestCount];
        
        double sourceRate = inputData.getSourceIntensive(); 
        double serveRate = inputData.getServingIntensive(); 
        Random rd = new MersenneTwisterRNG(); 
        ExponentialGenerator gen = new ExponentialGenerator(sourceRate, rd); 
        ExponentialGenerator serveTimeGen = new ExponentialGenerator(serveRate, rd); 
        
        for(int i = 0 ; i < requestCount; ++i) {
            interArrival [i] = Math.round(gen.nextValue()); 
            if( i != 0 && interArrival[i] == interArrival[i-1]){
                interArrival[i] += 1; 
            }
            serveTime[i] = Math.round(serveTimeGen.nextValue()); 
        }
        
        arrivalTime[0] =0; 
        for(int i = 1 ; i < requestCount ; ++i) {
            arrivalTime[i] = arrivalTime[i-1] + interArrival[i]; 
        }
        
        //create server
        int serverCount = inputData.getChannel(); 
        servers.clear();
        for (int i = 0 ; i < serverCount ; ++i) {
            Server server = new Server(); 
            servers.add(server); 
        }
        
        abortedCount = 0 ; 
        abortedTime = new double[requestCount];
        //queue to store serve time of each request
        Queue<Double> queue= new LinkedList<>(); 
        
        queueSize = 0 ;   
        queueAt = new int[requestCount];
        queueChangeTime = new double[requestCount]; 
        
        acceptedCount = 0; 
        acceptedTime = new double[requestCount]; 
        
        //process request
        for(int i = 0 ; i  < requestCount ; ++i ){
            //run request in queue first ( to make all server busy if they should be)
            boolean f = false ; 
            int size = queue.size(); 
            for(int j = 0 ;!f && j < size ; ++j) {
                Server s = getEarliestFreeServer(servers); 
                double earliestTime = s.getFinishTimeOfLastJob(); 
                if(earliestTime < arrivalTime[i]){
                    Double duration = queue.remove(); 
                    s.assignJob(new Pair<Double, Double>(earliestTime, earliestTime + duration));
                    //TODO: change accepted request;
                    acceptedTime[acceptedCount] = earliestTime + duration; 
                    acceptedCount ++; 
                    
                    //decrease number of item in queue
                    queueAt[queueSize] = queue.size(); 
                    queueChangeTime[queueSize] = earliestTime;
                    queueSize++; 
                }else {
                    f = true;
                    //stop here, there may be some request in queue
                }
            }
            //find free server
            Server s = getEarliestFreeServer(servers); 
            double earliestTime = s.getFinishTimeOfLastJob(); 
            if(earliestTime <= arrivalTime[i]){
                s.assignJob(new Pair<Double, Double>(arrivalTime[i], serveTime[i]));
                acceptedTime[acceptedCount] = arrivalTime[i] + serveTime[i]; 
                acceptedCount ++; 
            }else {
                if(queue.size() < queueLength) {
                    queue.add(serveTime[i]); 
                    queueAt[queueSize] = queue.size(); 
                    queueChangeTime[queueSize] = arrivalTime[i]; 
                    queueSize++; 
                }else { 
                    abortedTime[abortedCount] = arrivalTime[i]; 
                    abortedCount++;
                    System.out.println("Queue size: " + queue.size()); 
                }
            }
            //sort(acceptedTime, acceptedCount); 
        }
        acceptedCount = 0 ; 
        for(int i = 0 ; i < servers.size();  ++i) {
            Server s = servers.get(i); 
            List<Pair<Double, Double>> jobs  = s.getProcessedJobs(); 
            for(int j = 0 ; j < jobs.size(); ++j) {
               double time = jobs.get(j).getKey(); 
               double duration = jobs.get(j).getValue(); 
               acceptedTime[acceptedCount++] = time + duration; 
            }
        }
        sort(acceptedTime, acceptedCount); 
        
        processTime(); 
    } 
    
    private void sort(double[] times, int length) {
        for(int i = 0 ; i < length ; ++i) {
            for(int j = i+1 ; j < length ; ++j) {
                if(times[i] > times[j]) {
                    double t = times[i]; 
                    times[i] = times[j]; 
                    times[j] = t; 
                }
            }
        }
    }
    
    private void processTime() { 
        List<Double> arrivalDisplayedTimes = 
                findDisplayedTimes(arrivalTime, requestCount); 
        List<Double> acceptedDisplayedTimes = 
                findDisplayedTimes(acceptedTime, acceptedCount);
        List<Double> abortedDisplayedTimes  = 
                findDisplayedTimes(abortedTime, abortedCount);
        
        Pair<Integer, Integer> arrivalBorder  = findBorder(leftTime, rightTime, arrivalTime, requestCount);
        Pair<Integer, Integer> acceptedBorder  = findBorder(leftTime, rightTime, acceptedTime, acceptedCount);
        Pair<Integer, Integer> abortedBorder  = findBorder(leftTime, rightTime, abortedTime, abortedCount);
        
        //process request data
        RequestData rData = new RequestData(); 
        rData.setAbortedDisplayedTime(abortedDisplayedTimes);
        rData.setAcceptedDisplayedTime(acceptedDisplayedTimes);
        rData.setArrivalDisplayedTime(arrivalDisplayedTimes);
        rData.setLeftTime(leftTime);
        rData.setRightTime(rightTime);
        rData.setTotalArrivalCount(requestCount);
        rData.setFirstArrivalDisplayedOrder(arrivalBorder.getKey());
        rData.setFirstAcceptedDisplayedOrder(acceptedBorder.getKey());
        rData.setFirstAbortedDisplayedOrder(abortedBorder.getKey());
        
        //serve data
        ServeTimeData sData = new ServeTimeData();
        sData.setServers(servers);
        sData.setLeftTime(leftTime);
        sData.setRightTime(rightTime);
        
        //queue data
        QueueData qData = new QueueData(); 
        List<Double> displayedQueueChangeTime = 
                findDisplayedTimes(queueChangeTime, queueSize);
        Pair<Integer, Integer> border = 
                findBorder(leftTime, rightTime, queueChangeTime, queueSize); 
        List<Integer> queueValues = new ArrayList<>(); 
        int leftRequest = border.getKey();
        int rightRequest = border.getValue(); 
        if(leftRequest != -1  && rightRequest !=  -1) {
            for(int i = leftRequest ; i <= rightRequest ; ++i) {
                queueValues.add(queueAt[i]);
            }
        }
        
        qData.setQueueChangeTime(displayedQueueChangeTime);
        qData.setQueueValues(queueValues);
        qData.setLeftTime(leftTime);
        qData.setRightTime(rightTime);
        qData.setMaxQueueSize(inputData.getQueueLength());
        
        //public signals
        listener.requestDataChanged(rData);
        listener.serveDataChanged(sData);
        listener.queueDataChanged(qData);
    }
    
    private List<Double> findDisplayedTimes(double[] times, int count) {
        Pair<Integer, Integer>  arrivalRequestsBorder = findBorder(leftTime, 
                rightTime, times, count ) ;
        int leftRequest  = arrivalRequestsBorder.getKey(); 
        int rightRequest = arrivalRequestsBorder.getValue(); 
        firstArrivalDisplayedOrder = leftRequest; 
        List<Double> displayedTimes = new ArrayList<>();       
        if( leftRequest != -1 && rightRequest != -1) {
            double t; 
            for(int i = leftRequest; i <= rightRequest ; ++i) {
                t = times[i] - leftTime;
                displayedTimes.add(t); 
            }
            double firstTime = displayedTimes.get(0); 
            if(firstTime < 0) {
                firstTime = 0;
                displayedTimes.set(0, firstTime);
            }
        }
        return displayedTimes;
    }
    
    private Pair<Integer,Integer> findBorder(double leftTime, double rightTime, 
            double[] times, int count){
        
        int leftRequest = -1, rightRequest = -1 ; 
        if(count > 0) {
            if(leftTime < times[0]) {
                leftRequest = 0; 
            }
            if(rightTime > times[count -1] ) {
                rightRequest = count-1 ; 
            }
            if(leftRequest == -1) {
                boolean f = false;
                for(int i = 0 ; !f &&i < count-1 ; ++i) {
                    if(leftTime >= times[i] && leftTime <times[i+1]) {
                        f = true; 
                        leftRequest = i; 
                    }
                }
            }

            if(rightRequest == -1 && leftRequest != -1) {
                boolean f = false; 
                for(int i = leftRequest ; !f && i < count-1; ++i) {
                    if( rightTime >= times[i] && rightTime < times[i+1]) {
                        f = true; 
                        rightRequest = i; 
                    }
                }
            }
        }
        
        return new Pair<Integer, Integer> (leftRequest, rightRequest); 
    }
    
    private Server getEarliestFreeServer(List<Server> servers) {
        double t = 1000000; 
        int pos = 0; 
        for(int  i=  0 ; i < servers.size() ; ++i) {
            double time = servers.get(i).getFinishTimeOfLastJob(); 
            if(t > time) {
                t = time;
                pos = i; 
            }
        }
        return servers.get(pos); 
    }
    
    public void setInputData(InputData data) {
        this.inputData = data; 
    }
    
    public void addRequestDataChangeListener(RequestDataChangeListener listener){
        this.listener = listener ; 
    }
    
}
