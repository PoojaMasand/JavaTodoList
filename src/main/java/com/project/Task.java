package com.project;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * This class is the main manager class of the ToDoList Application.
 * All the task related details are stored in the file.
 * The object of this class contains task description, project, due date and status.
 * The file also contains Comparator for sorting functionality.
 *
 * @author Pooja Masand
 * @version 2020.10.08
 */

public class Task implements Serializable {
    private static final long serialVersionUID = 4L;
    private String description;
    private String project;
    private LocalDate taskDueDate;
    private String status;


    public void setProject(String project) {
        this.project = project;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public String getProject() {
        return project;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Constructor for ToDoTaskManager
     *
     * @param taskDescription, task title entered by the user
     * @param projectName      entered by the user
     * @param date             entered by the user
     * @param initialStatus    system generated for new task
     */
    public Task(String taskDescription, String projectName, LocalDate date, String initialStatus) {

        description = taskDescription;
        project = projectName;
        taskDueDate = date;
        status = initialStatus;
    }

    /**
     * Comparator for sorting Task objects by Project Name
     */
    public static Comparator<Task> ProjectNameComparator = (task1, task2) -> {
        String projectName1 = task1.getProject().toUpperCase();
        String projectName2 = task2.getProject().toUpperCase();

        //ascending order
        return projectName1.compareTo(projectName2);

    };

    /**
     * Comparator for sorting Task objects by Date
     */
    public static Comparator<Task> DateComparator = (task1, task2) -> {
        LocalDate date1 = task1.getTaskDueDate();
        LocalDate date2 = task2.getTaskDueDate();

        //ascending order
        return date1.compareTo(date2);

    };

    /**
     * Method to return a readable string
     *
     * @return String with task object detail
     */
    public String toString() {
        return "Task Title =" + description + "     Project = " + project
                + "    Date = " + taskDueDate + "Status = " + status;
    }
}
