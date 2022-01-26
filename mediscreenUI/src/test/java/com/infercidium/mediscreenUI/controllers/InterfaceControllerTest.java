package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.model.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InterfaceController.class})
class InterfaceControllerTest {

    @MockBean
    private PaginationIService paginationIService;

    @MockBean
    private InfoProxy infoProxy;

    @MockBean
    private Model model;

    @Autowired
    InterfaceController interfaceController;

    Patient patient = new Patient();
    List<Patient> patientList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        patient.setPatientId(1);
        patient.setGiven("firstName");
        patient.setFamily("lastName");
        patient.setSex(Genres.M);
        patient.setDob(LocalDate.of(2000,1,1));
        patient.setAddress("address");
        patient.setPhone("phone");

        patientList.add(patient);

        Mockito.when(infoProxy.getPatientId(patient.getPatientId())).thenReturn(patient);
    }

    @Test
    void home() {
        String result = interfaceController.home();
        assertEquals("research", result);
    }

    @Test
    void allPatient() {
        Page<Patient> patientPage = new PageImpl<>(patientList);
        interfaceController.setPatientList(patientList);
        Mockito.when(paginationIService.pagination(patientList, 1)).thenReturn(patientPage);
        String result = interfaceController.allPatient(model, 1);
        assertEquals("patientlist", result);
    }

    @Test
    void patient() {
        String result = interfaceController.patient(model, patient.getPatientId());
        assertEquals("patient", result);
    }

    @Test
    void patientChange() {
        String result = interfaceController.patientChange(model, 0);
        String result2 = interfaceController.patientChange(model, patient.getPatientId());

        assertEquals("patientnew", result);
        assertEquals("patientupdate", result2);
    }
}