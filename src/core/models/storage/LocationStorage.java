/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.LocationController;
import core.models.Location;
import core.models.observers.Observable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class LocationStorage extends Observable implements Storage{

    private final ArrayList<Location> locations;
    private static LocationStorage instance;

    public LocationStorage() {
        super(new LocationController());
        this.locations = new ArrayList<>();
    }
    
    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    
    @Override
    public boolean add(Object object) {
        Location location = (Location) object;
        for (Location f : this.locations) {
            if (f.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        this.locations.add(location);
        notifyObserver(location, "LocaltionInfo");
        return true;
    }

    @Override
    public Location get(Object id) {
        String idStr = (String) id;
        for (Location location : this.locations) {
            if (location.getAirportId().equals(idStr)) {
                return location;
            }
        }
        return null;
    }

    @Override
    public boolean del(Object id) {
        String idStr = (String) id;
        for (Location location : this.locations) {
            if (location.getAirportId().equals(idStr)) {
                this.locations.remove(location);
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
