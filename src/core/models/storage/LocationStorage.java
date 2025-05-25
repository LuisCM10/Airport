/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.LocationController;
import core.models.Location;
import core.models.observers.Observable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class LocationStorage extends Observable implements Storage, uploadData {

    private ArrayList<Location> locations;
    private static LocationStorage instance;

    public LocationStorage() {
        super(LocationController.getInstance());
        this.locations = new ArrayList<>();
    }

    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }

    public ArrayList<Location> getLocations() {
        return locations;
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
        notifyObserver(location, "LocationInfo");
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
    public boolean getDataToJSON() {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get("json/locations.json")));
        } catch (IOException ex) {
            return false;
        }
        try {
        JSONArray locationsArray = new JSONArray(content);
        for (int i = 0; i < locationsArray.length(); i++) {
            JSONObject loc = locationsArray.getJSONObject(i);
            String airportId = loc.getString("airportId");
            String airportName = loc.getString("airportName");
            String airportCity = loc.getString("airportCity");
            String airportCountry = loc.getString("airportCountry");
            double latitude = loc.getDouble("airportLatitude");
            double longitude = loc.getDouble("airportLongitude");
            this.add(new Location(airportId, airportName, airportCity, airportCountry, latitude, longitude));
        }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update( object, type); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
