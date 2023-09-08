import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {}

  private baseUrl : string = 'http://localhost:8080';
  private reservationUrl : string = `${this.baseUrl}/api/v1/reservations/`;

  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.reservationUrl);
  }

  createReservation(body: ReservationRequest): Observable<Reservation> {
    return this.http.post<Reservation>(this.reservationUrl, body);
  }
}

export class ReservationRequest {
  roomNumber: number;
  checkIn: string;
  checkOut: string;
  price: number;

  constructor(roomNumber: number,
    checkIn: string,
    checkOut: string,
    price: number) {
      this.roomNumber = roomNumber;
      this.checkIn = checkIn;
      this.checkOut = checkOut;
      this.price = price;
    }
}

export interface Reservation {
  id: string;
  roomNumber: number;
  checkIn: Date;
  checkOut: Date;
  price: number;
}


