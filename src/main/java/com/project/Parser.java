package com.project;

import java.util.Scanner;

/**
 * This parser reads user input and tries to interpret it as an command.
 * Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a one word command. It returns the command
 * as an object of class Command.
 *
 * @author Pooja Masand
 * @version 2020.10.07
 */

public class Parser {

    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * This method is used to read the user command from the terminal.
     *
     * @return the object of the Command class.
     */
    public Command getCommand() {

        int option = -1;
        String input;
        boolean notAnInteger = true;
        while (notAnInteger) {
            System.out.print(">> ");
            input = reader.next();
            try {
                option = Integer.parseInt(input);
                notAnInteger = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid Option");
            }

        }
        return new Command(commands.getCommandWord(option));
    }

}
