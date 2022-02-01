package com.infercidium.mediscreenUI.service;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.models.Note;
import com.infercidium.mediscreenUI.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {PaginationService.class})
@RunWith(SpringRunner.class)
class PaginationServiceTest {

    @Autowired
    private PaginationService paginationService;

    Patient patienta = new Patient();
    Patient patientZ = new Patient();
    List<Patient> patientList = new ArrayList<>();

    Note note1 = new Note();
    Note note2 = new Note();
    List<Note> noteList = new ArrayList<>();

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

        note1.setNoteId("1");
        note1.setPatient("patient1");
        note1.setPatId(1);
        note1.setMemo("Test1");
        note1.setDoctor("doc1");
        note1.setTimesheet(LocalDate.of(2000,1,1));

        note2.setNoteId("2");
        note2.setPatient("patient2");
        note2.setPatId(2);
        note2.setMemo("Test2");
        note2.setDoctor("doc2");
        note2.setTimesheet(LocalDate.of(2000,2,1));

        noteList.add(note1);
        noteList.add(note2);
    }

    @Test
    void patientPagination() {
        Page<Patient> patientPage = paginationService.patientPagination(patientList, 1);
        assertEquals(patienta, patientPage.getContent().get(0));
        assertEquals(patientList.size(), patientPage.getContent().size());
    }

    @Test
    void notePagination() {
        Page<Note> notePage = paginationService.notePagination(noteList, 1);
        assertEquals(note2, notePage.getContent().get(0));
        assertEquals(noteList.size(), notePage.getContent().size());
    }
}