package com.felipesantos.reservationreactiveapp.controller;

import com.felipesantos.reservationreactiveapp.model.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.felipesantos.reservationreactiveapp.controller.ReservationResource.API_V_1_RESERVATIONS;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    private Reservation reservation;

    @Before
    public void setUp() {
        webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                .build();

        reservation = new Reservation(123l,
                LocalDate.now(),
                LocalDate.now().plus(10, ChronoUnit.DAYS),
                150);
    }

    @Test
    public void listAllReservations_shouldReturnListOfReservations_ifItsRequired() {
        webTestClient.get()
                .uri(API_V_1_RESERVATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reservation.class);
    }

    @Test
    public void createReservation_shouldCreateAReservation_ifItsRequired() {
        webTestClient.post()
                .uri(API_V_1_RESERVATIONS)
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Reservation.class);
    }
}