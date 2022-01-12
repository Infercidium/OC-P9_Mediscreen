package com.infercidium.mediscreenInfo.controller;

import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(PatientController.class);

    /**
     * Instantiation of PatientInterfaceService.
     */
    @Autowired
    private PatientIService patientS;

    @GetMapping("/")
    public String test() { //TODO a effacer
        return "test";
    }
}
