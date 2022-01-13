package com.infercidium.mediscreenInfo.repository;

import com.infercidium.mediscreenInfo.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    /**
     * Find a list of patient with same name in several pages.
     * @param family is lastName.
     * @param given is firstName.
     * @param pageable to make the page.
     * @return a multi-page patientList.
     */
    Page<Patient> findByFamilyIgnoreCaseAndGivenIgnoreCase(String family,
                                                           String given,
                                                           Pageable pageable);

    /**
     * Find a list of patient with same lastName in several pages.
     * @param family is lastName.
     * @param pageable to make the page.
     * @return a multi-page patientList.
     */
    Page<Patient> findByFamilyIgnoreCase(String family, Pageable pageable);
}
