package com.infercidium.mediscreenNote.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
