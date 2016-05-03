/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.data;

import java.util.List;

/**
 *
 * @author tuyenhuynh
 */
public class QueueData {
    
    private int queueChangeCount;
    
    private int[] queueSizeAt ; 

    private double leftTime; 
    private double rightTime; 
    private List<Integer> queueValues; 
    private List<Double> queueChangeTime; 
    private int maxQueueSize; 

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }
    
    public List<Integer> getQueueValues() {
        return queueValues;
    }

    public void setQueueValues(List<Integer> queueValues) {
        this.queueValues = queueValues;
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
    
    public int getQueueChangeCount() {
        return queueChangeCount;
    }

    public void setQueueChangeCount(int queueChangeCount) {
        this.queueChangeCount = queueChangeCount;
    }

    public List<Double> getQueueChangeTime() {
        return queueChangeTime;
    }

    public void setQueueChangeTime(List<Double> queueChangeTime) {
        this.queueChangeTime = queueChangeTime;
    }

    public int[] getQueueSizeAt() {
        return queueSizeAt;
    }

    public void setQueueSizeAt(int[] queueSizeAt) {
        this.queueSizeAt = queueSizeAt;
    }
    
}
