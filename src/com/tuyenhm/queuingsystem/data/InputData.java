/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.data;

/**
 *
 * @author tuyenhuynh
 */
public class InputData {
    private int channel ; 
    private double sourceIntensive; 
    private double servingIntensive; 
    private int queueLength; 
    private double leftTime;
    private double rightTime; 
    
    //value of 10^4
    private int requestCount;
    public InputData(int channelCount, double sourceIntensive, 
            double servingIntensive, int queueLength, int requestCount, 
            double leftTime, double rightTime) {
        this.channel = channelCount ; 
        this.sourceIntensive = sourceIntensive; 
        this.servingIntensive = servingIntensive; 
        this.queueLength = queueLength; 
        this.requestCount = requestCount; 
        this.leftTime = leftTime; 
        this.rightTime = rightTime; 
    }

    public double getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(double leftTime) {
        this.leftTime = leftTime;
    }

    public double getRightTime() {
        return rightTime;
    }

    public void setRightTime(double rightTime) {
        this.rightTime = rightTime;
    }

    public int getChannel() {
        return channel;
    }

    public double getSourceIntensive() {
        return sourceIntensive;
    }

    public double getServingIntensive() {
        return servingIntensive;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public int getRequestCount() {
        return requestCount;
    }
    
}
