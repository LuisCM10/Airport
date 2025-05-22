/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.storage.Storage;

/**
 *
 * @author ASUS
 */
public class FlightController {

    public static Response createLocation () {
        try {
            
            Storage storage = Storage.getInstance();
            if (!storage.addFlight(new Flight())){
                return new Response("A flight with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Flight create succesfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public static Response readLocation (String id) {
        try {
            
            Storage storage = Storage.getInstance();            
            Flight flight = storage.getFlight(id);
            if (flight == null) {
                return new Response("Flight not found", Status.NOT_FOUND);
            }
            return new Response("Flight found", Status.OK, flight);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
