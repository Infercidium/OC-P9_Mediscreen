package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.constants.Result;
import com.infercidium.mediscreenUI.models.Note;
import com.infercidium.mediscreenUI.models.Patient;
import com.infercidium.mediscreenUI.proxies.AssessProxy;
import com.infercidium.mediscreenUI.proxies.InfoProxy;
import com.infercidium.mediscreenUI.proxies.NoteProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {OperationController.class})
@RunWith(SpringRunner.class)
class OperationControllerTest {

    @MockBean
    private InterfaceController interfaceController;

    @MockBean
    private InfoProxy infoProxy;

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private AssessProxy assessProxy;

    @MockBean
    private Model model;

    @MockBean
    private BindingResult bindingResult;

    @Autowired
    OperationController operationController;

    Patient patient = new Patient();
    List<Patient> patientList = new ArrayList<>();

    Note note = new Note();
    List<Note> noteList = new ArrayList<>();

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

        note.setNoteId("1");
        note.setDoctor("doctor");
        note.setMemo("memo");
        note.setPatient("patient");
        note.setTimesheet(LocalDate.of(2020,1,1));
        note.setPatId(1);

        noteList.add(note);
    }

    @Test
    void patientResearch() {
        Mockito.when(infoProxy.getAllPatient()).thenReturn(patientList);
        Mockito.when(infoProxy.getListFamilyPatient(patient.getFamily())).thenReturn(patientList);
        Mockito.when(infoProxy.getListGivenPatient(patient.getGiven())).thenReturn(patientList);
        Mockito.when(infoProxy.getPatient(patient.getFamily(), patient.getGiven())).thenReturn(patientList);
        Mockito.when(infoProxy.getPatient("123", "098")).thenReturn(Collections.emptyList());

        String resultAll = operationController.patientResearch(model, "", "");
        String resultFamily = operationController.patientResearch(model, patient.getFamily(), "");
        String resultGiven = operationController.patientResearch(model, "", patient.getGiven());
        String resultName = operationController.patientResearch(model, patient.getFamily(), patient.getGiven());
        String resultError = operationController.patientResearch(model, "123", "098");

        assertEquals("redirect:/patientList", resultAll);
        assertEquals("redirect:/patientList", resultFamily);
        assertEquals("redirect:/patientList", resultGiven);
        assertEquals("redirect:/patient/1", resultName);
        assertEquals("redirect:/?noResult", resultError);

    }

    @Test
    void patientDelete() {
        String result = operationController.patientDelete(patient.getPatientId());
        assertEquals("redirect:/?successDelete", result);
    }

    @Test
    void validatenew() {
        Mockito.when(infoProxy.addPatient(patient)).thenReturn(patient);
        String result = operationController.validatenew(model, patient, bindingResult);
        assertEquals("redirect:/patient/1", result);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String resultError = operationController.validatenew(model, patient, bindingResult);
        assertEquals("patientnew", resultError);
    }

    @Test
    void validateUpdate() {
        Mockito.when(infoProxy.updatePatient(patient, patient.getPatientId())).thenReturn(patient);
        String result = operationController.validateUpdate(model, patient, bindingResult, patient.getPatientId());
        assertEquals("redirect:/patient/1", result);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String resultError = operationController.validateUpdate(model, patient, bindingResult, patient.getPatientId());
        assertEquals("patientupdate", resultError);
    }

    @Test
    void validateNewNote() {
        String result = operationController.validateNewNote(note, bindingResult, note.getPatId());
        assertEquals("redirect:/patient/" + note.getPatId(), result);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String resultError = operationController.validateNewNote(note, bindingResult, note.getPatId());
        assertEquals("redirect:/patient/" + note.getPatId() + "?errorNote", resultError);
    }

    @Test
    void validateUpdateNote() {
        String result = operationController.validateUpdateNote(note, bindingResult, note.getPatId(), note.getNoteId());
        assertEquals("redirect:/patient/" + note.getPatId(), result);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String resultError = operationController.validateUpdateNote(note, bindingResult, note.getPatId(), note.getNoteId());
        assertEquals("redirect:/patient/" + note.getPatId() + "?errorNote", resultError);
    }

    @Test
    void noteDelete() {
        Mockito.when(noteProxy.getNote(note.getNoteId())).thenReturn(note);
        String result = operationController.noteDelete(note.getNoteId());
        assertEquals("redirect:/patient/" + note.getPatId() + "?successDelete", result);
    }

    @Test
    void assessPatient() {
        Mockito.when(assessProxy.getPatientResult(patient.getPatientId())).thenReturn(Result.None);
        String result = operationController.assessPatient(patient.getPatientId());
        assertEquals("redirect:/patient/" + patient.getPatientId() + "?" + Result.None.name(), result);
    }
}