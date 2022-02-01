package com.infercidium.mediscreenCalcul.controller;

import com.infercidium.mediscreenCalcul.constants.Result;
import com.infercidium.mediscreenCalcul.interfaceService.AssessIService;
import com.infercidium.mediscreenCalcul.models.Note;
import com.infercidium.mediscreenCalcul.models.Patient;
import com.infercidium.mediscreenCalcul.proxies.InfoProxy;
import com.infercidium.mediscreenCalcul.proxies.NoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssessController {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(AssessController.class);

    /**
     * Instantiation of InfoProxy.
     */
    @Autowired
    private InfoProxy infoProxy;

    /**
     * Instantiation of NoteProxy.
     */
    @Autowired
    private NoteProxy noteProxy;

    /**
     * Instantiation of AssessIService.
     */
    @Autowired
    private AssessIService assessIService;

    /**
     * Retrieves patient data and associated medical notes
     * to calculate diabetes risk.
     * @param id of patient.
     * @return result of assess.
     */
    @GetMapping("/assess/{id}")
    public Result getPatientAssess(@PathVariable final int id) {
        Patient patient = infoProxy.getPatientId(id);
        List<Note> noteList = noteProxy.getPatientNote(id);

        Result result = assessIService.assess(patient, noteList);
        patient.setResult(result);
        infoProxy.updatePatient(patient, id);

        LOGGER.info("Result sent");
        return result;
    }

    /**
     * Retrieves patient data and associated medical notes
     * to calculate diabetes risk.
     * @param patId of patient.
     * @return result of assess.
     */
    @PostMapping(value = {"/assess/id"},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String getPatientAssessURL(final int patId) {
        Patient patient = infoProxy.getPatientId(patId);
        List<Note> noteList = noteProxy.getPatientNote(patId);

        Result result = assessIService.assess(patient, noteList);
        patient.setResult(result);
        infoProxy.updatePatient(patient, patId);

        String finalresult = "Patient: " + patient.getGiven() + " "
                + patient.getFamily() + " (age " + patient.getAge()
                + ") diabetes assessment is: ";

        LOGGER.info("Result sent");

        if (result.name().equals(Result.Danger.name())) {
            return finalresult.concat("In danger");
        }

        if (result.name().equals(Result.Early.name())) {
            return finalresult.concat("Early onset");
        }

        return finalresult.concat(result.name());
    }

    /**
     * Retrieves the first patient data with same familyName
     * and associated medical notes to calculate diabetes risk.
     * @param familyName of patient.
     * @return result of assess.
     */
    @PostMapping(value = {"/assess/familyName"},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String getPatientAssessFamilyURL(final String familyName) {
        Patient patient = infoProxy.getListFamilyPatient(familyName).get(0);
        List<Note> noteList = noteProxy.getPatientNote(patient.getPatientId());

        Result result = assessIService.assess(patient, noteList);
        patient.setResult(result);
        infoProxy.updatePatient(patient, patient.getPatientId());

        String finalresult = "Patient: " + patient.getGiven() + " "
                + patient.getFamily() + " (age " + patient.getAge()
                + ") diabetes assessment is: ";

        LOGGER.info("Result sent");

        if (result.name().equals(Result.Danger.name())) {
            return finalresult.concat("In danger");
        }

        if (result.name().equals(Result.Early.name())) {
            return finalresult.concat("Early onset");
        }

        return finalresult.concat(result.name());
    }
}
