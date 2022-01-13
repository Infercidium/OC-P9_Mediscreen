package com.infercidium.mediscreenInfo.interfaceService;

import com.infercidium.mediscreenInfo.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientIService {
    //Create
    /**
     * Add a patient in the database.
     * @param patient to add.
     */
    void postPatient(Patient patient);

    //Update
    /**
     * Update a patient in the database.
     * @param patient to update.
     * @param id to select the patient.
     */
    void updatePatient(Patient patient, Integer id);

    //Read
    /**
     * Find patient which has the given id.
     * @param id to find.
     * @return the selected patient.
     */
    Patient getPatient(int id);

    /**
     * Find patient which has similar name.
     * @param family is lastName.
     * @param given is firstName.
     * @param pageable is current page.
     * @return the selected patient(s).
     */
    Page<Patient> getPatient(String family, String given, Pageable pageable);

    /**
     * Find all patients.
     * @param pageable is current page.
     * @return the list of patients.
     */
    Page<Patient> getPatientList(Pageable pageable);

    /**
     * Find all patients with the lastName.
     * @param family is lastName.
     * @param pageable is current page.
     * @return the list of patients.
     */
    Page<Patient> getFamilyPatient(String family, Pageable pageable);

    //Delete
    /**
     * Removes the patient which has the given id.
     * @param id to delete.
     */
    void deletePatient(int id);
}
