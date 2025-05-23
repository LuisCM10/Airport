/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.observers.Observable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Storage extends Observable{

    private ArrayList<Passenger> passengers;
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;

    private static Storage instance;

    public Storage() {
        super();
        this.passengers = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }

    public Passenger getPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }

    public boolean delPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                this.passengers.remove(passenger);
                return true;
            }
        }
        return false;
    }

    public boolean addPlane(Plane plane) {
        for (Plane p : this.planes) {
            if (p.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        return true;
    }

    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    public boolean delPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                this.planes.remove(plane);
                return true;
            }
        }
        return false;
    }
    
    public boolean addFlight(Flight flight) {
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }

    public boolean delFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }
    
    public boolean addLocation(Location location) {
        for (Location f : this.locations) {
            if (f.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        this.locations.add(location);
        return true;
    }

    public Location getLocation(String id) {
        for (Location location : this.locations) {
            if (location.getAirportId().equals(id)) {
                return location;
            }
        }
        return null;
    }

    public boolean delLocation(String id) {
        for (Location location : this.locations) {
            if (location.getAirportId().equals(id)) {
                this.locations.remove(location);
                return true;
            }
        }
        return false;
    }
    
    
}
