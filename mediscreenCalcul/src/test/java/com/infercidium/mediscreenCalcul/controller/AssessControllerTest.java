package com.infercidium.mediscreenCalcul.controller;

import com.infercidium.mediscreenCalcul.constants.Genres;
import com.infercidium.mediscreenCalcul.constants.Result;
import com.infercidium.mediscreenCalcul.interfaceService.AssessIService;
import com.infercidium.mediscreenCalcul.models.Note;
import com.infercidium.mediscreenCalcul.models.Patient;
import com.infercidium.mediscreenCalcul.proxies.InfoProxy;
import com.infercidium.mediscreenCalcul.proxies.NoteProxy;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {AssessController.class})
@RunWith(SpringRunner.class)
class AssessControllerTest {

    @MockBean
    private InfoProxy infoProxy;

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private AssessIService assessIService;

    @Autowired
    private AssessController assessController;

    Patient patient = new Patient();
    Note note = new Note();

    @BeforeEach
    void setUp() {
        note.setNoteId("1");
        note.setDoctor("doctor");
        note.setMemo("memo");
        note.setPatient("patient");
        note.setTimesheet(LocalDate.of(2020,1,1));
        note.setPatId(1);

        patient.setPatientId(1);
        patient.setGiven("firstName");
        patient.setFamily("lastName");
        patient.setSex(Genres.M);
        patient.setDob(LocalDate.now().minusYears(52));
        patient.setAddress("address");
        patient.setPhone("phone");
        patient.setResult(Result.Unknown);

        Mockito.when(infoProxy.getPatientId(patient.getPatientId())).thenReturn(patient);
        Mockito.when(infoProxy.getListFamilyPatient(patient.getFamily())).thenReturn(Collections.singletonList(patient));
        Mockito.when(noteProxy.getPatientNote(patient.getPatientId())).thenReturn(Collections.singletonList(note));
        Mockito.when(assessIService.assess(patient, Collections.singletonList(note))).thenReturn(Result.None);
    }

    @Test
    void getPatientAssess() {
        Result result = assessController.getPatientAssess(patient.getPatientId());
        assertEquals(Result.None, result);
    }

    @Test
    void getPatientAssessURL() {
        String resultNone = assessController.getPatientAssessURL(patient.getPatientId());
        assertEquals("Patient: firstName lastName (age 52) diabetes assessment is: None", resultNone);

        Mockito.when(assessIService.assess(patient, Collections.singletonList(note))).thenReturn(Result.Danger);
        String resultDanger = assessController.getPatientAssessURL(patient.getPatientId());
        assertEquals("Patient: firstName lastName (age 52) diabetes assessment is: In danger", resultDanger);

        Mockito.when(assessIService.assess(patient, Collections.singletonList(note))).thenReturn(Result.Early);
        String resultEarly = assessController.getPatientAssessURL(patient.getPatientId());
        assertEquals("Patient: firstName lastName (age 52) diabetes assessment is: Early onset", resultEarly);
    }

    @Test
    void getPatientAssessFamilyURL() {
        String resultNone = assessController.getPatientAssessFamilyURL(patient.getFamily());
        assertEquals("Patient: firstName lastName (age 52) diabetes assessment is: None", resultNone);

        Mockito.when(assessIService.assess(patient, Collections.singletonList(note))).thenReturn(Result.Danger);
        String resultDanger = assessController.getPatientAssessFamilyURL(patient.getFamily());
        assertEquals("Patient: firstName lastName (age 52) diabetes assessment is: In danger", resultDanger);

        Mockito.when(assessIService.assess(patient, Collections.singletonList(note))).thenReturn(Result.Early);
        String resultEarly = assessController.getPatientAssessFamilyURL(patient.getFamily());
        assertEquals("Patient: firstName lastName (age 52) diabetes assessment is: Early onset", resultEarly);
    }
}