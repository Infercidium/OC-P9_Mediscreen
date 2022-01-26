package com.infercidium.mediscreenUI.proxy;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
}
