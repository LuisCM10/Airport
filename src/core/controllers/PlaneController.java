/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.observers.Observable;
import core.models.observers.Observer;
import core.models.services.PlaneService;
import core.controllers.utils.SortPlanes;
import core.models.storage.PassengerStorage;
import core.models.storage.PlaneStorage;
import core.views.AirportFrame;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PlaneController extends Observable implements Observer{
  
      
    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            int maxCapacityInt;
            if (id.length() != 7) {
                return new Response("Id must have a 7 characters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 2; i++) {
                char c = id.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 2 initial capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            for (int i = 2; i < 7; i++) {
                char c = id.charAt(i);
                if (c < 48 || c > 57) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 7 digits after the 2 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            if (brand.equals("")) {
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }
            if (model.equals("")) {
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }
            try {
                maxCapacityInt = Integer.parseInt(maxCapacity);
                
                if (maxCapacityInt < 0) {
                    return new Response("Max capacity must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Max capacity must be numeric", Status.BAD_REQUEST);
            }
            if (airline.equals("")) {
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }
            PlaneStorage storage = PlaneStorage.getInstance();
            if(!storage.add(new Plane(id, brand, model, maxCapacityInt, airline))){                
                return new Response("A plane with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Plane created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response readPlane (String id) {
        try {
            if (id.length() != 7) {
                return new Response("Plane Id must have a 7 characters, 2 initial capital letters and 7 digits after de capital letters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 2; i++) {
                char c = id.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Plane Id must have a 2 initial capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            for (int i = 2; i < 7; i++) {
                char c = id.charAt(i);
                if (c < 48 || c > 57) { // Verificar si está en el rango de A-Z
                    return new Response("Plane Id must have a 7 digits after the 2 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            PlaneStorage storage = PlaneStorage.getInstance();
            Plane plane = storage.get(id);
            if (plane == null) {
                return new Response("Plane not found", Status.NOT_FOUND);
            }
            return new Response("Plane found", Status.OK, plane);
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getPlanesToTable (DefaultTableModel model) {
        try {            
            PlaneStorage storage = PlaneStorage.getInstance();
            ArrayList<Plane> planes = storage.getPlanes();
            
            if (planes.isEmpty()) {
                return new Response("Planes is empty" , Status.NOT_FOUND);
            }
            try {
                SortPlanes.sortPlanes(planes);
            } catch (IllegalStateException e) {
                return new Response("Sort error", Status.INTERNAL_SERVER_ERROR);
            }
            model.setRowCount(0);
            for (Plane plane : planes) {
                Object[] planeInfo = new Object[]{plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), PlaneService.getNumFlights(plane)};
                model.addRow(planeInfo);
            }
            return new Response("Planes table updated", Status.OK, model);            
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getData () {
        try {
            PlaneStorage storage = PlaneStorage.getInstance();
            if (!storage.getDataToJSON()) {
                return new Response("No information to load", Status.NO_CONTENT);
            }            
            return new Response("Planes load succesfully", Status.OK);
        } catch (Exception ex) {            
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public PlaneController() {
        super(AirportFrame.getInstance());
    }

    @Override
    public void update( Object arg, String type) {
       Plane plane = (Plane) arg;
       Object[] planeInfo = new Object[]{plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), PlaneService.getNumFlights(plane)};
       notifyObserver(planeInfo, type);
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update( object, type);
    }
}
