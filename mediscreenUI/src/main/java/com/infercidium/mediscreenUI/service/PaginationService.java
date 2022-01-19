package com.infercidium.mediscreenUI.service;

import com.infercidium.mediscreenUI.constants.Pagination;
import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PaginationService implements PaginationIService {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(PaginationService.class);

    @Override
    public Page<Patient> pagination(final List<Patient> list, final int currentPage) {
        List<Patient> patientList = sort(list);

        int startItem = (currentPage - 1) * Pagination.PAGE_SIZE;
        List<Patient> finalList;

        if (patientList.size() < startItem) {
            finalList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + Pagination.PAGE_SIZE, patientList.size());
            finalList = patientList.subList(startItem, toIndex);
        }

        Page<Patient> patientPage = new PageImpl<>(finalList, PageRequest.of(currentPage - 1, Pagination.PAGE_SIZE), list.size());
        LOGGER.debug("patientPage generated");
        return patientPage;
    }

    private List<Patient> sort(final List<Patient> list) {
        list.sort(Comparator.comparing(Patient::getFamily).thenComparing(Patient::getGiven).thenComparing(Patient::getDob));
        LOGGER.debug("Patient list sorted");
        return list;
    }
}
