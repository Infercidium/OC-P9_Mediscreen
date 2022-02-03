package com.infercidium.mediscreenNote.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteDtoTest {

    private NoteDto noteDto = new NoteDto();

    @BeforeEach
    void setUp() {
        noteDto.setPatId(1);
        noteDto.setE("Test");
    }

    @Test
    void getPatId() {
        assertEquals(1, noteDto.getPatId());
    }

    @Test
    void getE() {
        assertEquals("Test", noteDto.getE());
    }

    @Test
    void setPatId() {
        noteDto.setPatId(2);
        assertEquals(2, noteDto.getPatId());
    }

    @Test
    void setE() {
        noteDto.setE("Test2");
        assertEquals("Test2", noteDto.getE());
    }
}