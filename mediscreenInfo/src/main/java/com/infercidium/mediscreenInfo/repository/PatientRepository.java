package com.infercidium.mediscreenInfo.repository;

import com.infercidium.mediscreenInfo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    /**
     * Find a list of patient with same name in several pages.
     * @param family is lastName.
     * @param given is firstName.
     * @return a multi-page patientList.
     */
    List<Patient> findByFamilyIgnoreCaseAndGivenIgnoreCase(String family,
                                                           String given);

    /**
     * Find a list of patient with same lastName in several pages.
     * @param family is lastName.
     * @return a multi-page patientList.
     */
    List<Patient> findByFamilyIgnoreCase(String family);

    /**
     * Find a list of patient with same lastName in several pages.
     * @param given is firstName.
     * @return a multi-page patientList.
     */
    List<Patient> findByGivenIgnoreCase(String given);
}
