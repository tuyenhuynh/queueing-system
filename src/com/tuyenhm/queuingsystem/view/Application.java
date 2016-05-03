/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.queuingsystem.view;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.uncommons.maths.random.ExponentialGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

/**
 *
 * @author tuyenhuynh
 */
public class Application {
      public static void main(String[] argv) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                JFrame app = new QueuingSystemFrame();
                app.setVisible(true);
                app.setLocationRelativeTo(null);
                app.setTitle("Queuing System Simulation");
                app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }
}
