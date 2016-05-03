/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author tuyenhuynh
 */
public class Server {
    //key: start time ; 
    //value: processing time
    private List<Pair<Double, Double>> processingHistory = new ArrayList<Pair<Double, Double>>(); 
    
    public boolean isFree(double time) {
        int size = processingHistory.size(); 
        if(size > 0) {
            Pair<Double, Double> lastServe = processingHistory.get(size - 1); 
            double start = lastServe.getKey() ; 
            double end = start + lastServe.getValue(); 
            return (time >= end);
        }else {
            return true ; 
        } 
    }
    
    public void assignJob(Pair<Double,Double> serveTime) {
        processingHistory.add(serveTime);
    }
    
    double getFinishTimeOfLastJob() {
        int size = processingHistory.size(); 
        if(size > 0) {
            Pair<Double, Double> lastServe = processingHistory.get(size -1); 
            return lastServe.getKey() + lastServe.getValue(); 
        }
        return 0; 
    }
    
    public List<Pair<Double, Double>> getProcessedJobs() {
        return processingHistory; 
    }
}

