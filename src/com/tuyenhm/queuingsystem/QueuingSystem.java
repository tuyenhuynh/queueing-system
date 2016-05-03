/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuyenhuynh
 */
public class QueuingSystem {
    
    private int channel ; 
    private double sourceIntensive; 
    private double servingIntensive; 
    private int queueLength; 
    
    //value of 10^4
    private int requestCount;
    
    List<Request> arrivalRequests = new ArrayList<>() ; 
    
    List<Request> acceptedRequests = new ArrayList<>(); 
    
    List<Request> abortedRequests = new ArrayList<>(); 
    
    public List<Request> getArrivalRequests() {
        return this.arrivalRequests; 
    }
    
    public List<Request> getAcceptedRequests () {
        return this.acceptedRequests; 
    }
    
    public List<Request> getAbortedRequests () {
        return this.abortedRequests; 
    }
    
    public void getQueueStatus() {
        
    }
    
    
    public void genQueingSystem() {
        
    } 
    
    
    
    
    

}
