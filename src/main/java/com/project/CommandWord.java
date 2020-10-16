package com.project;

/**
 * This enum class is used to store the mapping value of user input with readable String value
 *
 * @author Pooja Masand
 * @version 1.0 (2020.10.08)
 */

public enum CommandWord {

    ADD(1), SEARCH_BY_DATE(2), SEARCH_BY_PROJECT(3),
    EDIT(4), REMOVE(5), COMPLETE(6), QUIT(7),
    RELOAD(0), UNKNOWN(-1);

    private final int commandNumber;

    CommandWord(int commandNumber) {
        this.commandNumber = commandNumber;
    }

    /**
     * This method returns the integer value of the constant enum object
     *
     * @return integer value
     */
    public int getUserValue() {
        return commandNumber;
    }
}

