package com.infercidium.mediscreenInfo.controller;

import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import com.infercidium.mediscreenInfo.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    @PostMapping("/update{id}")
    public void updatePatient(@PathVariable final int id,
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
    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable final int id) {
        Patient patient = patientS.getPatient(id);
        LOGGER.info("Patient Found");
        return patient;
    }

    /**
     * Return the patient with the same database name.
     * @param family is lastName.
     * @param given is firstName.
     * @return patientList.
     */
    @GetMapping("/patient/{family}/{given}")
    public List<Patient> getPatientName(@PathVariable final String family,
                                        @PathVariable final String given) {
        List<Patient> patientList = patientS.getPatient(family, given);
        LOGGER.info("Patient(s) Found");
        return patientList;
    }

    /**
     * Return the patient with the same database lastName.
     * @param family is lastName.
     * @return patientList.
     */
    @GetMapping("/family/{family}")
    public List<Patient> getPatientFamily(@PathVariable final String family) {
        List<Patient> patientList = patientS.getFamilyPatient(family);
        LOGGER.info("Patient(s) Found");
        return patientList;
    }

    /**
     * Return all the patients from the database.
     * @return patientList.
     */
    @GetMapping("/all")
    public List<Patient> getPatientList() {
        List<Patient> patientList = patientS.getPatientList();
        LOGGER.info("PatientList Found");
        return patientList;
    }
}
