/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import core.models.Flight;
import core.models.Location;
import core.models.Plane;

/**
 *
 * @author Usuario
 */
public class VueloCompleto {
    private String id;
    private Plane plane;
    private Location departureLocation;
    private Location arrivalLocation;
    private Location scaleLocation; // puede ser null
    private String departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;

    public VueloCompleto(Flight flight, Plane plane, Location dep, Location arr, Location scale) {
        this.id = flight.getId();
        this.plane = plane;
        this.departureLocation = dep;
        this.arrivalLocation = arr;
        this.scaleLocation = scale;
        this.departureDate = flight.getDepartureDate();
        this.hoursDurationArrival = flight.getHoursDurationArrival();
        this.minutesDurationArrival = flight.getMinutesDurationArrival();
        this.hoursDurationScale = flight.getHoursDurationScale();
        this.minutesDurationScale = flight.getMinutesDurationScale();
    }

    @Override
    public String toString() {
        return "Vuelo " + id + " con avión " + plane.getId() + "\n" +
               "Salida: " + departureLocation.getCity() + "\n" +
               "Llegada: " + arrivalLocation.getCity() + "\n" +
               (scaleLocation != null ? "Escala en: " + scaleLocation.getCity() + "\n" : "") +
               "Fecha: " + departureDate + "\n";
    }
}
