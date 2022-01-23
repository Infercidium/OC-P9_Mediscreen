package com.infercidium.mediscreenUI.interfaceService;

import com.infercidium.mediscreenUI.model.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaginationIService {
    /**
     * Allows you to transform a list into a page.
     * @param list to be paging.
     * @param currentPage to display.
     * @return list of current page.
     */
    Page<Patient> pagination(List<Patient> list, int currentPage);
}
