/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.data;

import com.tuyenhm.queuingsystem.Server;
import java.util.List;

/**
 *
 * @author tuyenhuynh
 */
public class ServeTimeData {
    private int arrivalCount ; 
    private double[] arrivalTime;
    private double[] serveTime ; 
    private List<Server> servers ; 
    private double displayedTotalTime; 
    private double leftTime; 
    private double rightTime; 

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
    
    public List<Server> getServers() {
        return servers;
    }

    public double getDisplayedTotalTime() {
        return displayedTotalTime;
    }

    public void setDisplayedTotalTime(double displayedTotalTime) {
        this.displayedTotalTime = displayedTotalTime;
    }
    
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    
    public int getArrivalCount() {
        return arrivalCount;
    }

    public void setRequestCount(int requestCount) {
        this.arrivalCount = requestCount;
    }

    public double[] getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double[] arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double[] getServeTime() {
        return serveTime;
    }

    public void setServeTime(double[] serveTime) {
        this.serveTime = serveTime;
    }
    
}
