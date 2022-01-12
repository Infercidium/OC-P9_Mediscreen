package com.infercidium.mediscreenInfo.interfaceService;

import com.infercidium.mediscreenInfo.model.Patient;

import java.util.List;

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
     * Find all patients.
     * @return the list of patients.
     */
    List<Patient> getPatientList();

    //Delete
    /**
     * Removes the patient which has the given id.
     * @param id to delete.
     */
    void deletePatient(int id);
}
