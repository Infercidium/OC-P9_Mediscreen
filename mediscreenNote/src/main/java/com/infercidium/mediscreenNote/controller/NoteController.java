package com.infercidium.mediscreenNote.controller;

import com.infercidium.mediscreenNote.interfaceService.NoteIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
