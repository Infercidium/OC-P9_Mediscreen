package com.infercidium.mediscreenNote.service;

import com.infercidium.mediscreenNote.interfaceService.NoteIService;
import com.infercidium.mediscreenNote.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private NoteRepository noteRepository;
}
