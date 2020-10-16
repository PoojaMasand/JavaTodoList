package com.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandWordsTest {

    @Test
    void handleUnknownInput()
    {
        CommandWords words = new CommandWords();
        assertEquals(CommandWord.UNKNOWN,words.getCommandWord(-1));
    }

    @Test
    void handleKnownInput()
    {
        CommandWords words = new CommandWords();
        assertEquals(CommandWord.ADD,words.getCommandWord(1));
    }


}