package com.infercidium.mediscreenUI.models;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.constants.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientTest {

    Patient patient = new Patient();

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
    }

    @Test
    void getAge() {
        int age = patient.getAge();
        assertEquals((LocalDate.now().getYear() - 2000), age);
    }

    @Test
    void testToString() {
        String result = patient.toString();
        assertEquals("Patient {patientId = 1, given = 'firstName', family = 'lastName', dob = 2000-01-01, sex = M, address = 'address', phone = 'phone', result = UNKNOWN}", result);
    }

    @Test
    void testEquals() {
        Patient test = new Patient();
        test.setPatientId(1);
        assertTrue(patient.equals(test));
    }

    @Test
    void testHashCode() {
        assertEquals(32, patient.hashCode());
    }

    @Test
    void getPatientId() {
        assertEquals(1, patient.getPatientId());
    }

    @Test
    void getGiven() {
        assertEquals("firstName", patient.getGiven());
    }

    @Test
    void getFamily() {
        assertEquals("lastName", patient.getFamily());
    }

    @Test
    void getDob() {
        assertEquals(LocalDate.of(2000,1,1), patient.getDob());
    }

    @Test
    void getSex() {
        assertEquals(Genres.M, patient.getSex());
    }

    @Test
    void getAddress() {
        assertEquals("address", patient.getAddress());
    }

    @Test
    void getPhone() {
        assertEquals("phone", patient.getPhone());
    }

    @Test
    void getResult() {
        assertEquals(Result.UNKNOWN, patient.getResult());
    }

    @Test
    void setPatientId() {
        patient.setPatientId(2);
        assertEquals(2, patient.getPatientId());
    }

    @Test
    void setGiven() {
        patient.setGiven("test");
        assertEquals("test", patient.getGiven());
    }

    @Test
    void setFamily() {
        patient.setFamily("test");
        assertEquals("test", patient.getFamily());
    }

    @Test
    void setDob() {
        patient.setDob(LocalDate.of(2001,1,1));
        assertEquals(LocalDate.of(2001,1,1), patient.getDob());
    }

    @Test
    void setSex() {
        patient.setSex(Genres.F);
        assertEquals(Genres.F, patient.getSex());
    }

    @Test
    void setAddress() {
        patient.setAddress("test");
        assertEquals("test", patient.getAddress());
    }

    @Test
    void setPhone() {
        patient.setPhone("test");
        assertEquals("test", patient.getPhone());
    }

    @Test
    void setResult() {
        patient.setResult(Result.NONE);
        assertEquals(Result.NONE, patient.getResult());
    }
}