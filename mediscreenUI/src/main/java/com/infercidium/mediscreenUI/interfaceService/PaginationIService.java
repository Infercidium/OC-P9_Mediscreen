package com.infercidium.mediscreenUI.interfaceService;

import com.infercidium.mediscreenUI.models.Note;
import com.infercidium.mediscreenUI.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaginationIService {
    /**
     * Allows you to transform a patient list into a page.
     * @param list to be paging.
     * @param currentPage to display.
     * @return list of current page.
     */
    Page<Patient> patientPagination(List<Patient> list, int currentPage);

    /**
     * Allows you to transform a note list into a page.
     * @param list to be paging.
     * @param currentPage to display.
     * @return list of current page.
     */
    Page<Note> notePagination(List<Note> list, int currentPage);
}
