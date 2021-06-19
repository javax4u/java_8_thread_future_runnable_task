/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javax4u.restaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author vdoxx
 */
public class StartMainLinkedListOrderedlJob {

    public static void main(String[] args) throws InterruptedException {

        // Queue<Task> orderList=new PriorityQueue<Task>();
        List<Task> orderList = new LinkedList<Task>();
        orderList.add(new Task(Menu.DOSA));
        orderList.add(new Task(Menu.BURGER));
        orderList.add(new Task(Menu.ROTI));

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future task;
        for (Task taskTemp : orderList) {
            task=threadPool.submit(taskTemp);
            while(!task.isDone()){
                
            }
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
* DOSA preparing ...
*DOSA completed after 10 seconds
*BURGER preparing ...
*BURGER completed after 15 seconds
*ROTI preparing ...
*ROTI completed after 20 seconds
*Table order completed. Program will exit now
 */
