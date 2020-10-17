package com.project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class holds an enumeration of all command words known in the application.
 * It is used to recognise commands as they are typed in.
 *
 * @author Pooja Masand
 * @version 2020.10.07
 */

public class CommandWords {
    // A mapping between a command word and the CommandWord
    // associated with it.
    private final HashMap<Integer, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        Arrays.stream(CommandWord.values())
                .forEachOrdered(command -> validCommands.put(command.getUserValue(), command));
    }

    /**
     * Find the CommandWord associated with a command word.
     *
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     * if it is not a valid command word.
     */
    public CommandWord getCommandWord(int commandWord) {
        CommandWord command = validCommands.get(commandWord);

        return Objects.requireNonNullElse(command, CommandWord.UNKNOWN);
    }
}

