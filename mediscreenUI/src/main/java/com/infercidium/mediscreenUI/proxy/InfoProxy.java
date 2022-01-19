package com.infercidium.mediscreenUI.proxy;

import com.infercidium.mediscreenUI.models.Patient;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@NoArgsConstructor
public class InfoProxy {
    @Value("${info.url}")
    public String infoUrlBase;

    @Autowired
    private WebClient infoClient;

   public List<Patient> getPatient(final String family, final String given) {
        return infoClient.get().uri("/patient/{family}/{given}", family, given).retrieve().bodyToFlux(Patient.class).collectList().block();
    }

    public List<Patient> getListPatient(final String family) {
        return infoClient.get().uri("/family/{family}", family).retrieve().bodyToFlux(Patient.class).collectList().block();
    }

    public List<Patient> getAllPatient() {
        return infoClient.get().uri("/all").retrieve().bodyToFlux(Patient.class).collectList().block();
    }

    public Patient getPatientId(final int id) {
       return infoClient.get().uri("/patient/{id}", id).retrieve().bodyToMono(Patient.class).block();
    }
}
