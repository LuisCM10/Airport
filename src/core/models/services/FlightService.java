/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

    package core.models.services;

import core.models.Flight;
import core.models.Passenger;
import java.time.LocalDateTime;

public class FlightService {

    // Agrega un pasajero al vuelo
    public void addPassenger(Flight flight, Passenger passenger) {
        flight.getPassengers().add(passenger);
    }

    // Calcula la fecha y hora de llegada considerando escala y duración total
    public LocalDateTime calculateArrivalDate(Flight flight) {
        return flight.getDepartureDate()
                .plusHours(flight.getHoursDurationScale())
                .plusMinutes(flight.getMinutesDurationScale())
                .plusHours(flight.getHoursDurationArrival())
                .plusMinutes(flight.getMinutesDurationArrival());
    }

    // Retrasa la salida del vuelo
    public void delayFlight(Flight flight, int hours, int minutes) {
        LocalDateTime newDeparture = flight.getDepartureDate()
                .plusHours(hours)
                .plusMinutes(minutes);
        flight.setDepartureDate(newDeparture);
    }

    // Retorna el número total de pasajeros del vuelo
    public int getNumPassengers(Flight flight) {
        return flight.getPassengers().size();
    }
}

