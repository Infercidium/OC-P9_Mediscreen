package com.infercidium.mediscreenUI.controllers;

import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.model.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InterfaceController {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(InterfaceController.class);

    /**
     * Patient list to display.
     */
    private List<Patient> patientList = new ArrayList<>();

    /**
     * Defines the new list to use for display.
     * @param newPatientList the new list to display.
     */
    public void setPatientList(final List<Patient> newPatientList) {
        this.patientList = newPatientList;
    }

    /**
     * Instantiation of PaginationIService.
     */
    @Autowired
    private PaginationIService paginationIService;

    /**
     * Instantiation of InfoProxy.
     */
    @Autowired
    private InfoProxy infoProxy;

    /**
     * Home page, patient search.
     * @return home page.
     */
    @RequestMapping("/")
    public String home() {
        return "research";
    }

    /**
     * Search result page, patient list.
     * @param model containing the elements to display.
     * @param page current to display.
     * @return patient list page.
     */
    @RequestMapping("/patientList")
    public String allPatient(final Model model,
                             @RequestParam(defaultValue = "1") final int page) {
        //Generate Page
        Page<Patient> patientPage
                = paginationIService.pagination(patientList, page);
        //Contenu
        model.addAttribute("patientList", patientPage.getContent());
        // Count the pages
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i < patientPage.getTotalPages(); i++) {
            pagecount.add(i + 1);
        }
        //Pagination
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", patientPage.getNumber());
        model.addAttribute("currentPage", patientPage.getNumber() + 1);
        model.addAttribute("next", patientPage.getNumber() + 2);
        //Referral to html page
        LOGGER.info("Patient list display");
        return "patientlist";
    }

    /**
     * Selected patient information page.
     * @param model containing the elements to display.
     * @param id of patient.
     * @return patient page.
     */
    @RequestMapping("/patient")
    public String patient(final Model model,
                                @RequestParam final int id) {
        Patient patient = infoProxy.getPatientId(id);
        model.addAttribute("patient", patient);
        LOGGER.info("Assigned patient");
        return "patient";
    }

    /**
     * Page for creating a patient or modifying patient information.
     * @param model containing the elements to display.
     * @param id of the patient to modify,
     *           if 0 (default value) it is a patient creation.
     * @return Edit/create page.
     */
    @RequestMapping("/patientChange")
    public String patientChange(final Model model,
                                @RequestParam(defaultValue = "0")
                                final int id) {
        if (id == 0) {
            model.addAttribute("patient", new Patient());
            LOGGER.info("New patient form display");
            return "patientnew";
        }

        model.addAttribute("patient", infoProxy.getPatientId(id));
        LOGGER.info("Update patient form display");
        return "patientupdate";
    }
}
