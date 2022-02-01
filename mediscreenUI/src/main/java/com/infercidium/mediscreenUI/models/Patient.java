package com.infercidium.mediscreenUI.models;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.constants.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
public class Patient {

    /**
     * Attribute id corresponding to id generate.
     */
    private Integer patientId;
    /**
     * Attribute given corresponding to firstName patient.
     */
    @NotBlank(message = "Le prénom est obligatoire")
    private String given;
    /**
     * Attribute family corresponding to lastName patient.
     */
    @NotBlank(message = "Le nom est obligatoire")
    private String family;
    /**
     * Attribute dob corresponding to birthday patient.
     */
    @NotNull(message = "La date de naissance est obligatoire")
    @PastOrPresent(message = "La date de naissance "
            + "ne peut pas être dans le futur")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob; //date of birthday
    /**
     * Attribute sex corresponding to patient genre.
     */
    @NotNull(message = "Le genre doit-être défini")
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
    private Result result = Result.Unknown;

    /**
     * Obtain the patient's age by comparing the current date
     * with his date of birth.
     * @return age.
     */
    public int getAge() {
        return Math.toIntExact(ChronoUnit.YEARS.between(dob, LocalDate.now()));
    }

    /**
     * Get the lastname in lowerCase for sorting lists.
     * @return family attribute in lowerCase.
     */
    public String getFamilySort() {
        return family.toLowerCase();
    }

    /**
     * Get the firstname in lowerCase for sorting lists.
     * @return given attribute in lowerCase.
     */
    public String getGivenSort() {
        return given.toLowerCase();
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
