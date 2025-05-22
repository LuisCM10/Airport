/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.Storage;

/**
 *
 * @author ASUS
 */
public class PlaneController {
  
      
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
            Storage storage = Storage.getInstance();
            if(!storage.addPlane(new Plane(id, brand, model, maxCapacityInt, airline))){                
                return new Response("A plane with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Plane created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
