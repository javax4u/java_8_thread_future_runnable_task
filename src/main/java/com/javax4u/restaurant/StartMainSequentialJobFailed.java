/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javax4u.restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author vdoxx
 */
public class StartMainSequentialJobFailed {

    public static void main(String[] args) throws InterruptedException{

        ExecutorService threadPool = Executors.newCachedThreadPool();

        Future dosaTask = threadPool.submit(new Task(Menu.DOSA));
        Future burgerTask = threadPool.submit(new Task(Menu.BURGER));
        Future rotitask = threadPool.submit(new Task(Menu.ROTI));
        while (!dosaTask.isDone()) {
           // this has no efect
        }
       
        while (!burgerTask.isDone()) {
            // this has no efect
        }
        
        while (!rotitask.isDone()) {
            // this has no efect
        }

        while (DisplayBoard.getStatusOfAll() != Status.COMPLETED) {
            // DisplayBoard.printStatusOfAll();
        }
        threadPool.shutdown();
        System.out.println("Table order completed. Program will exit now");
    }

}
//output
/**
BURGER preparing ...
DOSA preparing ...
ROTI preparing ...
DOSA completed after 10 seconds
BURGER completed after 15 seconds
ROTI completed after 20 seconds
Table order completed. Program will exit now
*/