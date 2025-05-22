/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

/**
 *
 * @author Usuario
 */

    import core.models.Flight;
import core.models.Plane;
import java.util.List;

public class PlaneFlightManagerImpl implements PlaneFlightManager {
    
    @Override
    public void addFlight(Plane plane, Flight flight) {
        if (!canAddFlight(plane)) {
            throw new IllegalStateException("Capacidad máxima alcanzada");
        }
        plane.getFlights().add(flight);
    }

    @Override
    public void removeFlight(Plane plane, Flight flight) {
        plane.getFlights().remove(flight);
    }

    @Override
    public List<Flight> getFlights(Plane plane) {
        return plane.getFlights();
    }

    @Override
    public boolean canAddFlight(Plane plane) {
        return plane.getFlights().size() < plane.getMaxCapacity();
    }
}