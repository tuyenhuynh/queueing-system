/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem;

/**
 *
 * @author tuyenhuynh
 */
public class Request {
    double arrivalTime; 
    double leavingTime ; 

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(double leavingTime) {
        this.leavingTime = leavingTime;
    }
    
}
