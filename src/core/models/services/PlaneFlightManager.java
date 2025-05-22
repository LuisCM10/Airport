/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.services;

/**
 *
 * @author Usuario
 */
import core.models.Flight;
import core.models.Plane;
import java.util.List;

public interface PlaneFlightManager {
    void addFlight(Plane plane, Flight flight);
    void removeFlight(Plane plane, Flight flight);
    List<Flight> getFlights(Plane plane);
    boolean canAddFlight(Plane plane);
}
