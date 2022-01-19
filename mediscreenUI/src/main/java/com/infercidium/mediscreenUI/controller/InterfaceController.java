package com.infercidium.mediscreenUI.controller;

import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.models.Patient;
import com.infercidium.mediscreenUI.proxy.InfoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InterfaceController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(InterfaceController.class);

    private List<Patient> patientList = new ArrayList<>();

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    private Patient patient = new Patient();

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Autowired
    private PaginationIService paginationIService;

    @Autowired
    private InfoProxy infoProxy;

    @RequestMapping("/")
    public String home() {
        return "research";
    }

    @RequestMapping("/patientList")
    public String allPatient(final Model model, @RequestParam(defaultValue = "1") final int page) {
        //Generate Page
        Page<Patient> patientPage = paginationIService.pagination(patientList, page);
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

    @RequestMapping("/patient")
    public String patient(final Model model) {
        model.addAttribute("patient", patient);
        LOGGER.info("Patient display");
        return "patient";
    }

    @RequestMapping("/patientChange/{id}")
    public String patientChange(final Model model, @PathVariable final int id) {
            model.addAttribute("patient", infoProxy.getPatientId(id));
            return "patientupdate";
    }
}
