package com.infercidium.mediscreenUI.proxy;

import com.infercidium.mediscreenUI.model.Patient;
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
     * Sends a request for patients with the same full name.
     * @param family of the patient.
     * @param given of the patient.
     * @return patient list.
     */
   public List<Patient> getPatient(final String family, final String given) {
           return infoClient.get()
                   .uri("/patient/{family}/{given}", family, given)
                   .retrieve().bodyToFlux(Patient.class).collectList().block();
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
     * Sends a request for patients with the same first name.
     * @param given of the patient.
     * @return patient list.
     */
    public List<Patient> getListGivenPatient(final String given) {
        return infoClient.get().uri("/patient/given/{given}", given)
                .retrieve().bodyToFlux(Patient.class).collectList().block();
    }

    /**
     * Send a request for all patients.
     * @return patient list.
     */
    public List<Patient> getAllPatient() {
        return infoClient.get().uri("/patient/all")
                .retrieve().bodyToFlux(Patient.class).collectList().block();
    }

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
     * Sends the patient to be added.
     * @param patient to add.
     * @return patient add.
     */
    public Patient addPatient(final Patient patient) {
       return infoClient.post().uri("/patient/add").bodyValue(patient)
               .retrieve().bodyToMono(Patient.class).block();
    }

    /**
     * Send the patient and the patient id to modify.
     * @param patient to modify.
     * @param id of the patient to modify.
     * @return patient edit.
     */
    public Patient updatePatient(final Patient patient, final int id) {
       return infoClient.put().uri("/patient/update/{id}", id).bodyValue(patient)
               .retrieve().bodyToMono(Patient.class).block();
    }

    /**
     * Sends the id of the patient to be deleted.
     * @param id of the patient to be deleted.
     * @return NOTHING.
     */
    public Void removePatient(final int id) {
       return infoClient.delete().uri("/patient/remove/{id}", id)
               .retrieve().bodyToMono(Void.class).block();
       //TODO A VOIR (Pas Satisfaisant)
    }
}
