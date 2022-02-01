package com.infercidium.mediscreenCalcul.proxies;

import com.infercidium.mediscreenCalcul.models.Patient;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@NoArgsConstructor
public class InfoProxy {

    /**
     * Instantiates the mediscreen-info API url.
     */
    @Value("${info.url}")
    private String infoUrlBase;

    /**
     * Instantiates the WebClient.
     */
    @Autowired
    private WebClient infoClient;

    /**
     * Sends the id of the requested patient.
     * @param id of the patient.
     * @return selected patient.
     */
    public Patient getPatientId(final int id) {
        return infoClient.get().uri("/patient/{id}", id)
                .retrieve().bodyToMono(Patient.class).block();
    }

    /**
     * Sends a request for patients with the same last name.
     * @param family of the patient.
     * @return patient list.
     */
    public List<Patient> getListFamilyPatient(final String family) {
        return infoClient.get().uri("/patient/family/{family}", family)
                .retrieve().bodyToFlux(Patient.class).collectList().block();
    }

    /**
     * Send the patient and the patient id to modify.
     * @param patient to modify.
     * @param id of the patient to modify.
     * @return patient edit.
     */
    public Patient updatePatient(final Patient patient, final int id) {
        return infoClient.put().uri("/patient/update/{id}", id)
                .bodyValue(patient)
                .retrieve().bodyToMono(Patient.class).block();
    }
}
