package com.infercidium.mediscreenCalcul.service;

import com.infercidium.mediscreenCalcul.constants.Assess;
import com.infercidium.mediscreenCalcul.constants.Genres;
import com.infercidium.mediscreenCalcul.constants.Result;
import com.infercidium.mediscreenCalcul.constants.Trigger;
import com.infercidium.mediscreenCalcul.interfaceService.AssessIService;
import com.infercidium.mediscreenCalcul.models.Note;
import com.infercidium.mediscreenCalcul.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessService implements AssessIService {

    /**
     * Instantiation of LOGGER in the order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(AssessService.class);


    /**
     * Allows you to calculate the risks of diabetes for the risk ratio.
     * @param patient  concerned.
     * @param noteList containing all doctors' notes.
     * @return the result of the diabetes calculation.
     */
    @Override
    public Result assess(final Patient patient, final List<Note> noteList) {
        int age = patient.getAge();
        int trigger = countTrigger(noteList);
        Genres sex = patient.getSex();

        if ((sex == Genres.M && age <= Assess.AGE_MAX
                && trigger >= Assess.EARLY_M_J)
                || (sex == Genres.F && age <= Assess.AGE_MAX
                && trigger >= Assess.EARLY_F_J)
                || (age > Assess.AGE_MAX && trigger >= Assess.EARLY)) {
            LOGGER.debug("Early result found");
            return Result.Early;
        }

        if ((sex == Genres.M && age <= Assess.AGE_MAX
                && trigger >= Assess.DANGER_M_J)
                || (sex == Genres.F && age <= Assess.AGE_MAX
                && trigger >= Assess.DANGER_F_J)
                || (age > Assess.AGE_MAX && trigger >= Assess.DANGER)) {
            LOGGER.debug("Danger result found");
            return Result.Danger;
        }

        if (age > Assess.AGE_MAX && trigger >= Assess.BORDER) {
            LOGGER.debug("Borderline result found");
            return Result.Borderline;
        }

        LOGGER.debug("None result found");
        return Result.None;
    }

    /**
     * Counts the number of different trigger words.
     * @param noteList to control.
     * @return the number of different trigger term.
     */
    private int countTrigger(final List<Note> noteList) {
        int count = 0;
        for (Trigger trigger : Trigger.values()) {
            if (verifTrigger(trigger.name(), noteList)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Checks if a trigger word is contained in the medical notes.
     * @param trigger to control.
     * @param noteList to control.
     * @return true if the notes contains it and false if not.
     */
    private Boolean verifTrigger(final String trigger,
                                 final List<Note> noteList) {

        String finalTrigger = trigger;

        if (trigger.contains("_")) {
            finalTrigger = trigger.replace("_", " ");
        }

        for (Note note : noteList) {
            if (note.getMemo().toLowerCase().contains(finalTrigger)) {
                return true;
            }
        }
        return false;
    }
}
