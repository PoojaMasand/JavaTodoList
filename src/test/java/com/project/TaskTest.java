package com.project;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void setProject() {
        Task taskManager =
                new Task("Go for Holiday","Family",
                        LocalDate.now(),"PENDING");
        assertEquals("Family",taskManager.getProject());
    }

    @Test
    void getStatus() {
        Task taskManager =
                new Task("Go for Holiday","Family",
                        LocalDate.now(),"Pending");
        assertEquals("Pending",taskManager.getStatus());
    }

}