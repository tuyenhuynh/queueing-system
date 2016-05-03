/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.view;

import com.tuyenhm.queuingsystem.data.QueueData;
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
public class QueuePanel extends JPanel {
    private QueueData queueData ; 
    
    private static final int WIDTH = 900; 
    private static final int HEIGHT = 200; 
    
    private  int maxQueueSize = 10;
    
    private List<Integer> queueValues; 
    private List<Double> queueChangeTime;
    
    private double scaleX;
    private double scaleY; 
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g; 
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(0, 0, 0 ,  HEIGHT);
        g2.drawLine(0, HEIGHT, WIDTH, HEIGHT);
        
        
        
        if(queueData != null) {
            queueValues = queueData.getQueueValues(); 
            queueChangeTime = queueData.getQueueChangeTime(); 
            int queueSize = queueValues.size();
            maxQueueSize = queueData.getMaxQueueSize(); 
            
            double leftTime = queueData.getLeftTime(); 
            double rightTime = queueData.getRightTime(); 
            scaleX = 900/ (rightTime - leftTime ); 
            scaleY = 200.0/maxQueueSize ; 
            
            drawAxises(g2); 
            
            for(int i = 0 ; i < queueSize -1  ; ++i) {
                int x1 = (int)(queueChangeTime.get(i) * scaleX);
                int y1 = 0 ;
                int y2 = 200; 
                g.drawLine(x1, y1, x1, y2); 
            }
            drawQueue(g);
        }
    }
    
    private void drawQueue(Graphics g) {
        
        int size = queueChangeTime.size(); 
        if(size > 0) {
            int i = 0; 
            int x1 = (int)(queueChangeTime.get(i)* scaleX) ; 
            int x2 = x1; //(int)(queueChangeTime.get(i+1) * scaleX) ; 
            int y1 = 200 ; // = 200 - (int)(queueValues.get(i)* scaleY ); 
            int y2 = 200 - (int)(queueValues.get(i) * scaleY); 
            Graphics2D g2 = (Graphics2D)g; 
            g2.setColor(Color.CYAN);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x1, y1, x2, y1);
            g2.drawLine(x2, y1, x2, y2);
            for(i = 0 ; i < size -1   ; ++i) {
                x1 = (int)(queueChangeTime.get(i)* scaleX) ; 
                x2 = (int)(queueChangeTime.get(i+1) * scaleX) ; 
                y1 = 200 - (int)(queueValues.get(i)* scaleY ); 
                y2 = 200 - (int)(queueValues.get(i+1) * scaleY); 
                g2.setColor(Color.CYAN);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(x1, y1, x2, y1);
                g2.drawLine(x2, y1, x2, y2);
            }
        }
        
        
        
    }
    
    private void drawAxises(Graphics2D g) {
        int intervalHeight = (HEIGHT)/maxQueueSize;
        g.setStroke(new BasicStroke(1));
        for(int i = 1 ; i <= maxQueueSize; ++i) {
            int height = HEIGHT - intervalHeight* i;
            g.drawString("" + i, 5, height);
            g.drawLine(0,height , WIDTH, height);
        }
    }
    
    public QueuePanel() {
        Dimension d = new Dimension(900, 200); 
        setMaximumSize(d);
        setMinimumSize(d); 
        setPreferredSize(d);        
    }
    
    public void setQueueData(QueueData data) {
        this.queueData = data ; 
    }
    
}
