/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {

    private final String id;
    private final Plane plane;
    private final Location departureLocation;
    private final Location scaleLocation;  // Puede ser null si no hay escala
    private final Location arrivalLocation;
    private LocalDateTime departureDate;
    private final int hoursDurationArrival;
    private final int minutesDurationArrival;
    private final int hoursDurationScale;
    private final int minutesDurationScale;
    private final List<Passenger> passengers;

    // Constructor para vuelo sin escala
    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation,
                  LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this(id, plane, departureLocation, null, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival, 0, 0);
    }

    // Constructor para vuelo con escala
    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation,
                  LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival,
                  int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;
    }

    // Getters necesarios
    public String getId() { return id; }
    public Plane getPlane() { return plane; }
    public Location getDepartureLocation() { return departureLocation; }
    public Location getScaleLocation() { return scaleLocation; }
    public Location getArrivalLocation() { return arrivalLocation; }
    public LocalDateTime getDepartureDate() { return departureDate; }
    public List<Passenger> getPassengers() { return passengers; }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }
    public int getHoursDurationScale() {
    return hoursDurationScale;
}

public int getMinutesDurationScale() {
    return minutesDurationScale;
}

public int getHoursDurationArrival() {
    return hoursDurationArrival;
}

public int getMinutesDurationArrival() {
    return minutesDurationArrival;
}

}
