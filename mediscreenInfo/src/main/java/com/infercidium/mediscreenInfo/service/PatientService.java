package com.infercidium.mediscreenInfo.service;

import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import com.infercidium.mediscreenInfo.model.Patient;
import com.infercidium.mediscreenInfo.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements PatientIService {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(PatientService.class);

    /**
     * Instantiation of PatientRepository.
     */
    @Autowired
    private PatientRepository patientR;


    /**
     * Add a patient in the database.
     * @param patient to add.
     */
    @Override
    public void postPatient(final Patient patient) {
        patientR.save(patient);
        LOGGER.debug("patient save");
    }

    /**
     * Update a patient in the database.
     * @param patient to update.
     * @param id      to select the patient.
     */
    @Override
    public void updatePatient(final Patient patient, final Integer id) {
        patient.setPatientId(id);
        patientR.save(patient);
        LOGGER.debug("patient update");
    }

    /**
     * Find patient which has the given id.
     * @param id to find.
     * @return the selected patient.
     */
    @Override
    public Patient getPatient(final int id) {
        Patient patient = patientR.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid patient id: " + id));
        LOGGER.debug("patient " + id + " found");
        return patient;
    }

    /**
     * Find all patients.
     * @return the list of patients.
     */
    @Override
    public List<Patient> getPatientList() {
        List<Patient> patientList = patientR.findAll();
        if (patientList.size() < 1) {
            throw new NullPointerException("Patient not found, empty list");
        }
        LOGGER.debug("patientList found");
        return patientList;
    }

    /**
     * Removes the patient which has the given id.
     * @param id to delete.
     */
    @Override
    public void deletePatient(final int id) {
        Patient patient = getPatient(id);
        patientR.delete(patient);
        LOGGER.debug("Patient remove");
    }
}
