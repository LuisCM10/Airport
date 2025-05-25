/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.observers.Observable;
import core.models.observers.Observer;
import core.controllers.utils.SortLocations;
import core.models.storage.LocationStorage;
import core.views.AirportFrame;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class LocationController extends Observable implements Observer {
    
    private static LocationController instance;

    public static Response createLocation(String airportId, String airportName, String airportCity, String airportCountry, String airportLatitude, String airportLongitude) {
        try {
            double airportLatitudeDouble, airportLongitudeDouble;
            if (airportId.length() != 3) {
                return new Response("Airport id must have 3 letters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 3; i++) {
                char c = airportId.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Airport id must have a 3 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            if (airportName.equals("")) {
                return new Response("Airport name must be not empty", Status.BAD_REQUEST);
            }
            if (airportCity.equals("")) {
                return new Response("Airport city must be not empty", Status.BAD_REQUEST);
            }
            if (airportCountry.equals("")) {
                return new Response("Airport country must be not empty", Status.BAD_REQUEST);
            }

            try {
                airportLatitudeDouble = Double.parseDouble(airportLatitude);
                if (airportLatitude.contains(".")) {
                    if (airportLatitude.substring(airportLatitude.indexOf(".") + 1, airportLatitude.length()).length() > 4) {
                        return new Response("Airport latitude must have at most 4 decimal places", Status.BAD_REQUEST);
                    }
                }
                if (airportLatitudeDouble < -90 || airportLatitudeDouble > 90) {
                    return new Response("Airport latitude must be a number between -90 and 90", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Airport latitude must be numeric", Status.BAD_REQUEST);
            }
            try {
                airportLongitudeDouble = Double.parseDouble(airportLongitude);
                if (airportLongitude.contains(".")) {
                    if (airportLongitude.substring(airportLongitude.indexOf(".") + 1, airportLongitude.length()).length() > 4) {
                        return new Response("Airport longitude must have at most 4 decimal places", Status.BAD_REQUEST);
                    }
                }
                if (airportLongitudeDouble < -180 || airportLongitudeDouble > 180) {
                    return new Response("Airport longitude must be a number between -180 and 180", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Airport longitude must be numeric", Status.BAD_REQUEST);
            }
            LocationStorage storage = LocationStorage.getInstance();
            if (!storage.add(new Location(airportId, airportName, airportCity, airportCountry, airportLatitudeDouble, airportLongitudeDouble))) {
                return new Response("A location with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Location create succesfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response readLocation(String id) {
        try {
            if (id.length() != 3) {
                return new Response("Airport id must have 3 letters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 3; i++) {
                char c = id.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Airport id must have a 3 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            LocationStorage storage = LocationStorage.getInstance();
            Location location = storage.get(id);
            if (location == null) {
                return new Response("Location not found", Status.NOT_FOUND);
            }
            return new Response("Location found", Status.OK, location.clone());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getLocationsToTable(DefaultTableModel model) {
        try {
            LocationStorage storage = LocationStorage.getInstance();
            ArrayList<Location> locations = storage.getLocations();

            if (locations.isEmpty()) {
                return new Response("Locations is empty", Status.NOT_FOUND);
            }
            try {
                SortLocations.sortLocations(locations);
            } catch (IllegalStateException e) {
                return new Response("Sort error", Status.INTERNAL_SERVER_ERROR);
            }
            model.setRowCount(0);
            for (Location location : locations) {
                Object[] locationInfo = new Object[]{location.getAirportId(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()};
                model.addRow(locationInfo);
            }
            return new Response("Locations table updated", Status.OK, model);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getData() {
        try {
            LocationStorage storage = LocationStorage.getInstance();
            try {
                if (!storage.getDataToJSON()) {
                    return new Response("No locations information to load", Status.NOT_FOUND);
                }
            } catch (Exception ex) {
                return new Response("load Data error", Status.INTERNAL_SERVER_ERROR);
            }
            return new Response("Locations load succesfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public LocationController() {
        super(AirportFrame.getInstance());
    }

    public static LocationController getInstance() {
        if( instance == null) {
            instance = new LocationController();
        }
        return instance;
    }
    
    

    @Override
    public void update( Object arg, String type) {
        if(type.equals("LocationInfo")) {
            Location location = (Location) arg;
            Object[] locationInfo = new Object[]{location.getAirportId(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()};
            notifyObserver(locationInfo, type);
        } else {
            notifyObserver(null, type);
        }
        
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update( object, type);
    }
}
