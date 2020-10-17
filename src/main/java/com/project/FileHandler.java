package com.project;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is used to read and write arraylist of ToDoTaskManager objects to a text file "TodoList.txt"
 *
 * @author Pooja Masand
 * @version 2020.10.07
 */
public class FileHandler {

    private final String path = "src/main/resources/todoList.txt";

    /**
     * Read lines from text file in object form.
     *
     * @return ArrayList of ToDoTaskManager objects
     */
    public ArrayList<Task> readFromTextFile() {
        ArrayList<Task> taskListFromFile = new ArrayList<>();
        try {

            FileInputStream file = new FileInputStream(path);
            ObjectInputStream stream = new ObjectInputStream(file);

            // read thing from the stream
            taskListFromFile = (ArrayList<Task>) stream.readObject();

            stream.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Unable to open the file " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Problem in reading the file " + e);
        }
        return taskListFromFile;
    }

    /**
     * Save data in object form in text file.
     *
     * @param taskList containing objects of ToDoTaskManager class
     */
    public void saveToTextFile(ArrayList<Task> taskList) {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(file);

            // writes objects to output stream
            output.writeObject(taskList);
            output.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File doesn't found " + e);
        }
    }
}
