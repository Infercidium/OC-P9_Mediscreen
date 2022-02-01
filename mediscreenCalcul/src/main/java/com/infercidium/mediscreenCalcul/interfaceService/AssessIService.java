package com.infercidium.mediscreenCalcul.interfaceService;

import com.infercidium.mediscreenCalcul.constants.Result;
import com.infercidium.mediscreenCalcul.models.Note;
import com.infercidium.mediscreenCalcul.models.Patient;

import java.util.List;

public interface AssessIService {

    /**
     *  Allows you to calculate the risks of diabetes for the risk ratio.
     * @param patient concerned.
     * @param noteList containing all doctors' notes.
     * @return the result of the diabetes calculation.
     */
    Result assess(Patient patient, List<Note> noteList);
}
