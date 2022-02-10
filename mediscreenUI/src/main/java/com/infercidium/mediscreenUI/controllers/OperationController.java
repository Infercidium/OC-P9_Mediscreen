package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.constants.Result;
import com.infercidium.mediscreenUI.models.Note;
import com.infercidium.mediscreenUI.models.Patient;
import com.infercidium.mediscreenUI.proxies.AssessProxy;
import com.infercidium.mediscreenUI.proxies.InfoProxy;
import com.infercidium.mediscreenUI.proxies.NoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OperationController {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(OperationController.class);

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
     * Instantiation of NoteProxy.
     */
    @Autowired
    private AssessProxy assessProxy;

    /**
     * Instantiation of InterfaceController.
     */
    @Autowired
    private InterfaceController interfaceController;

    /**
     * Returns a list of patients according to the search parameters,
     * none: the whole list,
     * family or given: all the patients with the parameter,
     * family and given: the list of all the patients with the two parameters,
     * the patient's page there is only one.
     * @param model  containing the elements to display.
     * @param family of patient.
     * @param given  of patient.
     * @return the patient list to display.
     */
    @GetMapping("/patientResearch")
    public String patientResearch(final Model model,
                                  final String family,
                                  final String given) {

        List<Patient> patientList;
        if (given.isEmpty() && family.isEmpty()) {
            patientList
                    = infoProxy.getAllPatient();
        } else if (given.isEmpty()) {
            patientList
                    = infoProxy.getListFamilyPatient(family);
        } else if (family.isEmpty()) {
            patientList
                    = infoProxy.getListGivenPatient(given);
        } else {
            patientList
                    = infoProxy.getPatient(family, given);
            if (patientList.size() == 1) {
                LOGGER.debug("Patient found");
                return "redirect:/patient/"
                        + patientList.get(0).getPatientId();
            }
        }
        if (patientList.isEmpty()) {
            LOGGER.info("No patient found");
            return "redirect:/?noResult";
        }
        interfaceController.setPatientList(patientList);
        LOGGER.debug("Patient list found");
        return "redirect:/patientList";
    }

    /**
     * Request to delete a patient.
     *
     * @param id of patient.
     * @return the deletion validation page.
     */
    @GetMapping("/patient/delete/{id}")
    public String patientDelete(@PathVariable final int id) {
        infoProxy.removePatient(id);
        noteProxy.deleteNotes(id);
        LOGGER.info("Deletion of patient successful");
        return "redirect:/?successDelete";
    }

    /**
     * Verifies the patient and adds it to the database.
     *
     * @param model   containing the elements to display.
     * @param patient to add to the database.
     * @param result  to error validation.
     * @return added patient page.
     */
    @PostMapping("/validatenew")
    public String validatenew(final Model model,
                              @Valid final Patient patient,
                              final BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.info("Error detected, return to form");
            return "patientnew";
        }
        Patient savePatient = infoProxy.addPatient(patient);
        LOGGER.info("New patient created");
        return "redirect:/patient/" + savePatient.getPatientId();
    }

    /**
     * Checks the patient and modifies it in the database.
     *
     * @param model   containing the elements to display.
     * @param patient to modify in the database.
     * @param result  to error validation.
     * @param id      of the patient to modify.
     * @return modified patient page.
     */
    @PostMapping("/validateupdate/{id}")
    public String validateUpdate(final Model model,
                                 @Valid final Patient patient,
                                 final BindingResult result,
                                 @PathVariable final int id) {
        if (result.hasErrors()) {
            patient.setPatientId(id);
            model.addAttribute("patient", patient);
            LOGGER.info("Error detected, return to form");
            return "patientupdate";
        }
        Patient updatePatient = infoProxy.updatePatient(patient, id);
        LOGGER.info("Patient updated");
        return "redirect:/patient/" + updatePatient.getPatientId();
    }

//Note
    /**
     * Verifies the note and adds it to the database.
     *
     * @param note   to add.
     * @param result to error validation.
     * @param patId  is patient id.
     * @return patient page.
     */
    @PostMapping("validateNewNote/{patId}")
    public String validateNewNote(@Valid final Note note,
                                  final BindingResult result,
                                  @PathVariable final int patId) {
        if (result.hasErrors()) {
            LOGGER.info("Error detected, new note cancel");
            return "redirect:/patient/" + patId + "?errorNote";
        }
        noteProxy.addNote(note);
        LOGGER.info("New note created");
        return "redirect:/patient/" + patId;
    }

    /**
     * Checks the patient and modifies it in the database.
     *
     * @param note   to update.
     * @param result to error validation.
     * @param patId  is patient id.
     * @param id     of note.
     * @return patient page.
     */
    @PostMapping("validateUpdateNote/{patId}")
    public String validateUpdateNote(@Valid final Note note,
                                     final BindingResult result,
                                     @PathVariable final int patId,
                                     @RequestParam final String id) {
        if (result.hasErrors()) {
            LOGGER.info("Error detected, new note cancel");
            return "redirect:/patient/" + patId + "?errorNote";
        }
        noteProxy.updateNote(note, id);
        LOGGER.info("Note updated");
        return "redirect:/patient/" + patId;
    }

    /**
     * Request to delete a note.
     *
     * @param id of note.
     * @return the deletion validation page.
     */
    @GetMapping("/patHistory/delete/{id}")
    public String noteDelete(@PathVariable final String id) {
        Note note = noteProxy.getNote(id);
        noteProxy.deleteNote(id);
        LOGGER.info("Deletion of note successful");
        return "redirect:/patient/" + note.getPatId() + "?successDelete";
    }

    //Assess
    /**
     * Request to assess patient report.
     *
     * @param id of patient.
     * @return to assess patient page.
     */
    @GetMapping("assess/patient/{id}")
    public String assessPatient(@PathVariable final int id) {
        Result result = assessProxy.getPatientResult(id);
        return "redirect:/patient/" + id + "?" + result.name();
    }
}
