/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.FlightController;
import core.models.observers.Observable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight extends Observable implements Cloneable {

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

    // Constructor para vuelo con escala
    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation,
            LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival,
            int hoursDurationScale, int minutesDurationScale) {
        super(new FlightController());
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
    public String getId() {
        return id;
    }

    public Plane getPlane() {
        return plane;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    public void addPassenger(Passenger passenger) {// es un setter
        this.passengers.add(passenger);
        notifyObserver(null, "FlightUpload");
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
        notifyObserver(null, "FlightUpload");
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
    

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update( object, type);
    }

    @Override
    public Flight clone(){
        Flight clone = new Flight(this.id, this.plane, this.departureLocation, this.scaleLocation, this.arrivalLocation, this.departureDate, this.hoursDurationArrival, this.minutesDurationArrival, this.hoursDurationScale, this.minutesDurationScale );
        clone.getPassengers().addAll(passengers);
        return clone;
    }
    
    

}
