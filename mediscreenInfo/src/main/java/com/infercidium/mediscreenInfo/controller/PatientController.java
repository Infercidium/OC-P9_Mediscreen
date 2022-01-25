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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private PatientIService patientIService;

    /**
     * Add patient to the database.
     * @param patient to add.
     * @return the save patient.
     */
    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody final Patient patient) {
        Patient save = patientIService.postPatient(patient);
        LOGGER.info("Patient Save");
        return save;
    }

    /**
     * Update patient to the database.
     * @param id of patient.
     * @param patient with new information.
     * @return the updated patient.
     */
    @PutMapping("/patient/update/{id}")
    public Patient updatePatient(@PathVariable final int id,
                              @RequestBody final Patient patient) {
        Patient update = patientIService.updatePatient(patient, id);
        LOGGER.info("Patient Update");
        return update;
    }

    /**
     * Remove patient of the database.
     * @param id of patient.
     */
    @DeleteMapping("/patient/remove/{id}")
    public void removePatient(@PathVariable final int id) {
        patientIService.deletePatient(id);
        LOGGER.info("Patient removed");
    }

    /**
     * Return the patient with the same database id.
     * @param id of selected patient.
     * @return patient.
     */
    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable final int id) {
        Patient patient = patientIService.getPatient(id);
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
        List<Patient> patientList = patientIService.getPatient(family, given);
        LOGGER.info("Patient(s) Found");
        return patientList;
    }

    /**
     * Return the patient with the same database lastName.
     * @param family is lastName.
     * @return patientList.
     */
    @GetMapping("/patient/family/{family}")
    public List<Patient> getPatientFamily(@PathVariable final String family) {
        List<Patient> patientList = patientIService.getFamilyPatient(family);
        LOGGER.info("Patient(s) Found");
        return patientList;
    }

    /**
     * Return the patient with the same database firstName.
     * @param given is firsName.
     * @return patientList.
     */
    @GetMapping("/patient/given/{given}")
    public List<Patient> getPatientGiven(@PathVariable final String given) {
        List<Patient> patientList = patientIService.getGivenPatient(given);
        LOGGER.info("Patient(s) Found");
        return patientList;
    }

    /**
     * Return all the patients from the database.
     * @return patientList.
     */
    @GetMapping("/patient/all")
    public List<Patient> getPatientList() {
        List<Patient> patientList = patientIService.getPatientList();
        LOGGER.info("PatientList Found");
        return patientList;
    }
}
