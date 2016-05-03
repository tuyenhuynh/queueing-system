/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.view;

import com.tuyenhm.queuingsystem.Server;
import com.tuyenhm.queuingsystem.data.ServeTimeData;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List; 
import javafx.util.Pair;

/**
 *
 * @author tuyenhuynh
 */
public class ServeTimePanel extends JPanel {
    private ServeTimeData serveTimeData ; 
    
    private static final int WIDTH = 900; 
    private static final int HEIGHT = 200; 
    
    private double leftTime ; 
    private double rightTime; 
    
    private double scaleX; 
    
    public void setServeTimeData(ServeTimeData data) {
        this.serveTimeData = data; 
    }
    
    public ServeTimePanel() {
        Dimension d = new Dimension(900, 200); 
        setMaximumSize(d);
        setMinimumSize(d); 
        setPreferredSize(d);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g; 
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(0, 0, 0 ,  HEIGHT);
        g2.drawLine(0, HEIGHT, WIDTH, HEIGHT);
        
        if(serveTimeData != null) {
            List<Server> servers = serveTimeData.getServers(); 

            leftTime = serveTimeData.getLeftTime();
            rightTime = serveTimeData.getRightTime(); 
            double displayedTotalTime = rightTime - leftTime;  
            scaleX = 900 / displayedTotalTime; 

            for( int i = 0 ; i < servers.size(); ++i) {
                drawServeData(servers.get(i), i, g); 
            }
        }
    }
    
    private void drawServeData(Server server, int serverId, Graphics g){
        List<Pair<Double, Integer>> jobs = processServeTime(server); 
        int serveCount = jobs.size(); 
        
        int height = 30; 
        for (int i = 0; i < serveCount; ++i) {
            Pair<Double, Integer> data = jobs.get(i); 
            int x = (int) (data.getKey() * scaleX);
            int y = HEIGHT - serverId*35 - 30;
            g.setColor(Color.black);
            //g.drawLine(x1, y1, x1, y2);
            //g.drawString("t" +i, x1+20, 20);
            int width = (int)(data.getValue() * scaleX);
            g.drawRect(x, y , width, height);
            //g.drawString("tau"+i, x1, HEIGHT - 30);
            g.setColor(Color.green);
            g.fillRect(x, y, width, height);
        }
    }
    
    private List<Pair<Double, Integer>> processServeTime(Server s) {
        List<Pair<Double, Integer>> result = new ArrayList<>(); 
        List<Pair<Double, Double>> jobs = s.getProcessedJobs(); 
        for(int i = 0 ;i < jobs.size(); ++i) {
            Pair<Double, Double> job = jobs.get(i);
            int  width ; // = job.getValue().intValue(); 
            double left = job.getKey(); 
            double right = left + job.getValue();
            
            if(left >=rightTime || right <=leftTime) {
                width  = -1; 
            }else {
                left-= leftTime ; 
                right-= leftTime ; 
                if(left < 0) {
                    left = 0 ; 
                }
                if(right > rightTime) {
                    right = rightTime ; 
                }
                width = (int) (right - left); 
            }
            if(width != -1) {
                result.add(new Pair<Double, Integer>(left, width));
            }
            
//            
//            if(left <= leftTime && right > leftTime && right < rightTime) {
//                left -= leftTime; 
//                right -= leftTime; 
//                if(left <0){
//                    left = 0; 
//                } 
//                width  =(int)( right - left); 
//            }else if(left > leftTime &&  right > leftTime && left < rightTime && right < rightTime) {
//                left -= leftTime; 
//                right -= leftTime;
//                width = (int)( right - left);
//            }else if(left > leftTime && left <rightTime && right >= rightTime) {
//                left -= leftTime; 
//                right -= leftTime;
//                if(right > rightTime){
//                    right  = rightTime; 
//                }
//                width = (int)( right - left);
//            }
            
        }
        return result ; 
    }
}

