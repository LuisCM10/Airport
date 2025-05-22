/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.services;

import core.models.Plane;
import core.models.Flight;

public class PlaneService {
    private final PlaneFlightManager flightManager;

    public PlaneService(PlaneFlightManager flightManager) {
        this.flightManager = flightManager;
    }

    public void assignFlight(Plane plane, Flight flight) {
        flightManager.addFlight(plane, flight);
    }

    public void unassignFlight(Plane plane, Flight flight) {
        flightManager.removeFlight(plane, flight);
    }

    public boolean isFlightAssignable(Plane plane) {
        return flightManager.canAddFlight(plane);
    }
}
