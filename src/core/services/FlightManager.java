/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.services;

import core.models.Flight;
import core.models.Passenger;
import java.util.List;

/**
 *
 * @author Usuario
 */

public interface FlightManager {
    void addFlight(Passenger passenger, Flight flight);
    List<Flight> getFlights(Passenger passenger);
}

