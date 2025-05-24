/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.FlightController;
import core.models.Flight;
import core.models.observers.Observable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class FlightStorage extends Observable implements Storage{
    private static FlightStorage instance;
    private ArrayList<Flight> flights;

    public FlightStorage() {
        super(new FlightController());
        this.flights = new ArrayList<>();
    }     
    
    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }
    
    public boolean add(Object object) {
        Flight flight = (Flight) object;
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        notifyObserver(flight, "FlightInfo");
        return true;
    }

    public Flight get(Object id) {
        String idStr = (String) id;
        for (Flight flight : this.flights) {
            if (flight.getId().equals(idStr)) {
                return flight;
            }
        }
        return null;
    }

    public boolean del(Object id) {
        String idStr = (String) id;
        for (Flight flight : this.flights) {
            if (flight.getId().equals(idStr)) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(this, object, type);
    }
}
