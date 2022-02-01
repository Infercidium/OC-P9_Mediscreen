package com.infercidium.mediscreenUI.service;

import com.infercidium.mediscreenUI.constants.Pagination;
import com.infercidium.mediscreenUI.interfaceService.PaginationIService;
import com.infercidium.mediscreenUI.models.Note;
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
    public Page<Patient> patientPagination(final List<Patient> list,
                                           final int currentPage) {
        List<Patient> patientList = patientSort(list);

        int startItem = (currentPage - 1) * Pagination.PATIENT_PAGE_SIZE;
        List<Patient> finalList;

        if (patientList.size() < startItem) {
            finalList = Collections.emptyList();
        } else {
            int toIndex = Math.min(
                    startItem + Pagination.PATIENT_PAGE_SIZE,
                    patientList.size());
            finalList = patientList.subList(startItem, toIndex);
        }

        Page<Patient> patientPage = new PageImpl<>(
                finalList,
                PageRequest.of(currentPage - 1, Pagination.PATIENT_PAGE_SIZE),
                list.size());
        LOGGER.debug("patientPage generated");
        return patientPage;
    }

    /**
     * Allows you to transform a note list into a page.
     * @param list        to be paging.
     * @param currentPage to display.
     * @return list of current page.
     */
    @Override
    public Page<Note> notePagination(final List<Note> list,
                                     final int currentPage) {
        List<Note> noteList = noteSort(list);

        int startItem = (currentPage - 1) * Pagination.NOTE_PAGE_SIZE;
        List<Note> finalList;

        if (noteList.size() < startItem) {
            finalList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + Pagination.NOTE_PAGE_SIZE,
                    noteList.size());
            finalList = noteList.subList(startItem, toIndex);
        }

        Page<Note> notePage = new PageImpl<>(
                finalList,
                PageRequest.of(currentPage - 1, Pagination.NOTE_PAGE_SIZE),
                list.size());
        LOGGER.debug("notePage generated");
        return notePage;
    }

    /**
     * Sorts a list by family (from a to z), by given (from a to z)
     * and by date of birth (from oldest to youngest).
     * @param list to sort.
     * @return list sorted.
     */
    private List<Patient> patientSort(final List<Patient> list) {
        list.sort(Comparator.comparing(Patient::getFamilySort)
                .thenComparing(Patient::getGivenSort)
                .thenComparing(Patient::getDob));
        LOGGER.debug("Patient list sorted");
        return list;
    }

    /**
     * Sorts a list by date of add (from newest to oldest).
     * @param list to sort.
     * @return list sorted.
     */
    private List<Note> noteSort(final List<Note> list) {
        list.sort(Comparator.comparing(Note::getTimesheet).reversed());
        LOGGER.debug("Note list sorted");
        return list;
    }
}
