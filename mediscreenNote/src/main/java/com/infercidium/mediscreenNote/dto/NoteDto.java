package com.infercidium.mediscreenNote.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
public class NoteDto {
    /**
     * The patId attribute corresponds to the patient ID linked to the note.
     */
    @NotNull
    private int patId;

    /**
     * The e attribute corresponds to the note taken.
     */
    @NotBlank
    private String e;

    @Override
    public String toString() {
        return "NoteDto{"
                + "patId = " + patId
                + ", e = '" + e + '\''
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
        if (!(o instanceof NoteDto)) {
            return false;
        }
        NoteDto noteDto = (NoteDto) o;
        return getPatId() == noteDto.getPatId();
    }

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPatId());
    }
}
