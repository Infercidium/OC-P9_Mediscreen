package com.infercidium.mediscreenInfo.model;

import com.infercidium.mediscreenInfo.constant.Genres;
import com.infercidium.mediscreenInfo.constant.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    @NotBlank(message = "date of birthday is mandatory")
    private LocalDate dob; //date of birthday
    /**
     * Attribute sex corresponding to patient genre.
     */
    @NotBlank(message = "genre is mandatory")
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
    private Result result = Result.UNKNOWN;

    /**
     * Obtain the patient's age by comparing the current date
     * with his date of birth.
     * @return age.
     */
    public int getAge() {
        return Math.toIntExact(ChronoUnit.YEARS.between(dob, LocalDate.now()));
    }

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
