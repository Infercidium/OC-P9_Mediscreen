package com.infercidium.mediscreenCalcul.service;

import com.infercidium.mediscreenCalcul.constants.Assess;
import com.infercidium.mediscreenCalcul.constants.Genres;
import com.infercidium.mediscreenCalcul.constants.Result;
import com.infercidium.mediscreenCalcul.models.Note;
import com.infercidium.mediscreenCalcul.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {AssessService.class})
@RunWith(SpringRunner.class)
class AssessServiceTest {

    @Autowired
    AssessService assessService;

    Patient patient30 = new Patient();
    Patient patientM = new Patient();
    Patient patientF = new Patient();

    Note note = new Note();

    @BeforeEach
    void setUp() {
        note.setNoteId("1");
        note.setDoctor("doctor");
        note.setMemo("memo");
        note.setPatient("patient");
        note.setTimesheet(LocalDate.of(2020,1,1));
        note.setPatId(1);

        patient30.setPatientId(1);
        patient30.setGiven("firstName");
        patient30.setFamily("lastName");
        patient30.setSex(Genres.M);
        patient30.setDob(LocalDate.of(2000,1,1).minusYears(Assess.AGE_MAX));
        patient30.setAddress("address");
        patient30.setPhone("phone");
        patient30.setResult(Result.Unknown);

        patientM.setPatientId(2);
        patientM.setGiven("firstName");
        patientM.setFamily("lastName");
        patientM.setSex(Genres.M);
        patientM.setDob(LocalDate.now().minusYears(20));
        patientM.setAddress("address");
        patientM.setPhone("phone");
        patientM.setResult(Result.Unknown);

        patientF.setPatientId(3);
        patientF.setGiven("firstName");
        patientF.setFamily("lastName");
        patientF.setSex(Genres.F);
        patientF.setDob(LocalDate.now().minusYears(20));
        patientF.setAddress("address");
        patientF.setPhone("phone");
        patientF.setResult(Result.Unknown);
    }

    @Test
    void assess() {
        Result result0 = assessService.assess(patient30, Collections.singletonList(note));
        assertEquals(Result.None, result0);

        note.setMemo(note.getMemo().concat(" poids taille"));
        Result result2 = assessService.assess(patient30, Collections.singletonList(note));
        assertEquals(Result.Borderline, result2);

        note.setMemo(note.getMemo().concat(" vertige"));
        Result result3 = assessService.assess(patientM, Collections.singletonList(note));
        assertEquals(Result.Danger, result3);

        note.setMemo(note.getMemo().concat(" anormal"));
        Result result4 = assessService.assess(patientF, Collections.singletonList(note));
        assertEquals(Result.Danger, result4);

        note.setMemo(note.getMemo().concat(" fumeur"));
        Result result5 = assessService.assess(patientM, Collections.singletonList(note));
        assertEquals(Result.Early, result5);

        note.setMemo(note.getMemo().concat(" rechute"));
        Result result6 = assessService.assess(patient30, Collections.singletonList(note));
        assertEquals(Result.Danger, result6);

        note.setMemo(note.getMemo().concat(" réaction"));
        Result result7 = assessService.assess(patientF, Collections.singletonList(note));
        assertEquals(Result.Early, result7);

        note.setMemo(note.getMemo().concat(" hémoglobine a1c"));
        Result result8 = assessService.assess(patient30, Collections.singletonList(note));
        assertEquals(Result.Early, result8);
    }
}