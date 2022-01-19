package com.infercidium.mediscreenUI.controller;

import com.infercidium.mediscreenUI.models.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Controller
public class OperationController {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(OperationController.class);
    @Autowired
    private InfoProxy infoProxy;

    @Autowired
    private InterfaceController interfaceController;

    @GetMapping("/patientResearch")
    public String patientResearch(final String family, final String given) {

        try {
            if (given.isEmpty() && family.isEmpty()) {
                List<Patient> patientList = infoProxy.getAllPatient();
                interfaceController.setPatientList(patientList);
            } else if (given.isEmpty()) {
                List<Patient> patientList = infoProxy.getListPatient(family);
                interfaceController.setPatientList(patientList);
            } else if (family.isEmpty()) {
                return "redirect:/?errorParam";
            } else {
                List<Patient> patientList = infoProxy.getPatient(family, given);
                if (patientList.size() == 1) {
                    interfaceController.setPatient(patientList.get(0));
                    return "redirect:/patient";
                }
                interfaceController.setPatientList(patientList);
            }
        } catch (WebClientResponseException e) {
            return "redirect:/?noResult";
        }
        return "redirect:/patientList";
    }

    @GetMapping("/patientChosen/{id}")
    public String patientChosen(@PathVariable final int id) {
        Patient patient = infoProxy.getPatientId(id);
        interfaceController.setPatient(patient);
        return "redirect:/patient";
    }

    @PostMapping("/validateupdate/{id}")
    public String validateUpdate(@PathVariable final int id) {
        return "redirect:/patient";
    }
}
