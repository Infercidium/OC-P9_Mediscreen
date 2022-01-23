package com.infercidium.mediscreenUI;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.model.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
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

    Patient patient = new Patient();

    @BeforeEach
    void setUp() {
        patient.setGiven("firstName");
        patient.setFamily("lastName");
        patient.setSex(Genres.M);
        patient.setDob(LocalDate.of(2000,1,1));
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
}
