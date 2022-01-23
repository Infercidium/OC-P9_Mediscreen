package com.infercidium.mediscreenUI.service;

import com.infercidium.mediscreenUI.constants.Pagination;
import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.model.Patient;
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

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(PaginationService.class);

    /**
     * Allows you to transform a list into a page.
     * @param list to be paging.
     * @param currentPage to display.
     * @return list of current page.
     */
    @Override
    public Page<Patient> pagination(final List<Patient> list,
                                    final int currentPage) {
        List<Patient> patientList = sort(list);

        int startItem = (currentPage - 1) * Pagination.PAGE_SIZE;
        List<Patient> finalList;

        if (patientList.size() < startItem) {
            finalList = Collections.emptyList();
        } else {
            int toIndex = Math.min(
                    startItem + Pagination.PAGE_SIZE,
                    patientList.size());
            finalList = patientList.subList(startItem, toIndex);
        }

        Page<Patient> patientPage = new PageImpl<>(
                finalList,
                PageRequest.of(currentPage - 1, Pagination.PAGE_SIZE),
                list.size());
        LOGGER.debug("patientPage generated");
        return patientPage;
    }

    /**
     * Sorts a list by family (from a to z), by given (from a to z)
     * and by date of birth (from oldest to oldest).
     * @param list to sort.
     * @return list sorted.
     */
    private List<Patient> sort(final List<Patient> list) {
        list.sort(Comparator.comparing(Patient::getFamilySort)
                .thenComparing(Patient::getGivenSort)
                .thenComparing(Patient::getDob));
        LOGGER.debug("Patient list sorted");
        return list;
    }
}
