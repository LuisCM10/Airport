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
import core.models.Plane;
import java.util.List;

public class PlaneFlightManager {
    
    
    public void addFlight(Plane plane, Flight flight) {
        if (!canAddFlight(plane)) {
            throw new IllegalStateException("Capacidad máxima alcanzada");
        }
        plane.getFlights().add(flight);
    }

    
    public void removeFlight(Plane plane, Flight flight) {
        plane.getFlights().remove(flight);
    }

    
    public static List<Flight> getFlights(Plane plane) {
        return plane.getFlights();
    }

    
    public boolean canAddFlight(Plane plane) {
        return plane.getFlights().size() < plane.getMaxCapacity();
    }
}