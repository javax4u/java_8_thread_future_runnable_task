/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javax4u.restaurant;

import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vdoxx
 */
enum Status {
    ENTERED, START, PRCOCESSING, COMPLETED, FAILED
}

enum Menu {
    DOSA, BURGER, CHAI, ROTI, DAAL
}

class DisplayBoard {

    static Map<Menu, Status> taskMap = new ConcurrentHashMap<Menu, Status>();

    static Status getStatusOfTask(Menu dosaOrder) {
        return taskMap.get(dosaOrder);
    }

    static void addTask(Menu taskName) {
        taskMap.put(taskName, Status.ENTERED);
    }

    static void updateTask(Menu taskName, Status status) {
        taskMap.put(taskName, status);
    }

    static Status getStatusOfAll() {
        Status returnMe = Status.COMPLETED;
        for (Map.Entry<Menu, Status> entry : taskMap.entrySet()) {
            Menu key = entry.getKey();
            Status value = entry.getValue();
            if (value != Status.COMPLETED) {
                returnMe = Status.PRCOCESSING;
            }
        }
        return returnMe;
    }

    static void printStatusOfAll() {
        System.out.println(taskMap);
    }

}

class Task implements Runnable {

    Menu name;

    Task(Menu taskName) {
        name = taskName;
        DisplayBoard.addTask(taskName);
    }

    @Override
    public void run() {
        

        try {
            Thread.currentThread().setName(name.toString());
            System.out.println(Thread.currentThread().getName() + " preparing ...");
            if (name == Menu.DOSA) {
                // Thread.currentThread().wait(TimeUnit.SECONDS.toSeconds(5));
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + " completed after 10 seconds");
            } else if (name == Menu.BURGER) {
                // Thread.currentThread().wait(TimeUnit.SECONDS.toSeconds(2));
                TimeUnit.SECONDS.sleep(15);
                System.out.println(Thread.currentThread().getName() + " completed after 15 seconds");
            } else if (name == Menu.ROTI) {
                //Thread.currentThread().wait(TimeUnit.SECONDS.toSeconds(3));
                TimeUnit.SECONDS.sleep(20);
                System.out.println(Thread.currentThread().getName() + " completed after 20 seconds");
            }

            DisplayBoard.updateTask(name, Status.COMPLETED);

        } catch (InterruptedException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            DisplayBoard.updateTask(name, Status.FAILED);
        }
    }

}
