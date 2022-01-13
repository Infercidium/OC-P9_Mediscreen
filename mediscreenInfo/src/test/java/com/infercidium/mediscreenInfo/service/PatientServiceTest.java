package com.infercidium.mediscreenInfo.service;

import com.infercidium.mediscreenInfo.constant.Genres;
import com.infercidium.mediscreenInfo.constant.Result;
import com.infercidium.mediscreenInfo.model.Patient;
import com.infercidium.mediscreenInfo.repository.PatientRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {PatientService.class})
@RunWith(SpringRunner.class)
class PatientServiceTest {

    @MockBean
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    Patient patient = new Patient();
    Page<Patient> patientPage = new PageImpl<>(Collections.singletonList(patient));

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
        //Mock
        Mockito.when(patientRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(patient));
    }

    @Test
    void postPatient() {
        patientService.postPatient(patient);
        Mockito.verify(patientRepository, Mockito.times(1)).save(patient);
    }

    @Test
    void updatePatient() {
        patientService.updatePatient(patient, 1);
        Mockito.verify(patientRepository,Mockito.times(1)).save(patient);
    }

    @Test
    void getPatient() {
        Patient result = patientService.getPatient(1);
        assertEquals(patient, result);
    }

    @Test
    void testGetPatient() {
        Mockito.when(patientRepository.findByFamilyIgnoreCaseAndGivenIgnoreCase("lastName", "firstName", PageRequest.of(0, 10))).thenReturn(patientPage);
        Page<Patient> result = patientService.getPatient("lastName", "firstName", PageRequest.of(0, 10));
        assertEquals(patientPage, result);
    }

    @Test
    void getPatientList() {
        Mockito.when(patientRepository.findAll( PageRequest.of(0, 10))).thenReturn(patientPage);
        Page<Patient> result = patientService.getPatientList(PageRequest.of(0, 10));
        assertEquals(patientPage, result);
    }

    @Test
    void getFamilyPatient() {
        Mockito.when(patientRepository.findByFamilyIgnoreCase("lastName", PageRequest.of(0, 10))).thenReturn(patientPage);
        Page<Patient> result = patientService.getFamilyPatient("lastName", PageRequest.of(0, 10));
        assertEquals(patientPage, result);
    }

    @Test
    void deletePatient() {
        patientService.deletePatient(patient.getPatientId());
        Mockito.verify(patientRepository, Mockito.times(1)).delete(patient);
    }
}