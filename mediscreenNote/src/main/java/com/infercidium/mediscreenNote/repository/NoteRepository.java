package com.infercidium.mediscreenNote.repository;

import com.infercidium.mediscreenNote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    /**
     * Find a list of note with same patient ID.
     * @param patId is patient ID.
     * @return noteList.
     */
    List<Note> findByPatId(int patId);
}
