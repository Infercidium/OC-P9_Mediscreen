package com.infercidium.mediscreenInfo.controller;

import com.infercidium.mediscreenInfo.constants.Genres;
import com.infercidium.mediscreenInfo.constants.Result;
import com.infercidium.mediscreenInfo.interfaceService.PatientIService;
import com.infercidium.mediscreenInfo.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class PatientControllerTest {

    @MockBean
    PatientIService patientIService;

    @Autowired
    PatientController patientController;

    Patient patient = new Patient();
    List<Patient> patientList = Collections.singletonList(patient);

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
        Mockito.when(patientIService.getPatient("lastName", "firstName")).thenReturn(patientList);
        List<Patient> result = patientController.getPatientName("lastName", "firstName");
        assertEquals(patientList, result);
    }

    @Test
    void getPatientFamily() {
        Mockito.when(patientIService.getFamilyPatient("lastName")).thenReturn(patientList);
        List<Patient> result = patientController.getPatientFamily("lastName");
        assertEquals(patientList, result);
    }

    @Test
    void getPatientGiven() {
        Mockito.when(patientIService.getGivenPatient("firstName")).thenReturn(patientList);
        List<Patient> result = patientController.getPatientGiven("firstName");
        assertEquals(patientList, result);
    }

    @Test
    void getPatientList() {
        Mockito.when(patientIService.getPatientList()).thenReturn(patientList);
        List<Patient> result = patientController.getPatientList();
        assertEquals(patientList, result);
    }
}