package com.infercidium.mediscreenUI.proxies;

import com.infercidium.mediscreenUI.models.Note;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@NoArgsConstructor
public class NoteProxy {

    /**
     * Instantiates the mediscreen-note API url.
     */
    @Value("${note.url}")
    private String noteUrlBase;

    /**
     * Instantiates the WebClient.
     */
    @Autowired
    private WebClient noteClient;

    /**
     * Sends a request for note with the same patient id.
     * @param patId is patient id.
     * @return note list.
     */
    public List<Note> getPatientNote(final int patId) {
        return noteClient.get().uri("/patHistory/patient/{id}", patId)
                .retrieve().bodyToFlux(Note.class).collectList().block();
    }

    /**
     * Sends the id of the requested note.
     * @param id of the note.
     * @return selected note.
     */
    public Note getNote(final String id) {
        return noteClient.get().uri("/patHistory/{id}", id)
                .retrieve().bodyToMono(Note.class).block();
    }

    /**
     * Sends the note to be added.
     * @param note to add.
     * @return NOTHING.
     */
    public Void addNote(final Note note) {
        return noteClient.post().uri("/patHistory/add").bodyValue(note)
                .retrieve().bodyToMono(Void.class).block();
        //TODO A VOIR (Pas Satisfaisant)
    }

    /**
     * Sends the note annd the note id to modify.
     * @param note to modify.
     * @param id of the note to modify.
     * @return NOTHING.
     */
    public Void updateNote(final Note note, final String id) {
        return noteClient.put().uri("/patHistory/update/{id}", id)
                .bodyValue(note)
                .retrieve().bodyToMono(Void.class).block();
        //TODO A VOIR (Pas Satisfaisant)
    }

    /**
     * Sends the id of the note to be deleted.
     * @param id of the note.
     * @return NOTHING.
     */
    public Void deleteNote(final String id) {
        return noteClient.delete().uri("/patHistory/remove/{id}", id)
                .retrieve().bodyToMono(Void.class).block();
        //TODO A VOIR (Pas Satisfaisant)
    }

    /**
     * Sends the patient id of note list to be deleted.
     * @param patId is patient id.
     * @return NOTHING.
     */
    public Void deleteNotes(final int patId) {
        return noteClient.delete().uri("/patHistory/removes/{patId}", patId)
                .retrieve().bodyToMono(Void.class).block();
        //TODO A VOIR (Pas Satisfaisant)
    }
}
