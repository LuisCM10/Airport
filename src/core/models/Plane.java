/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.PlaneController;
import core.models.Flight;
import core.models.observers.Observable;
import java.util.ArrayList;

public class Plane extends Observable implements Cloneable {

    private final String id;
    private String brand;
    private String model;
    private final int maxCapacity;
    private String airline;
    private ArrayList<Flight> flights;

    public Plane(String id, String brand, String model, int maxCapacity, String airline) {
        super(new PlaneController());
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
        this.flights = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        try {
            this.flights.add(flight);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        notifyObserver(null, "PlaneUpload");
    }

    // Setters si quieres cambiar algo
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(object, type);
    }

    @Override
    public Plane clone() {
        Plane clone = new Plane(this.id, this.brand, this.model, this.maxCapacity, this.airline);
        clone.setFlights(this.flights);
        return clone;
    }

}
