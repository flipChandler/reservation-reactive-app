package com.felipesantos.reservationreactiveapp.controller;

import com.felipesantos.reservationreactiveapp.model.Reservation;
import com.felipesantos.reservationreactiveapp.service.ReservationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResource.API_V_1_RESERVATIONS)
@CrossOrigin
public class ReservationResource {

    public static final String API_V_1_RESERVATIONS = "/api/v1/reservations";

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public Flux<Reservation> listAllReservations() {
        return reservationService.listAllReservations();
    }

    @GetMapping("{roomId}")
    public Mono<Reservation> getReserrvationById(@PathVariable String roomId) {
        return reservationService.getReservation(roomId);
    }

    @PostMapping
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping("{roomId}")
    public Mono<Reservation> updateReservation(@PathVariable String roomId,
                                               @RequestBody Mono<Reservation> reservation) {
        return reservationService.updateReservation(roomId, reservation);
    }

    @DeleteMapping("{roomId}")
    public Mono<Boolean> deleteReservation(@PathVariable String roomId) {
        return reservationService.deleteReservation(roomId);
    }
}
