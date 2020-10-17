package com.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * This class is the main operational class of the ToDoList Application.
 * "ToDoList" is a very simple, text based application.
 * The application will allow a user to create new tasks, assign them a title and due date,
 * and choose a project for that task to belong to.
 * The user should be able to also edit, mark as done or remove tasks.
 * They can also quit and save the current task list to file.
 *
 * @author Pooja Masand
 * @version 2020.10.08
 */

public class ToDoTaskList {
    private ArrayList<Task> tasklist = new ArrayList<>();
    Parser parser = new Parser();
    private final String PENDING = "Pending";
    private final String COMPLETE = "Complete";

    /**
     * This method will start the application by giving all the available options to the user
     */
    public void start() {
        readFromFile();
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for using ToDoList Application.");
    }

    /**
     * This method is used to direct to the respective method based on the option selected by the user.
     *
     * @param command to be processed.
     * @return true If user quits the application.
     */
    private boolean processCommand(Command command) {
        boolean isFinished = false;

        try {
            CommandWord commandWord = command.getCommandWord();

            switch (commandWord) {
                case UNKNOWN:
                    System.out.println("Please enter a valid Option");
                    break;

                case RELOAD:
                    printWelcome();
                    break;

                case ADD:
                    putValidationAndAddTask();
                    askForReload();
                    break;

                case SEARCH_BY_PROJECT:
                    sortList(true, false); // ProjectFlag = true, // DateFlag = false
                    askForReload();
                    break;

                case SEARCH_BY_DATE:
                    sortList(false, true); // ProjectFlag = false, // DateFlag = true
                    askForReload();
                    break;

                case EDIT:
                    sortList(true, false); // Display records for user to choose and update
                    putValidationAndUpdateTask();
                    askForReload();
                    break;

                case COMPLETE:
                    sortList(true, false);
                    putValidationAndCompleteTask();
                    askForReload();
                    break;

                case REMOVE:
                    sortList(true, false);
                    putValidationAndRemoveTask();
                    askForReload();
                    break;
                case QUIT:
                    saveToFile();
                    isFinished = true;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFinished;
    }

    /**
     * This method is used to give the option to the user to view all options or quit.
     */
    private void askForReload() {
        System.out.println();
        System.out.println("Type 0 to View the Options again or Type 7 to Quit");
        System.out.println();
    }

    /**
     * This method is used to put validation for task details and add task to the arraylist
     * User is asked to enter task title, project name and due date
     */

    private void putValidationAndAddTask() {
        Scanner sc = new Scanner(System.in);
        String taskDescription;
        String projectName;
        boolean isFinished = false;
        LocalDate taskDueDate = LocalDate.now();
        try {
            while (!isFinished) {
                System.out.println("Enter Task Title");
                taskDescription = sc.nextLine();

                if (validateTaskForAdd(taskDescription))
                    continue;

                System.out.println("Enter Project");
                projectName = sc.nextLine();

                if (validateProject(projectName))
                    continue;

                System.out.println("Enter Date in format yyyy-mm-dd");
                taskDueDate = getCorrectDateFormat(sc);

                addTask(taskDescription, projectName, taskDueDate);  // Separating add method for unit testing

                System.out.println();
                System.out.println("Do you want to add more task.Type Y/N ");
                String userAnswer = sc.nextLine();
                isFinished = !userAnswer.equalsIgnoreCase("Y");

            }

        } catch (Exception e) {
            System.out.println("Error in adding Task " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * This method is used to validate that user has not added empty project name
     *
     * @param projectName entered by the user
     * @return true if project name is empty
     */

    public boolean validateProject(String projectName) {
        boolean validationFailed = false;
        if (projectName.isEmpty()) {
            System.out.println("Project cannot be empty");
            System.out.println();
            validationFailed = true;
        }
        return validationFailed;
    }

    /**
     * This method is used to validate that task entered is not empty or duplicate
     *
     * @param taskDescription entered by the user
     * @return true if validation failed
     */

    public boolean validateTaskForAdd(String taskDescription) {
        boolean validationFailed = false;

        if (checkIfTaskEmpty(taskDescription))
            validationFailed = true;
        else if(checkIfTaskExist(taskDescription))
        {
            System.out.println("This Task title already exist.Give another title");
            System.out.println();
            validationFailed = true;
        }

        return validationFailed;
    }

    /**
     * This method is used to add the task object with title, project name and due date to the tasklist
     *
     * @param taskDescription as entered by the user
     * @param projectName     as entered by the user
     * @param taskDueDate     as entered by the user
     * @return true if add is successful
     */

    public boolean addTask(String taskDescription, String projectName, LocalDate taskDueDate) {
        boolean taskSucessful;
        try {
            Task taskManager = new Task(taskDescription.trim(), projectName, taskDueDate, PENDING);
            tasklist.add(taskManager);
            System.out.println();
            System.out.println("Task added Successfully");
            taskSucessful = true;
        } catch (Exception e) {
            System.out.println("Error in adding Task " + e.getMessage());
            taskSucessful = false;
        }
        return taskSucessful;

    }

    /**
     * This method is used to get the correct DateFormat from the user input
     *
     * @param sc object of Scanner class
     * @return LocalDate object
     */

    private LocalDate getCorrectDateFormat(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean notAValidDate = true;
        LocalDate taskDueDate = LocalDate.now();
        while (notAValidDate) {
            try {
                String date = sc.nextLine();
                taskDueDate = LocalDate.parse(date, formatter);
                notAValidDate = false;

            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date format");
                System.out.println();
            }
        }
        return taskDueDate;
    }

    /**
     * This method is used to put the validation and update task to the arraylist
     * User is asked to enter task title, project name and due date
     */

    private void putValidationAndUpdateTask() {
        Scanner sc = new Scanner(System.in);
        String taskDescription;
        String projectName;
        boolean isFinished = false;
        LocalDate taskDueDate = LocalDate.now();
        try {
            System.out.println("Please enter the Task title which need to be Updated");
            while (!isFinished) {
                System.out.println("Enter Task Title");
                taskDescription = sc.nextLine();

                if (validateTaskForEdit(taskDescription))
                    continue;

                System.out.println("Enter Project");
                projectName = sc.nextLine();

                if (validateProject(projectName))
                    continue;

                System.out.println("Enter Date in format yyyy-mm-dd");
                taskDueDate = getCorrectDateFormat(sc);

                updateTask(taskDescription, projectName, taskDueDate); // seperating update method for unit testing

                System.out.println("Do you want to update more tasks.Type Y/N ");
                String userAnswer = sc.nextLine();
                isFinished = !userAnswer.equalsIgnoreCase("Y");
            }

        } catch (Exception e) {
            System.out.println("Error in updation " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is used to validate that task is not empty and its already there in the tasklist
     *
     * @param taskDescription as entered by the user
     * @return true
     */
    private boolean validateTaskForEdit(String taskDescription) {
        boolean validationFailed = false;

        if (checkIfTaskEmpty(taskDescription)) {
            validationFailed = true;
        }
        else if(!checkIfTaskExist(taskDescription)) {
            System.out.println("This task does not exist");
            validationFailed = true;
        }

        return validationFailed;
    }

    /**
     * This method is used to update the Task object and add it back to the list
     *
     * @param taskDescription as entered by the user
     * @param projectName     as entered by the user
     * @param taskDueDate     as entered by the user
     * @return true if updation is successful
     */


    public boolean updateTask(String taskDescription, String projectName, LocalDate taskDueDate) {
        boolean taskSuccessful;
        try {
            for (Task taskManager : tasklist) {
                if (taskManager.getDescription().equals(taskDescription)) {
                    taskManager.setProject(projectName);
                    taskManager.setTaskDueDate(taskDueDate);
                    break;
                }
            }
            System.out.println();
            System.out.println("Updation of Task Successful");
            taskSuccessful = true;
        } catch (Exception e) {
            System.out.println("Error in updating Task" + e.getMessage());
            taskSuccessful = false;
        }
        return taskSuccessful;
    }

    /**
     * This method is used to prevent the user to add duplicate value of task
     *
     * @param taskDescription, the title of the task entered by the user
     * @return true if task exist
     */
    public boolean checkIfTaskExist(String taskDescription) {
        Iterator<Task> iterator = tasklist.iterator();
        boolean taskFound = false;


        while (iterator.hasNext()) {
            Task taskManager = iterator.next();
            if (taskManager.getDescription().equals(taskDescription)) {
                taskFound = true;
                break;
            }
        }
        return taskFound;
    }

    /**
     * This method is used to check if Task entered by the user is empty
     *
     * @param taskDescription, the task title entered by the user
     * @return true if task is empty
     */
    private boolean checkIfTaskEmpty(String taskDescription) {
        boolean isEmpty = false;
        if (taskDescription.isEmpty()) {
            System.out.println("Task cannot be empty");
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * This method is used to validate the task and remove it from the arraylist
     * User is asked to enter task title
     */
    private void putValidationAndRemoveTask() {
        Scanner sc = new Scanner(System.in);
        String taskDescription;
        boolean isFinished = false;
        try {
            System.out.println("Please enter the Task title which need to be removed");
            while (!isFinished) {
                System.out.println("Enter Task Title");
                taskDescription = sc.nextLine();

                if (validateTaskForEdit(taskDescription))
                    continue;

                removeTask(taskDescription); // separating remove functionality for unit testing

                System.out.println("Do you want to remove more tasks.Type Y/N ");
                String userAnswer = sc.nextLine();
                isFinished = !userAnswer.equalsIgnoreCase("Y");
            }

        } catch (Exception e) {
            System.out.println("Error in removing the Task " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is used to remove the task from the tasklist
     *
     * @param taskDescription as entered by the user
     * @return true if removal is successful
     */
    public boolean removeTask(String taskDescription) {
        boolean taskSuccessful;
        try {
            Iterator<Task> iterator = tasklist.iterator();
            while (iterator.hasNext()) {
                Task taskManager = iterator.next();
                if (taskManager.getDescription().equals(taskDescription)) {
                    iterator.remove();
                    break;
                }
            }
            taskSuccessful = true;
            System.out.println();
            System.out.println("Removal of task Successful");
        } catch (Exception e) {
            System.out.println("Error in removing the task " + e.getMessage());
            taskSuccessful = false;
        }
        return taskSuccessful;
    }

    /**
     * This method is used to put the validation and complete the status to complete
     * User is asked to enter task title
     */
    private void putValidationAndCompleteTask() {
        Scanner sc = new Scanner(System.in);
        String taskDescription;
        boolean isFinished = false;
        try {
            System.out.println("Please enter the Task title which need to be Completed");
            while (!isFinished) {
                System.out.println("Enter Task Title");
                taskDescription = sc.nextLine();

                if (validateTaskForEdit(taskDescription))
                    continue;

                completeTask(taskDescription);

                System.out.println("Do you want to complete more tasks.Type Y/N ");
                String userAnswer = sc.nextLine();
                isFinished = !userAnswer.equalsIgnoreCase("Y");
            }

        } catch (Exception e) {
            System.out.println("Error in Completing the Task " + e.getMessage());
            e.printStackTrace();

        }
    }

    public boolean completeTask(String taskDescription)  {
        boolean taskSuccessful;
        try {
            for (Task taskManager : tasklist) {
                if (taskManager.getDescription().equals(taskDescription)) {
                    taskManager.setStatus(COMPLETE);
                    break;
                }
            }
            System.out.println();
            System.out.println("Completion of Task Successful");
            taskSuccessful = true;
        } catch (Exception e) {
            System.out.println("Error in completing the task " + e.getMessage());
            taskSuccessful = false;
        }
        return taskSuccessful;
    }

    /**
     * This method is used to call saveToTextFile method of FileHandler class
     */
    private void saveToFile() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.saveToTextFile(tasklist);
    }

    /**
     * This method is used to search task based on due date or project
     *
     * @param projectFlag is true if it is project based sorting, dateFlag is true if it is date based sorting
     */
    private void sortList(boolean projectFlag, boolean dateFlag) {
        // In future we can add more flags to expand the search operation

        if (projectFlag)
            Collections.sort(tasklist, Task.ProjectNameComparator);

        if (dateFlag)
            Collections.sort(tasklist, Task.DateComparator);

        // Display the Task details in Tabular Form

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%20s %20s %20s %10s", "TASK TITLE", "PROJECT", "DATE", "STATUS");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");

        tasklist.forEach(str -> {
            System.out.format("%20s %20s %20s %10s",
                    str.getDescription(), str.getProject(), str.getTaskDueDate(), str.getStatus());
            System.out.println();
        });
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    /**
     * This method is used to call readFromTextFile method of FileHandler class
     */
    public void readFromFile() {
        FileHandler fileHandler = new FileHandler();
        tasklist = fileHandler.readFromTextFile();

    }

    /**
     * This method is used to display all the available options of this application to the user
     */
    private void printWelcome() {
        System.out.println();
        System.out.println(" >> Welcome to ToDoList Application");
        System.out.println(" >> You can maintain a ToDoList by Adding/Editing/Deleting/Sorting your Task List.");
        System.out.println();
        System.out.println(" >> Pick an Option");
        System.out.println(" >> Type 1 to Add Task");
        System.out.println(" >> Type 2 to View task sorted by Date");
        System.out.println(" >> Type 3 to View task sorted by Project");
        System.out.println(" >> Type 4 to Edit a Task");
        System.out.println(" >> Type 5 to Remove a Task");
        System.out.println(" >> Type 6 to Mark the Task Complete");
        System.out.println(" >> Type 7 to Save and Quit");

    }
}
