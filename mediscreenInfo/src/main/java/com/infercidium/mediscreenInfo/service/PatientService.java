package com.infercidium.mediscreenInfo.service;

import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import com.infercidium.mediscreenInfo.model.Patient;
import com.infercidium.mediscreenInfo.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
     * Find patient which has similar name.
     * @param family is lastName.
     * @param given  is firstName.
     * @param pageable is current page.
     * @return the selected patient.
     */
    @Override
    public Page<Patient> getPatient(final String family, final String given,
                                    final Pageable pageable) {
        Page<Patient> patientPage
                = patientR.findByFamilyIgnoreCaseAndGivenIgnoreCase(
                        family, given, pageable);
        if (patientPage.isEmpty()) {
            throw new NullPointerException("Patient not found, empty list");
        }
        LOGGER.debug("patient(s) found");
        return patientPage;
    }

    /**
     * Find all patients.
     * @param pageable is current page.
     * @return the list of patients.
     */
    @Override
    public Page<Patient> getPatientList(final Pageable pageable) {
        Page<Patient> patientPage = patientR.findAll(pageable);
        if (patientPage.isEmpty()) {
            throw new NullPointerException("Patient not found, empty list");
        }
        LOGGER.debug("patientList found");
        return patientPage;
    }

    /**
     * Find all patients with the lastName.
     * @param family is lastName.
     * @param pageable is current page.
     * @return the list of patients.
     */
    @Override
    public Page<Patient> getFamilyPatient(final String family,
                                          final Pageable pageable) {
        Page<Patient> patientPage
                = patientR.findByFamilyIgnoreCase(family, pageable);
        if (patientPage.isEmpty()) {
            throw new NullPointerException("Patient not found, empty list");
        }
        LOGGER.debug("patient(s) found");
        return patientPage;
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
