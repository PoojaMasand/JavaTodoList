package com.project;

/**
 * Command Class is used for setting and getting the command word from CommandWordClass
 *
 * @author Pooja Masand
 * @version 1.0 (2020.10.08)
 */


public class Command {

    private CommandWord commandWord;

    /**
     * This is the constructor of the Command Class
     *
     * @param commandWord is the object of CommandWord Class
     */

    public Command(CommandWord commandWord) {
        this.commandWord = commandWord;

    }

    /**
     * This method is the getter for commandWord
     *
     * @return the object of CommandWord class
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }
}