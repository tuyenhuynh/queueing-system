/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.view;

import com.tuyenhm.queuingsystem.data.RequestData;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author tuyenhuynh
 */
public class RequestPanel extends  JPanel{
    
    private RequestData requestData ; 
    
    private static final int WIDTH = 900; 
    private static final int HEIGHT = 200; 
    
    private double scaleX; 
    private double scaleY; 
    private int arrivalOffset;
    private int acceptedOffset; 
    private int abortedOffset; 
    
    public void setRequestData(RequestData  requestData) {
        this.requestData = requestData ; 
    }
    
    public RequestPanel() {
        Dimension d = new Dimension(WIDTH, HEIGHT); 
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
        
        if(requestData != null) {
            drawAxises(g2); 
            List<Double> arrivalTime = requestData.getArrivalDisplayedTime(); 
            List<Double> acceptedTime = requestData.getAcceptedDisplayedTime();
            System.out.println("Accepted count: " + acceptedTime.size()); 
            List<Double> abortedTime = requestData.getAbortedDisplayedTime();
            System.out.println("Aborted count: " + abortedTime.size()); 
            arrivalOffset = requestData.getFirstArrivalDisplayedOrder(); 
            acceptedOffset = requestData.getFirstAcceptedDisplayedOrder(); 
            abortedOffset = requestData.getFirstAbortedDisplayedOrder(); 
            
            double displayedInterval = requestData.getRightTime() - requestData.getLeftTime(); 
            int totalArrivalCount = requestData.getTotalArrivalCount();
            scaleX = 900/displayedInterval ; 
            scaleY = 200/totalArrivalCount ;
            
            drawRequest(arrivalTime, g, arrivalOffset, Color.red); 
            drawRequest(acceptedTime, g, acceptedOffset, Color.yellow); 
            drawRequest(abortedTime, g, abortedOffset, Color.blue); 
        }
    }
    
    private void drawRequest(List<Double>request, Graphics g, int offset, Color color){
        Graphics2D g2 = (Graphics2D)g ; 
        g2.setStroke( new BasicStroke(2));
        g2.setColor(color);
        int size = request.size(); 
        
        if(size > 0) {
            int i, x1, x2, y1, y2 ; 
            for(i = 0 ; i <  size-1 ; ++i) {
                x1 = (int)(request.get(i)* scaleX) ; 
                x2 = (int)(request.get(i+1) * scaleX) ; 
                y1 = 200 - (int)((i+ offset)* scaleY ); 
                y2 = 200 - (int)((i+1 + offset) * scaleY); 
                g2.drawLine(x1, y1, x1, y2);
                g2.drawLine(x1, y2, x2, y2); 
                if(i == size -2 ) {
                    int y3 = 200 - (int) ((i+2 + offset) * scaleY); 
                    g2.drawLine( x2, y2, x2, y3);
                }
            }
            if(size == 1) {
                i =0 ; 
                x1 = (int)(request.get(i)* scaleX) ; 
                y1 = 200 - (int)((i+ offset)* scaleY ); 
                y2 = 200 - (int)((i+1 + offset) * scaleY); 
                g2.drawLine(x1, y1, x1, y2);
            }
        }
    }
    
    private void drawAxises(Graphics2D g) {
        int intervalHeight = (HEIGHT - 10)/10;
        g.setStroke(new BasicStroke(1));
        int requestCount = requestData.getTotalArrivalCount(); 
        int interval = (int)(requestCount/10) ;  
        if(interval == 0) {
            interval = 1 ; 
        }
        for(int i = 1 ; i <= 10; ++i) {
            int height = HEIGHT - intervalHeight* i;
            g.drawString("" + i* interval, 5, height);
            g.drawLine(0,height , WIDTH, height);
        }
    }
    
}
