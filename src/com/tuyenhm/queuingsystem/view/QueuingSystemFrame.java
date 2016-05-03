/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.view;

import com.tuyenhm.queuingsystem.Model;
import com.tuyenhm.queuingsystem.data.InputData;
import com.tuyenhm.queuingsystem.data.QueueData;
import com.tuyenhm.queuingsystem.data.RequestData;
import com.tuyenhm.queuingsystem.data.ServeTimeData;
import com.tuyenhm.queuingsystem.event.InputDataChangeListener;
import com.tuyenhm.queuingsystem.event.RequestDataChangeListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 *
 * @author tuyenhuynh
 */
public class QueuingSystemFrame extends JFrame{
    private InputPanel inputPanel  = new InputPanel ();
    private RequestPanel requestPanel = new RequestPanel(); 
    private ServeTimePanel serveTimePanel = new ServeTimePanel(); 
    private QueuePanel queuePanel = new QueuePanel(); 
    
    private Model model = new Model();
    
    public QueuingSystemFrame(){
        Dimension d = new Dimension(1200, 650); 
        setMaximumSize(d);
        setMinimumSize(d); 
        setPreferredSize(d);
        
        initializeComponents(); 
        model.addRequestDataChangeListener(new RequestDataChangeListener() {
            @Override
            public void requestDataChanged(RequestData requestData) {
                requestPanel.setRequestData(requestData);
                requestPanel.repaint();
            }

            @Override
            public void serveDataChanged(ServeTimeData serveTimeData) {
                serveTimePanel.setServeTimeData(serveTimeData);
                serveTimePanel.repaint();
            }

            @Override
            public void queueDataChanged(QueueData queueData) {
                queuePanel.setQueueData(queueData);
                queuePanel.repaint();
            }
            
        });
        
        inputPanel.addInputDataChangeListener(new InputDataChangeListener() {
            @Override
            public void inputDataChange(InputData inputData) {
                model.setInputData(inputData);
                model.analyzeInput();
            }
        });
    }
    
    private void initializeComponents() {
        GridBagLayout layout = new GridBagLayout(); 
        setLayout(layout); 
        
        GridBagConstraints gc = new GridBagConstraints(); 
        gc.gridwidth = 1; 
        gc.gridheight = 3; 
        gc.gridx = 0 ; 
        gc.gridy  = 0; 
        add(inputPanel, gc);
        gc.gridheight =1 ;
        gc.gridx =1; 
        gc.gridy  = 0 ; 
        gc.ipady =10 ;
        add(requestPanel, gc);
        gc.gridy =1 ; 
        gc.ipady =10 ; 
        add(serveTimePanel, gc); 
        
        gc.gridy = 2; 
        gc.ipady = 10; 
        add(queuePanel, gc); 
        
    }
    
}
