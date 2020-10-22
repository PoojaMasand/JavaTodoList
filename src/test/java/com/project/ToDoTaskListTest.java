package com.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ToDoTaskListTest {

    @Test
    void checkIfProjectEmpty() {
        ToDoTaskList todoList = new ToDoTaskList();
        boolean validationFailed = todoList.validateProject("");
        assertTrue(validationFailed);
    }

    @Test
    void checkIfTaskEmpty() {
        ToDoTaskList todoList = new ToDoTaskList();
        boolean validationFailed = todoList.checkIfTaskEmpty("");
        assertTrue(validationFailed);
    }

    @Test
    void checkDuplicateTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        boolean validationFailed = todoList.validateTaskForAdd("Read Book");
        assertTrue(validationFailed);
    }

    @Test
    void addTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        boolean isSuccessful = todoList.addTask("Read Book","Education",LocalDate.now());
        assertTrue(isSuccessful);
    }

    @Test
    void updateTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        boolean isSuccessful = todoList.updateTask("Read Book","Hobby",LocalDate.now());
        assertTrue(isSuccessful);
    }

    @Test
    void removeTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        todoList.removeTask("Read Book");
        assertFalse(todoList.checkIfTaskExist("Read Book"));
    }

    @Test
    void completeTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        boolean isSuccessful = todoList.completeTask("Read Book");
        assertEquals(true,isSuccessful);
    }

}