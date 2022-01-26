package com.infercidium.mediscreenNote.service;

import com.infercidium.mediscreenNote.model.Note;
import com.infercidium.mediscreenNote.repository.NoteRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {NoteService.class})
@RunWith(SpringRunner.class)
class NoteServiceTest {

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

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

        Mockito.when(noteRepository.findById("1")).thenReturn(Optional.ofNullable(note));
    }

    @Test
    void postNote() {
        noteService.postNote(note);
        Mockito.verify(noteRepository, Mockito.times(1)).save(note);
    }

    @Test
    void updateNote() {
        noteService.updateNote(note, "1");
        Mockito.verify(noteRepository, Mockito.times(1)).save(note);
    }

    @Test
    void getNote() {
        Note result = noteService.getNote("1");
        assertEquals(note, result);
    }

    @Test
    void getPatientNote() {
        Mockito.when(noteRepository.findByPatId(1)).thenReturn(noteList);
        List<Note> result = noteService.getPatientNote(1);
        assertEquals(noteList, result);
    }

    @Test
    void deleteNote() {
        noteService.deleteNote("1");
        Mockito.verify(noteRepository, Mockito.times(1)).delete(note);
    }
}