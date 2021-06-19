/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javax4u.restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author vdoxx
 */
public class StartMain {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        threadPool.submit(new Task(Menu.DOSA));
        threadPool.submit(new Task(Menu.BURGER));
        threadPool.submit(new Task(Menu.ROTI));

        
        while (DisplayBoard.getStatusOfAll() != Status.COMPLETED) {
           // DisplayBoard.printStatusOfAll();
        }
        threadPool.shutdown();
        System.out.println("Table order completed. Program will exit now");
    }

}

//output
/**
 * DOSA preparing ...
ROTI preparing ...
BURGER preparing ...
DOSA completed after 10 seconds
BURGER completed after 15 seconds
ROTI completed after 20 seconds
Table order completed. Program will exit now
 */