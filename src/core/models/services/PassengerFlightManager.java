/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.services;

/**
 *
 * @author Usuario
 */
import core.models.Flight;
import core.models.Passenger;
import java.util.List;

public class PassengerFlightManager implements FlightManager {
    @Override
    public void addFlight(Passenger passenger, Flight flight) {
        passenger.getFlights().add(flight);
    }
    @Override
    public List<Flight> getFlights(Passenger passenger) {
        return passenger.getFlights();
    }
}