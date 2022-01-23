package com.infercidium.mediscreenUI.service;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {PaginationService.class})
class PaginationServiceTest {

    @Autowired
    private PaginationService paginationService;

    Patient patienta = new Patient();
    Patient patientZ = new Patient();
    List<Patient> patientList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        patienta.setPatientId(1);
        patienta.setGiven("afirstName");
        patienta.setFamily("alastName");
        patienta.setSex(Genres.M);
        patienta.setDob(LocalDate.of(2000,1,1));

        patientZ.setPatientId(1);
        patientZ.setGiven("ZfirstName");
        patientZ.setFamily("ZlastName");
        patientZ.setSex(Genres.M);
        patientZ.setDob(LocalDate.of(2000,1,1));

        patientList.add(patientZ);
        patientList.add(patienta);
    }

    @Test
    void pagination() {
        Page<Patient> patientPage = paginationService.pagination(patientList, 1);
        assertEquals(patienta, patientPage.getContent().get(0));
        assertEquals(patientList.size(), patientPage.getContent().size());
    }
}