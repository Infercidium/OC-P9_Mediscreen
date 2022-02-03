package com.infercidium.mediscreenNote.service;

import com.infercidium.mediscreenNote.dto.NoteDto;
import com.infercidium.mediscreenNote.interfaceService.NoteIService;
import com.infercidium.mediscreenNote.model.Note;
import com.infercidium.mediscreenNote.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService implements NoteIService {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(NoteService.class);

    /**
     * Instantiation of NoteRepository.
     */
    @Autowired
    private NoteRepository noteR;

    /**
     * Add a note in the database.
     * @param note to add.
     */
    @Override
    public void postNote(final Note note) {
        noteR.save(note);
        LOGGER.debug("note save");
    }

    /**
     * Update a note in the database.
     * @param note to update.
     * @param id   to select the note.
     */
    @Override
    public void updateNote(final Note note, final String id) {
        note.setNoteId(id);
        noteR.save(note);
        LOGGER.debug("note update");
    }

    /**
     * Find note which has given id.
     * @param id to find.
     * @return the selected note.
     */
    @Override
    public Note getNote(final String id) {
        Note note = noteR.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid note id: " + id));
        LOGGER.debug("note " + id + " found");
        return note;
    }

    /**
     * Find note which has similar patient ID.
     * @param patId is patient ID.
     * @return the list of note.
     */
    @Override
    public List<Note> getPatientNote(final int patId) {
        List<Note> noteList = noteR.findByPatId(patId);
        LOGGER.debug("note(s) found");
        return noteList;
    }

    /**
     * Removes the note which has the given id.
     * @param id to delete.
     */
    @Override
    public void deleteNote(final String id) {
        Note note = getNote(id);
        noteR.delete(note);
        LOGGER.debug("note remove");
    }

    /**
     * Delete notes related to patient with id matching patId.
     * @param patId is patient id.
     */
    @Override
    public void deleteListNote(final int patId) {
        List<Note> noteList = getPatientNote(patId);
        noteR.deleteAll(noteList);
        LOGGER.debug("notes related to the patient are deleted.");
    }

    /**
     * Allows you to transform a NoteDto into a Note.
     * @param noteDto to transform.
     * @return note result.
     */
    @Override
    public Note dtoToNote(final NoteDto noteDto) {
        String[] split = noteDto.getE().split(": ");
        List<String> stringList
                = Arrays.stream(split).collect(Collectors.toList());

        String memo = stringList.get(2);
        String patient = stringList.get(1)
                .replace(" Practitioner's notes/recommendations", "");

        Note note = new Note();
        note.setPatId(noteDto.getPatId());
        note.setMemo(memo);
        note.setPatient(patient);
        return note;
    }
}
