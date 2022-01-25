package com.infercidium.mediscreenNote.repository;

import com.infercidium.mediscreenNote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, Integer> {
}
