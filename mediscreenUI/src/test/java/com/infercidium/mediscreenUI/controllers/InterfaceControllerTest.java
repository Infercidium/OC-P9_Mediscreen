package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.models.Note;
import com.infercidium.mediscreenUI.models.Patient;
import com.infercidium.mediscreenUI.proxies.InfoProxy;
import com.infercidium.mediscreenUI.proxies.NoteProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InterfaceController.class})
@RunWith(SpringRunner.class)
class InterfaceControllerTest {

    @MockBean
    private PaginationIService paginationIService;

    @MockBean
    private InfoProxy infoProxy;

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private Model model;

    @Autowired
    InterfaceController interfaceController;

    Patient patient = new Patient();
    List<Patient> patientList = new ArrayList<>();

    List<Note> noteList = Collections.emptyList();

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
        Mockito.when(paginationIService.patientPagination(patientList, 1)).thenReturn(patientPage);
        String result = interfaceController.allPatient(model, 1);
        assertEquals("patientlist", result);
    }

    @Test
    void patient() {
        Page<Note> notePage = new PageImpl<>(noteList);
        Mockito.when(noteProxy.getPatientNote(patient.getPatientId())).thenReturn(noteList);
        Mockito.when(paginationIService.notePagination(noteList, 1)).thenReturn(notePage);
        String result = interfaceController.patient(model, patient.getPatientId(), 1);
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