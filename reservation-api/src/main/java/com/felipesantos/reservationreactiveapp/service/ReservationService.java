package com.felipesantos.reservationreactiveapp.service;

import com.felipesantos.reservationreactiveapp.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {

    Flux<Reservation> listAllReservations();

    Mono<Reservation> getReservation(String id);

    Mono<Reservation> createReservation(Mono<Reservation> reservationMono);

    Mono<Reservation> updateReservation(String id, Mono<Reservation> reservationMono);

    Mono<Boolean> deleteReservation(String id);
}
