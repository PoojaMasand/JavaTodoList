package com.project;

/**
 * This is the Execution Class for ToDoList Application. It marks the starting point of the application.
 * It has the main method which calls the start method of the TodoTaskList class
 * from where user gets all the options to proceed further.
 *
 * @author Pooja Masand
 * @version 1.0 (2020.10.07)
 */

public class Main {

    public static void main(String[] args) {
        ToDoTaskList toDoTasklist = new ToDoTaskList();
        toDoTasklist.start();
    }
}
