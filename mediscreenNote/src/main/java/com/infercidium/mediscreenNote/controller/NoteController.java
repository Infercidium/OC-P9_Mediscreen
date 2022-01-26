package com.infercidium.mediscreenNote.controller;

import com.infercidium.mediscreenNote.interfaceService.NoteIService;
import com.infercidium.mediscreenNote.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(NoteController.class);

    /**
     * Instantiation of NoteInterfaceService.
     */
    @Autowired
    private NoteIService noteIService;

    /**
     * Add note to the database.
     * @param note to add.
     */
    @PostMapping("/patHistory/add")
    public void addNote(@RequestBody final Note note) {
        noteIService.postNote(note);
        LOGGER.info("Note Save");
    }

    /**
     * Update note to the database.
     * @param id of note.
     * @param note with new information.
     */
    @PutMapping("/patHistory/update/{id}")
    public void updateNote(@PathVariable final String id,
                           @RequestBody final Note note) {
        noteIService.updateNote(note, id);
        LOGGER.info("Note Update");
    }

    /**
     * Remove note of the database.
     * @param id of note.
     */
    @DeleteMapping("/patHistory/remove/{id}")
    public void removeNote(@PathVariable final String id) {
        noteIService.deleteNote(id);
        LOGGER.info("Note Removed");
    }

    /**
     * Return the note with the same database id.
     * @param id of selected note.
     * @return note.
     */
    @GetMapping("/patHistory/{id}")
    public Note getNote(@PathVariable final String id) {
        Note note = noteIService.getNote(id);
        LOGGER.info("Note Found");
        return note;
    }

    /**
     * Return the note with the same database patient ID.
     * @param id of patient.
     * @return noteList.
     */
    @GetMapping("patHistory/patient/{id}")
    public List<Note> getPatientNote(@PathVariable final int id) {
        List<Note> noteList = noteIService.getPatientNote(id);
        LOGGER.info("Note(s) Found");
        return noteList;
    }
}
