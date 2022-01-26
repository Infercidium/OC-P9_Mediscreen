package com.infercidium.mediscreenNote.controller;

import com.infercidium.mediscreenNote.interfaceService.NoteIService;
import com.infercidium.mediscreenNote.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {NoteController.class})
@RunWith(SpringRunner.class)
class NoteControllerTest {

    @MockBean
    private NoteIService noteIService;

    @Autowired
    private NoteController noteController;

    Note note = new Note();
    List<Note> noteList = Collections.singletonList(note);

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
    void addNote() {
        noteController.addNote(note);
        Mockito.verify(noteIService, Mockito.times(1)).postNote(note);
    }

    @Test
    void updateNote() {
        noteController.updateNote("1", note);
        Mockito.verify(noteIService, Mockito.times(1)).updateNote(note, "1");
    }

    @Test
    void removeNote() {
        noteController.removeNote("1");
        Mockito.verify(noteIService, Mockito.times(1)).deleteNote("1");
    }

    @Test
    void getNote() {
        Mockito.when(noteIService.getNote("1")).thenReturn(note);
        Note result = noteController.getNote("1");
        assertEquals(note, result);
    }

    @Test
    void getPatientNote() {
        Mockito.when(noteIService.getPatientNote(1)).thenReturn(noteList);
        List<Note> result = noteController.getPatientNote(1);
        assertEquals(noteList, result);
    }
}