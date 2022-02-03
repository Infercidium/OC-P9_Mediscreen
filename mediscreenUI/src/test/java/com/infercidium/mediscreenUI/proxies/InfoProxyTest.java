package com.infercidium.mediscreenUI.proxies;

import com.infercidium.mediscreenUI.constants.Genres;
import com.infercidium.mediscreenUI.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {InfoProxy.class})
@RunWith(SpringRunner.class)
class InfoProxyTest {
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    private WebClient.RequestHeadersSpec requestHeadersMock;
    private WebClient.RequestBodySpec requestBodyMock;
    private WebClient.ResponseSpec responseMock;
    private WebClient webClientMock;

    @Value("${info.url}")
    private String infoUrlBase;

    @MockBean
    private WebClient infoClient;

    @Autowired
    private InfoProxy infoProxy;

    Patient patient = new Patient();
    private Mono<List<Patient>> patientMono = Mono.just(Collections.singletonList(patient));
    private Flux<Patient> patientFlux = Flux.just(patient);


    @BeforeEach
    void setUp() {
        requestHeadersUriMock = mock(WebClient.RequestHeadersUriSpec.class);
        requestBodyUriMock = mock(WebClient.RequestBodyUriSpec.class);
        requestHeadersMock = mock(WebClient.RequestHeadersSpec.class);
        requestBodyMock = mock(WebClient.RequestBodySpec.class);
        responseMock = mock(WebClient.ResponseSpec.class);
        webClientMock = mock(WebClient.class);
        patientFlux = mock(Flux.class);
        patientMono = mock(Mono.class);

        patient.setGiven("firstName");
        patient.setFamily("lastName");
        patient.setSex(Genres.M);
        patient.setDob(LocalDate.of(2000,1,1));
    }

    @Test
    void getPatient() {
        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("/patient/{family}/{given}", patient.getFamily(), patient.getGiven())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToFlux(Patient.class)).thenReturn(patientFlux);
        when(patientFlux.collectList()).thenReturn(patientMono);
        when(patientMono.block()).thenReturn(Collections.singletonList(patient));

        List<Patient> patientList = infoProxy.getPatient(patient.getFamily(), patient.getGiven());
        System.out.println(patientList);
    }

    @Test
    void getListFamilyPatient() {
    }

    @Test
    void getListGivenPatient() {
    }

    @Test
    void getAllPatient() {
    }

    @Test
    void getPatientId() {
    }

    @Test
    void addPatient() {
    }

    @Test
    void updatePatient() {
    }

    @Test
    void removePatient() {
    }
}