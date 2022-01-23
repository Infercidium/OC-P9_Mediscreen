package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.model.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {OperationController.class})
class OperationControllerTest {

    @MockBean
    private InterfaceController interfaceController;

    @MockBean
    private InfoProxy infoProxy;

    @MockBean
    private Model model;

    @MockBean
    private BindingResult bindingResult;

    @Autowired
    OperationController operationController;

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
    }

    @Test
    void patientResearch() {
        Mockito.when(infoProxy.getAllPatient()).thenReturn(patientList);
        Mockito.when(infoProxy.getListFamilyPatient(patient.getFamily())).thenReturn(patientList);
        Mockito.when(infoProxy.getListGivenPatient(patient.getGiven())).thenReturn(patientList);
        Mockito.when(infoProxy.getPatient(patient.getFamily(), patient.getGiven())).thenReturn(patientList);
        Mockito.when(infoProxy.getPatient("123", "098")).thenThrow(WebClientResponseException.class);

        String resultAll = operationController.patientResearch(model, "", "");
        String resultFamily = operationController.patientResearch(model, patient.getFamily(), "");
        String resultGiven = operationController.patientResearch(model, "", patient.getGiven());
        String resultName = operationController.patientResearch(model, patient.getFamily(), patient.getGiven());
        String resultError = operationController.patientResearch(model, "123", "098");

        assertEquals("redirect:/patientList", resultAll);
        assertEquals("redirect:/patientList", resultFamily);
        assertEquals("redirect:/patientList", resultGiven);
        assertEquals("patient", resultName);
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
        assertEquals("patient", result);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String resultError = operationController.validatenew(model, patient, bindingResult);
        assertEquals("patientnew", resultError);
    }

    @Test
    void validateUpdate() {
        Mockito.when(infoProxy.updatePatient(patient, patient.getPatientId())).thenReturn(patient);
        String result = operationController.validateUpdate(model, patient, bindingResult, patient.getPatientId());
        assertEquals("patient", result);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String resultError = operationController.validateUpdate(model, patient, bindingResult, patient.getPatientId());
        assertEquals("patientupdate", resultError);
    }
}