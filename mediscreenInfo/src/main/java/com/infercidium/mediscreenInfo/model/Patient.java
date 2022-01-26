package com.infercidium.mediscreenInfo.model;

import com.infercidium.mediscreenInfo.constants.Genres;
import com.infercidium.mediscreenInfo.constants.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class Patient {

    /**
     * Attribute id corresponding to id generate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    /**
     * Attribute given corresponding to firstName patient.
     */
    @NotBlank(message = "firstName is mandatory")
    private String given;
    /**
     * Attribute family corresponding to lastName patient.
     */
    @NotBlank(message = "lastName is mandatory")
    private String family;
    /**
     * Attribute dob corresponding to birthday patient.
     */
    @PastOrPresent(message = "date of birthday is mandatory")
    private LocalDate dob; //date of birthday
    /**
     * Attribute sex corresponding to patient genre.
     */
    @NotNull(message = "genre is mandatory")
    @Enumerated
    private Genres sex;
    /**
     * Attribute address corresponding to address of patient.
     */
    private String address;
    /**
     * Attribute phone corresponding to phone of patient.
     */
    private String phone;

    /**
     * Attribute result corresponding to diabetes result.
     */
    @Enumerated
    private Result result = Result.UNKNOWN;

    /**
     * ToString method.
     * @return toString.
     */
    @Override
    public String toString() {
        return "Patient {"
                + "patientId = " + patientId
                + ", given = '" + given + '\''
                + ", family = '" + family + '\''
                + ", dob = " + dob
                + ", sex = " + sex
                + ", address = '" + address + '\''
                + ", phone = '" + phone + '\''
                + ", result = " + result
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
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient patient = (Patient) o;
        return getPatientId().equals(patient.getPatientId());
    }

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPatientId());
    }
}
