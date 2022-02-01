package com.infercidium.mediscreenCalcul.proxies;

import com.infercidium.mediscreenCalcul.models.Note;
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
}
