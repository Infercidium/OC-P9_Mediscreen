package com.infercidium.mediscreenInfo.interfaceService;

import com.infercidium.mediscreenInfo.model.Patient;

import java.util.List;

public interface PatientIService {
    //Create
    /**
     * Add a patient in the database.
     * @param patient to add.
     * @return the save patient.
     */
    Patient postPatient(Patient patient);

    //Update
    /**
     * Update a patient in the database.
     * @param patient to update.
     * @param id to select the patient.
     * @return the updated patient.
     */
    Patient updatePatient(Patient patient, Integer id);

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
     * @return the selected patient(s).
     */
    List<Patient> getPatient(String family, String given);

    /**
     * Find all patients.
     * @return the list of patients.
     */
    List<Patient> getPatientList();

    /**
     * Find all patients with the lastName.
     * @param family is lastName.
     * @return the list of patients.
     */
    List<Patient> getFamilyPatient(String family);

    /**
     * Find all patients with the lastName.
     * @param given is lastName.
     * @return the list of patients.
     */
    List<Patient> getGivenPatient(String given);

    //Delete
    /**
     * Removes the patient which has the given id.
     * @param id to delete.
     */
    void deletePatient(int id);
}
