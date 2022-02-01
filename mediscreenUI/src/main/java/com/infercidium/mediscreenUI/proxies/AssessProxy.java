package com.infercidium.mediscreenUI.proxies;

import com.infercidium.mediscreenUI.constants.Result;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@NoArgsConstructor
public class AssessProxy {

    /**
     * Instantiates the mediscreen-Calcul API url.
     */
    @Value("${assess.url}")
    private String assessUrlBase;

    /**
     * Instantiates the WebClient.
     */
    @Autowired
    private WebClient assessClient;

    /**
     * Send a request to calculate a patient's diabetes risk.
     * @param id is patient id.
     * @return result string.
     */
    public Result getPatientResult(final int id) {
        return assessClient.get().uri("/assess/{id}", id)
                .retrieve().bodyToMono(Result.class).block();
    }
}
