package com.infercidium.mediscreenUI.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
public class Note {

    /**
     * The id attribute corresponds to the generated id of the note.
     */
    private String noteId;

    /**
     * The note attribute corresponds to the note-taking date.
     */
    @PastOrPresent
    private LocalDate timesheet = LocalDate.now();

    /**
     * The patId attribute corresponds to the patient ID linked to the note.
     */
    @NotNull
    private int patId;

    /**
     * The patient attribute corresponds to the name
     * of the patient linked to the note.
     */
    @NotBlank
    private String patient;

    /**
     * The memo attribute corresponds to the note taken.
     */
    @NotBlank
    private String memo;

    /**
     * The attribute doctor corresponds to the physician author of the note.
     */
    private String doctor = "Inconnu";

    /**
     * ToString method.
     * @return toString.
     */
    @Override
    public String toString() {
        return "Note {"
                + "noteId = " + noteId
                + ", timesheet = " + timesheet
                + ", patId = " + patId
                + ", patient = '" + patient + '\''
                + ", memo = '" + memo + '\''
                + ", doctor = '" + doctor + '\''
                + '}';
    }

    /**
     * Equals method.
     * @param o : element to compare.
     * @return result of comparison.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Note)) {
            return false;
        }
        Note note = (Note) o;
        return getNoteId().equals(note.getNoteId());
    }

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNoteId());
    }
}
