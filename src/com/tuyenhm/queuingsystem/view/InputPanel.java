/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.view;

import com.tuyenhm.queuingsystem.data.InputData;
import com.tuyenhm.queuingsystem.event.InputDataChangeListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author tuyenhuynh
 */
public class InputPanel extends JPanel{

    private JLabel channelCountLabel = new JLabel("Channel Count"); 
    private JTextField channelCountField = new JTextField("1");
    
    private JLabel sourceIntensiveLabel = new JLabel("Source Intensive");
    private JTextField sourceIntensiveField = new JTextField("0.1");
    
    private JLabel servingIntensiveLabel  = new JLabel("Serving Intensive");
    private JTextField servingIntensiveField = new JTextField("0.01");
    
    private JLabel requestCountLabel = new JLabel ("Request Count"); 
    private JTextField requestCountField = new JTextField("5");
    
    private JLabel queueLengthLabel = new JLabel("Queue Length");
    private JTextField queueLengthField = new JTextField("3"); 
    
    private JLabel leftTimeLabel = new JLabel("Left Time");
    private JTextField leftTimeField = new JTextField("0"); 
    
    private JLabel rightTimeLabel = new JLabel("Right Time");
    private JTextField rightTimeField = new JTextField("200"); 
    
    private JButton btnSimulation = new JButton("Simulation"); 
    
    private InputDataChangeListener listener ; 
    
    public void addInputDataChangeListener (InputDataChangeListener listener ) {
        this.listener = listener ; 
    }
    
    private InputData getInputData() {
        InputData data= null; 
        try{
            int channelCount = Integer.parseInt(channelCountField.getText()); 
            double sourceIntensive = Double.parseDouble(sourceIntensiveField.getText()); 
            double servingIntensive = Double.parseDouble(servingIntensiveField.getText()); 
            int requestCount = Integer.parseInt(requestCountField.getText());
            int queueLength = Integer.parseInt(queueLengthField.getText()); 
            double leftTime = Double.parseDouble(leftTimeField.getText()); 
            double rightTime = Double.parseDouble(rightTimeField.getText());
            data = new InputData(channelCount, sourceIntensive, 
                    servingIntensive, queueLength, requestCount, leftTime, rightTime); 
            
        }catch(Exception ex) {
            ex.printStackTrace();;
            JOptionPane.showMessageDialog(this, "Invalid input data");
        }
        return data ; 
    }
    
    public InputPanel() {
        Dimension d = new Dimension(300, 600); 
        setMaximumSize(d);
        setMinimumSize(d); 
        setPreferredSize(d);
        setLayout(new FlowLayout()); 
        channelCountField.setColumns(10);
        add(channelCountLabel); 
        add(channelCountField);
        sourceIntensiveField.setColumns(10);
        add(sourceIntensiveLabel);
        add(sourceIntensiveField);
        servingIntensiveField.setColumns(10);
        add(servingIntensiveLabel);
        add(servingIntensiveField); 
        requestCountField.setColumns(10);
        add(requestCountLabel);
        add(requestCountField); 
        queueLengthField.setColumns(10);
        add(queueLengthLabel);
        add(queueLengthField);
        leftTimeField.setColumns(10);
        add(leftTimeLabel);
        add(leftTimeField); 
        rightTimeField.setColumns(10);
        add(rightTimeLabel);
        add(rightTimeField); 
        add(btnSimulation);
        
        btnSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputData data  = getInputData(); 
                listener.inputDataChange(data);
            }
        });
        
    }
    
}
