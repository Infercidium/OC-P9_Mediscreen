package com.infercidium.mediscreenInfo.controller;

import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import com.infercidium.mediscreenInfo.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "/patient")
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

    /**
     * Add patient to the database.
     * @param patient to add.
     */
    @PostMapping("/add")
    public void addPatient(@RequestParam final Patient patient) {
        patientS.postPatient(patient);
        LOGGER.info("Patient Save");
    }

    /**
     * Update patient to the database.
     * @param id of patient.
     * @param patient with new information.
     */
    @PostMapping("/update")
    public void updatePatient(@RequestParam final int id,
                              @RequestParam final Patient patient) {
        patientS.updatePatient(patient, id);
        LOGGER.info("Patient Update");
    }

    /**
     * Remove patient of the database.
     * @param id of patient.
     */
    @DeleteMapping("/remove")
    public void removePatient(@RequestParam final int id) {
        patientS.deletePatient(id);
        LOGGER.info("Patient removed");
    }

    /**
     * Return the patient with the same database id.
     * @param id of selected patient.
     * @return patient.
     */
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable final int id) {
        Patient patient = patientS.getPatient(id);
        LOGGER.info("Patient Found");
        return patient;
    }

    /**
     * Return the patient with the same database name.
     * @param family is lastName.
     * @param given is firstName.
     * @param page is current page.
     * @return patientList.
     */
    @GetMapping("/name")
    public Page<Patient> getPatientName(@RequestParam final String family,
                                        @RequestParam final String given,
                                        @RequestParam final Pageable page) {
        Page<Patient> patientPage = patientS.getPatient(family, given, page);
        LOGGER.info("Patient(s) Found");
        return patientPage;
    }

    /**
     * Return the patient with the same database lastName.
     * @param family is lastName.
     * @param page is current page.
     * @return patientList.
     */
    @GetMapping("/family")
    public Page<Patient> getPatientFamily(@RequestParam final String family,
                                          @RequestParam final Pageable page) {
        Page<Patient> patientPage = patientS.getFamilyPatient(family, page);
        LOGGER.info("Patient(s) Found");
        return patientPage;
    }

    /**
     * Return all the patients from the database.
     * @param page is current page.
     * @return patientList.
     */
    @GetMapping("/all")
    public Page<Patient> getPatientList(@RequestParam final Pageable page) {
        Page<Patient> patientPage = patientS.getPatientList(page);
        LOGGER.info("PatientList Found");
        return patientPage;
    }
}
