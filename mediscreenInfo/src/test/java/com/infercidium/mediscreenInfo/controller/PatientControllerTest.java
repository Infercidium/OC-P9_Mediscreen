package com.infercidium.mediscreenInfo.controller;

import com.infercidium.mediscreenInfo.constant.Genres;
import com.infercidium.mediscreenInfo.constant.Result;
import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import com.infercidium.mediscreenInfo.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class PatientControllerTest {

    @MockBean
    PatientIService patientIService;

    @Autowired
    PatientController patientController;

    Patient patient = new Patient();
    Page<Patient> patientPage = new PageImpl<>(Collections.singletonList(patient));
    Pageable pageable = PageRequest.of(0, 10);

    @BeforeEach
    void setUp() {
        patient.setPatientId(1);
        patient.setGiven("firstName");
        patient.setFamily("lastName");
        patient.setSex(Genres.M);
        patient.setDob(LocalDate.of(2000,1,1));
        patient.setAddress("address");
        patient.setPhone("phone");
        //SafeTest
        patient.setResult(Result.UNKNOWN);
    }

    @Test
    void addPatient() {
        patientController.addPatient(patient);
        Mockito.verify(patientIService, Mockito.times(1)).postPatient(patient);
    }

    @Test
    void updatePatient() {
        patientController.updatePatient(1, patient);
        Mockito.verify(patientIService, Mockito.times(1)).updatePatient(patient, 1);
    }

    @Test
    void removePatient() {
        patientController.removePatient(1);
        Mockito.verify(patientIService, Mockito.times(1)).deletePatient(1);
    }

    @Test
    void getPatient() {
        Mockito.when(patientIService.getPatient(1)).thenReturn(patient);
        Patient result = patientController.getPatient(1);
        assertEquals(patient, result);
    }

    @Test
    void getPatientName() {
        Mockito.when(patientIService.getPatient("lastName", "firstName", pageable)).thenReturn(patientPage);
        Page<Patient> result = patientController.getPatientName("lastName", "firstName", pageable);
        assertEquals(patientPage, result);
    }

    @Test
    void getPatientFamily() {
        Mockito.when(patientIService.getFamilyPatient("lastName", pageable)).thenReturn(patientPage);
        Page<Patient> result = patientController.getPatientFamily("lastName", pageable);
        assertEquals(patientPage, result);
    }

    @Test
    void getPatientList() {
        Mockito.when(patientIService.getPatientList(pageable)).thenReturn(patientPage);
        Page<Patient> result = patientController.getPatientList(pageable);
        assertEquals(patientPage, result);
    }
}