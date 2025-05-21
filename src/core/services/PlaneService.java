/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Plane;
import core.models.Flight;

public class PlaneService {

    public void addFlight(Plane plane, Flight flight) {
        plane.getFlights().add(flight);
    }

    public int getNumFlights(Plane plane) {
        return plane.getFlights().size();
    }
   
}
