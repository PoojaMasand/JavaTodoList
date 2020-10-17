package com.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ToDoTaskListTest {

    @Test
    void validateProject() {
        ToDoTaskList todoList = new ToDoTaskList();
        boolean validationFailed = todoList.validateProject("");
        assertEquals(true,validationFailed);
    }

    @Test
    void checkDuplicateTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        boolean validationFailed = todoList.validateTaskForAdd("Read Book");
        assertEquals(true,validationFailed);
    }

    @Test
    void addTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        boolean isSuccessful = todoList.addTask("Read Book","Education",LocalDate.now());
        assertEquals(true,isSuccessful);
    }

    @Test
    void updateTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        boolean isSuccessful = todoList.updateTask("Read Book","Hobby",LocalDate.now());
        assertEquals(true,isSuccessful);
    }

    @Test
    void removeTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        todoList.removeTask("Read Book");
        assertEquals(false,todoList.checkIfTaskExist("Read Book"));
    }

    @Test
    void completeTask() {
        ToDoTaskList todoList = new ToDoTaskList();
        todoList.addTask("Read Book","Education",LocalDate.now());
        boolean isSuccessful = todoList.completeTask("Read Book");
        assertEquals(true,isSuccessful);
    }

}