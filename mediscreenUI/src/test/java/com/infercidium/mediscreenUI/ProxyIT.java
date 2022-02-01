package com.infercidium.mediscreenUI;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.constants.Result;
import com.infercidium.mediscreenUI.models.Note;
import com.infercidium.mediscreenUI.models.Patient;
import com.infercidium.mediscreenUI.proxies.AssessProxy;
import com.infercidium.mediscreenUI.proxies.InfoProxy;
import com.infercidium.mediscreenUI.proxies.NoteProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProxyIT {

    @Autowired
    private InfoProxy infoProxy;

    @Autowired
    private NoteProxy noteProxy;

    @Autowired
    private AssessProxy assessProxy;

    Patient patient = new Patient();
    Note note = new Note();

    @BeforeEach
    void setUp() {
        patient.setGiven("firstName");
        patient.setFamily("lastName");
        patient.setSex(Genres.M);
        patient.setDob(LocalDate.of(2000,1,1));

        note.setNoteId("1");
        note.setDoctor("doctor");
        note.setMemo("memo");
        note.setPatient("patient");
        note.setTimesheet(LocalDate.of(2020,1,1));
        note.setPatId(0);
    }

    @Test
    void InfoProxy() {
        Patient patientadd = infoProxy.addPatient(patient);
        assertEquals(patient.getGiven(), patientadd.getGiven());
        assertEquals(patient.getFamily(), patientadd.getFamily());
        assertEquals(patient.getSex(), patientadd.getSex());
        assertEquals(patient.getDob(), patientadd.getDob());

        patientadd.setAddress("address");
        patientadd.setPhone("phone");
        Patient patientup = infoProxy.updatePatient(patientadd, patientadd.getPatientId());
        assertEquals(patientadd.getPatientId(), patientup.getPatientId());
        assertEquals(patientadd.getGiven(), patientup.getGiven());
        assertEquals(patientadd.getFamily(), patientup.getFamily());
        assertEquals(patientadd.getSex(), patientup.getSex());
        assertEquals(patientadd.getDob(), patientup.getDob());
        assertEquals(patientadd.getAddress(), patientup.getAddress());
        assertEquals(patientadd.getPhone(), patientup.getPhone());

        List<Patient> patientListAll = infoProxy.getAllPatient();
        assertTrue(patientListAll.contains(patientup));

        List<Patient> patientListFull = infoProxy.getPatient(patient.getFamily(), patient.getGiven());
        assertTrue(patientListFull.contains(patientup));

        List<Patient> patientListFamily = infoProxy.getListFamilyPatient(patient.getFamily());
        assertTrue(patientListFamily.contains(patientup));

        List<Patient> patientListGiven = infoProxy.getListGivenPatient(patient.getGiven());
        assertTrue(patientListGiven.contains(patientup));

        Patient patientId = infoProxy.getPatientId(patientadd.getPatientId());
        assertEquals(patientId, patientup);

        infoProxy.removePatient(patientup.getPatientId());
        List<Patient> patientListEnd = infoProxy.getAllPatient();
        assertFalse(patientListEnd.contains(patientup));
    }

    @Test
    void NoteProxy() {
        noteProxy.addNote(note);
        Note noteAdd = noteProxy.getNote(note.getNoteId());
        assertEquals(note.getNoteId(), noteAdd.getNoteId());
        assertEquals(note.getPatId(), noteAdd.getPatId());
        assertEquals(note.getPatient(), noteAdd.getPatient());
        assertEquals(note.getDoctor(), noteAdd.getDoctor());
        assertEquals(note.getTimesheet(), noteAdd.getTimesheet());
        assertEquals(note.getMemo(), noteAdd.getMemo());

        noteAdd.setTimesheet(LocalDate.now());
        noteAdd.setMemo("Test2");
        noteProxy.updateNote(noteAdd, noteAdd.getNoteId());
        List<Note> noteUpList = noteProxy.getPatientNote(0);
        assertEquals(1, noteUpList.size());
        Note noteUp = noteUpList.get(0);
        assertEquals(noteAdd.getNoteId(), noteUp.getNoteId());
        assertEquals(noteAdd.getPatId(), noteUp.getPatId());
        assertEquals(noteAdd.getPatient(), noteUp.getPatient());
        assertEquals(noteAdd.getDoctor(), noteUp.getDoctor());
        assertEquals(noteAdd.getTimesheet(), noteUp.getTimesheet());
        assertEquals(noteAdd.getMemo(), noteUp.getMemo());

        noteProxy.deleteNote(noteUp.getNoteId());
        List<Note> noteVerifList = noteProxy.getPatientNote(0);
        assertEquals(0, noteVerifList.size());

        noteProxy.addNote(note);
        noteProxy.deleteNotes(0);
        List<Note> noteVerif2List = noteProxy.getPatientNote(0);
        assertEquals(0, noteVerif2List.size());
    }

    @Test
    void AssessProxy() {
        Patient patientTest = infoProxy.addPatient(patient);

        Result result = assessProxy.getPatientResult(patientTest.getPatientId());
        assertEquals(Result.None, result);

        infoProxy.removePatient(patientTest.getPatientId());
    }
}
