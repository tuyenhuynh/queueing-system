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
public class RequestData {
    private int totalArrivalCount ; 
    private double[] arrivalTime; 
    private double[] acceptedTime;
    private double[] abortedTime; 
    private double leftTime; 
    private double rightTime; 
    
    private int firstArrivalDisplayedOrder;
    private int firstAbortedDisplayedOrder;
    private int firstAcceptedDisplayedOrder; 
    
    private List<Double> arrivalDisplayedTime; 
    private List<Double> acceptedDisplayedTime; 
    private List<Double> abortedDisplayedTime; 

    public int getFirstArrivalDisplayedOrder() {
        return firstArrivalDisplayedOrder;
    }

    public void setFirstArrivalDisplayedOrder(int firstArrivalDisplayedOrder) {
        this.firstArrivalDisplayedOrder = firstArrivalDisplayedOrder;
    }

    public int getFirstAbortedDisplayedOrder() {
        return firstAbortedDisplayedOrder;
    }

    public void setFirstAbortedDisplayedOrder(int firstAbortedDisplayedOrder) {
        this.firstAbortedDisplayedOrder = firstAbortedDisplayedOrder;
    }

    public int getFirstAcceptedDisplayedOrder() {
        return firstAcceptedDisplayedOrder;
    }

    public void setFirstAcceptedDisplayedOrder(int firstAcceptedDisplayedOrder) {
        this.firstAcceptedDisplayedOrder = firstAcceptedDisplayedOrder;
    }

    public List<Double> getArrivalDisplayedTime() {
        return arrivalDisplayedTime;
    }

    public void setArrivalDisplayedTime(List<Double> arrivalDisplayedTime) {
        this.arrivalDisplayedTime = arrivalDisplayedTime;
    }

    public List<Double> getAcceptedDisplayedTime() {
        return acceptedDisplayedTime;
    }

    public void setAcceptedDisplayedTime(List<Double> acceptedDisplayedTime) {
        this.acceptedDisplayedTime = acceptedDisplayedTime;
    }

    public List<Double> getAbortedDisplayedTime() {
        return abortedDisplayedTime;
    }

    public void setAbortedDisplayedTime(List<Double> abortedDisplayedTime) {
        this.abortedDisplayedTime = abortedDisplayedTime;
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
    
    public int getTotalArrivalCount() {
        return totalArrivalCount;
    }

    public void setTotalArrivalCount(int totalArrivalCount) {
        this.totalArrivalCount = totalArrivalCount;
    }

    public double[] getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double[] arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double[] getAcceptedTime() {
            return acceptedTime;
    }

    public void setAcceptedTime(double[] acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public double[] getAbortedTime() {
        return abortedTime;
    }

    public void setAbortedTime(double[] abortedTime) {
        this.abortedTime = abortedTime;
    }
    
}
