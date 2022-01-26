package com.infercidium.mediscreenNote.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoteTest {

    Note note = new Note();

    @BeforeEach
    void setUp() {
        note.setNoteId("1");
        note.setDoctor("doctor");
        note.setMemo("memo");
        note.setPatient("patient");
        note.setTimesheet(LocalDate.of(2020,1,1));
        note.setPatId(1);
    }

    @Test
    void testToString() {
        String result = note.toString();
        assertEquals("Note {noteId = 1, timesheet = 2020-01-01, patId = 1, patient = 'patient', memo = 'memo', doctor = 'doctor'}", result);
    }

    @Test
    void testEquals() {
        Note test = new Note();
        test.setNoteId("1");
        assertTrue(note.equals(test));
    }

    @Test
    void testHashCode() {
        assertEquals(80, note.hashCode());
    }

    @Test
    void getNoteId() {
        assertEquals("1", note.getNoteId());
    }

    @Test
    void getTimesheet() {
        assertEquals(LocalDate.of(2020,1,1), note.getTimesheet());
    }

    @Test
    void getPatId() {
        assertEquals(1, note.getPatId());
    }

    @Test
    void getPatient() {
        assertEquals("patient", note.getPatient());
    }

    @Test
    void getMemo() {
        assertEquals("memo", note.getMemo());
    }

    @Test
    void getDoctor() {
        assertEquals("doctor", note.getDoctor());
    }

    @Test
    void setNoteId() {
        note.setNoteId("2");
        assertEquals("2", note.getNoteId());
    }

    @Test
    void setTimesheet() {
        note.setTimesheet(LocalDate.of(2022,1,1));
        assertEquals(LocalDate.of(2022,1,1), note.getTimesheet());
    }

    @Test
    void setPatId() {
        note.setPatId(2);
        assertEquals(2, note.getPatId());
    }

    @Test
    void setPatient() {
        note.setPatient("patientTest");
        assertEquals("patientTest", note.getPatient());
    }

    @Test
    void setMemo() {
        note.setMemo("memoTest");
        assertEquals("memoTest", note.getMemo());
    }

    @Test
    void setDoctor() {
        note.setDoctor("doctorTest");
        assertEquals("doctorTest", note.getDoctor());
    }
}