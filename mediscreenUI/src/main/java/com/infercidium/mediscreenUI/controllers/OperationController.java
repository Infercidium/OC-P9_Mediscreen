package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.model.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OperationController {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(OperationController.class);

    /**
     * Instantiation of InfoProxy.
     */
    @Autowired
    private InfoProxy infoProxy;

    /**
     * Instantiation of InterfaceController.
     */
    @Autowired
    private InterfaceController interfaceController;

    /**
     * Returns a list of patients according to the search parameters,
     * none: the whole list,
     * family or given: all the patients with the parameter,
     * family and given: the list of all the patients with the two parameters,
     * the patient's page there is only one.
     * @param model containing the elements to display.
     * @param family of patient.
     * @param given of patient.
     * @return the patient list to display.
     */
    @GetMapping("/patientResearch")
    public String patientResearch(final Model model,
                                  final String family,
                                  final String given) {

        try {
            List<Patient> patientList;
            if (given.isEmpty() && family.isEmpty()) {
                patientList
                        = infoProxy.getAllPatient();
            } else if (given.isEmpty()) {
                patientList
                        = infoProxy.getListFamilyPatient(family);
            } else if (family.isEmpty()) {
                patientList
                        = infoProxy.getListGivenPatient(given);
            } else {
                patientList
                        = infoProxy.getPatient(family, given);
                if (patientList.size() == 1) {
                    LOGGER.debug("Patient found");
                    model.addAttribute("patient", patientList.get(0));
                    return "patient";
                }
            }
            interfaceController.setPatientList(patientList);
        } catch (WebClientResponseException e) {
            LOGGER.info("No patient found");
            return "redirect:/?noResult";
        }
        LOGGER.debug("Patient list found");
        return "redirect:/patientList";
    }

    /**
     * Request to delete a patient.
     * @param id of patient.
     * @return the deletion validation page.
     */
    @GetMapping("/patientDelete")
    public String patientDelete(@RequestParam final int id) {
        infoProxy.removePatient(id);
        LOGGER.info("Deletion of patient successful");
        return "redirect:/?successDelete";
    }

    /**
     * Verifies the patient and adds it to the database.
     * @param model containing the elements to display.
     * @param patient to add to the database.
     * @param result to error validation.
     * @return added patient page.
     */
    @PostMapping("/validatenew")
    public String validatenew(final Model model,
                              @Valid final Patient patient,
                              final BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.info("Error detected, return to form");
            return "patientnew";
        }
        Patient savePatient = infoProxy.addPatient(patient);
        model.addAttribute("patient", savePatient);
        LOGGER.info("New patient created");
        return "patient";
    }

    /**
     * Checks the patient and modifies it in the database.
     * @param model containing the elements to display.
     * @param patient to modify in the database.
     * @param result to error validation.
     * @param id of the patient to modify.
     * @return modified patient page.
     */
    @PostMapping("/validateupdate/{id}")
    public String validateUpdate(final Model model,
                                 @Valid final Patient patient,
                                 final BindingResult result,
                                 @PathVariable final int id) {
        if (result.hasErrors()) {
            patient.setPatientId(id);
            model.addAttribute("patient", patient);
            LOGGER.info("Error detected, return to form");
            return "patientupdate";
        }
        Patient updatePatient = infoProxy.updatePatient(patient, id);
        model.addAttribute("patient", updatePatient);
        LOGGER.info("Patient updated");
        return "patient";
    }
}
