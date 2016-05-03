/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.event;

import com.tuyenhm.queuingsystem.data.QueueData;
import com.tuyenhm.queuingsystem.data.RequestData;
import com.tuyenhm.queuingsystem.data.ServeTimeData;

/**
 *
 * @author tuyenhuynh
 */
public interface RequestDataChangeListener {
    public void requestDataChanged(RequestData requestData); 
    public void serveDataChanged(ServeTimeData serveTimeData);
    public void queueDataChanged(QueueData queueData);
    
}
