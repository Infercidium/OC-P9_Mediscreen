package com.infercidium.mediscreenNote.interfaceService;

import com.infercidium.mediscreenNote.model.Note;

import java.util.List;

public interface NoteIService {
    //Create
    /**
     * Add a note in the database.
     * @param note to add.
     */
    void postNote(Note note);

    //Update
    /**
     * Update a note in the database.
     * @param note to update.
     * @param id to select the note.
     */
    void updateNote(Note note, String id);

    //Read

    /**
     * Find note which has given id.
     * @param id to find.
     * @return the selected note.
     */
    Note getNote(String id);

    /**
     * Find note which has similar patient ID.
     * @param patId is patient ID.
     * @return the list of note.
     */
    List<Note> getPatientNote(int patId);

    //Delete
    /**
     * Removes the note which has the given id.
     * @param id to delete.
     */
    void deleteNote(String id);

    /**
     * Delete notes related to patient with id matching patId.
     * @param patId is patient id.
     */
    void deleteListNote(int patId);
}
