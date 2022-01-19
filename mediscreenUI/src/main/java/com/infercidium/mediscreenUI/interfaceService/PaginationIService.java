package com.infercidium.mediscreenUI.interfaceService;

import com.infercidium.mediscreenUI.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaginationIService {
    Page<Patient> pagination(List<Patient> list, int currentPage);
}
