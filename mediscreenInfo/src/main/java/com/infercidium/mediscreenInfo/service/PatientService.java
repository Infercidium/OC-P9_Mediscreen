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
     * @return the save patient.
     */
    @Override
    public Patient postPatient(final Patient patient) {
        Patient save = patientR.save(patient);
        LOGGER.debug("patient save");
        return save;
    }

    /**
     * Update a patient in the database.
     * @param patient to update.
     * @param id to select the patient.
     * @return the updated patient.
     */
    @Override
    public Patient updatePatient(final Patient patient, final Integer id) {
        patient.setPatientId(id);
        Patient save = patientR.save(patient);
        LOGGER.debug("patient update");
        return save;
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
     * @return the selected patient.
     */
    @Override
    public List<Patient> getPatient(final String family, final String given) {
        List<Patient> patientList
                = patientR.findByFamilyIgnoreCaseAndGivenIgnoreCase(
                        family, given);
        LOGGER.debug("patient(s) found");
        return patientList;
    }

    /**
     * Find all patients.
     * @return the list of patients.
     */
    @Override
    public List<Patient> getPatientList() {
        List<Patient> patientList = patientR.findAll();
        LOGGER.debug("patientList found");
        return patientList;
    }

    /**
     * Find all patients with the lastName.
     * @param family is lastName.
     * @return the list of patients.
     */
    @Override
    public List<Patient> getFamilyPatient(final String family) {
        List<Patient> patientList
                = patientR.findByFamilyIgnoreCase(family);
        LOGGER.debug("patient(s) found");
        return patientList;
    }

    /**
     * Find all patients with the lastName.
     * @param given is lastName.
     * @return the list of patients.
     */
    @Override
    public List<Patient> getGivenPatient(final String given) {
        List<Patient> patientList
                = patientR.findByGivenIgnoreCase(given);
        LOGGER.debug("patient(s) found");
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
        LOGGER.debug("patient remove");
    }
}
