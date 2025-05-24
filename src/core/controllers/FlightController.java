/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.observers.Observable;
import core.models.observers.Observer;
import core.models.services.FlightService;
import core.models.storage.FlightStorage;
import core.views.AirportFrame;
import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class FlightController extends Observable implements Observer{

    public static Response createFlight (String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hourDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
        try {
            Response response;
            Plane plane = null;
            Location departure = null, arrival = null, scale = null;
            int yearInt, monthInt, dayInt, hourInt, minutesInt, hourDurationArrivalInt, minutesDurationArrivalInt, hoursDurationScaleInt, minutesDurationScaleInt;
            LocalDateTime departureDate;
            
            if (id.length() != 6) {
                return new Response("Id must have a 6 characters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 3; i++) {
                char c = id.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 3 initial capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            for (int i = 3; i < 6; i++) {
                char c = id.charAt(i);
                if (c < 48 || c > 57) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 3 digits after the 3 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            if (planeId.equals("Plane")) {
                return new Response("Plane must be selected", Status.BAD_REQUEST);
            }
            response = PlaneController.readPlane(planeId);
            if (response.getStatus() >= 400) {
                return response;
            }
            if (departureLocationId.equals("Location")) {
                return new Response("Location departure must be selected", Status.BAD_REQUEST);
            }
            response = LocationController.readLocation(departureLocationId);
            if (response.getStatus() >= 400) {
                return response;
            }
            try {
                yearInt = Integer.parseInt(year);
                if (yearInt < 0) {
                    return new Response("Year must be positive", Status.BAD_REQUEST);
                }                
                if (year.length() > 4) {
                    return new Response("Year must have a maximun of 4 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Year must be numeric", Status.BAD_REQUEST);
            }
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException ex) {
                return new Response("Month must be selected", Status.BAD_REQUEST);
            }
            try {
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException ex) {
                return new Response("Day must be selected", Status.BAD_REQUEST);
            }
            try {
                hourInt = Integer.parseInt(hour);
            } catch (NumberFormatException ex) {
                return new Response("Hour must be selected", Status.BAD_REQUEST);
            }
            try {
                minutesInt = Integer.parseInt(minutes);
            } catch (NumberFormatException ex) {
                return new Response("Minutes must be selected", Status.BAD_REQUEST);
            }            
            try {
                departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minutesInt);
            } catch (DateTimeException ex) {
                return new Response("Departure date must be valid", Status.BAD_REQUEST);
            }
            if (arrivalLocationId.equals("Location")) {
                return new Response("Location Arrival must be selected", Status.BAD_REQUEST);
            }
            response = LocationController.readLocation(arrivalLocationId);
            if (response.getStatus() >= 400) {
                return response;
            }
            try {
                hourDurationArrivalInt = Integer.parseInt(hourDurationArrival);                
            } catch (NumberFormatException ex) {
                return new Response("Hour must be selected", Status.BAD_REQUEST);
            }
            try {
                minutesDurationArrivalInt = Integer.parseInt(minutesDurationArrival);                
            } catch (NumberFormatException ex) {
                return new Response("Minutes must be selected", Status.BAD_REQUEST);
            }
            if (hourDurationArrivalInt == 0 && minutesDurationArrivalInt == 0) {
                return new Response("Arrival duration must not be 00:00", Status.BAD_REQUEST);
            }
            if (!scaleLocationId.equals("Location")) {
                response = LocationController.readLocation(departureLocationId);
                if (response.getStatus() >= 400) {
                    return response;
                }
                scale = (Location) response.getObject();
            }
            if (scale != null) {
                try {
                    hoursDurationScaleInt = Integer.parseInt(hoursDurationScale);                
                } catch (NumberFormatException ex) {
                    return new Response("Hour must be selected", Status.BAD_REQUEST);
                }
                try {
                    minutesDurationScaleInt = Integer.parseInt(minutesDurationScale);                
                } catch (NumberFormatException ex) {
                    return new Response("Minutes must be selected", Status.BAD_REQUEST);
                }
                if (hoursDurationScaleInt == 0 && minutesDurationScaleInt == 0) {
                    return new Response("Scale duration must not be 00:00", Status.BAD_REQUEST); 
                }
            } else {
                if (!hoursDurationScale.equals("0")) {
                    return new Response("Scale duration hour must not be different than 0 or Scale location must be selected", Status.BAD_REQUEST);
                }
                if (!minutesDurationScale.equals("0")) {
                    return new Response("Scale duration minute must not be different than 0 or Scale location must be selected", Status.BAD_REQUEST);
                }
                hoursDurationScaleInt = 0;
                minutesDurationScaleInt = 0;
            }                                     
            FlightStorage storage = FlightStorage.getInstance();
            boolean done;
            if (scale == null) {
                done = storage.add(new Flight(id, plane, departure, arrival, departureDate, hourDurationArrivalInt, minutesDurationArrivalInt));
            } else {
                done = storage.add(new Flight(id, plane, departure, scale, arrival, departureDate, hourDurationArrivalInt, minutesDurationArrivalInt, hoursDurationScaleInt, minutesDurationScaleInt));
            }
            if (!done){
                return new Response("A flight with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Flight create succesfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    public static Response readFlight (String id) {
        try {            
            if (id.length() != 6) {
                return new Response("Id must have a 6 characters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 3; i++) {
                char c = id.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 3 initial capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            for (int i = 3; i < 6; i++) {
                char c = id.charAt(i);
                if (c < 48 || c > 57) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 3 digits after the 3 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }            
            FlightStorage storage = FlightStorage.getInstance();            
            Flight flight = storage.get(id);
            if (flight == null) {
                return new Response("Flight not found", Status.NOT_FOUND);
            }
            return new Response("Flight found", Status.OK, flight);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response delayFlight(String id, String hour, String minute) {
        try {
            int hourInt, minuteInt;
            if (id.length() != 6) {
                return new Response("Id must have a 6 characters", Status.BAD_REQUEST);
            }
            for (int i = 0; i < 3; i++) {
                char c = id.charAt(i);
                if (c < 65 || c > 90) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 3 initial capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }
            for (int i = 3; i < 6; i++) {
                char c = id.charAt(i);
                if (c < 48 || c > 57) { // Verificar si está en el rango de A-Z
                    return new Response("Id must have a 3 digits after the 3 capital letters", Status.BAD_REQUEST); // No es una letra mayúscula
                }
            }            
            FlightStorage storage = FlightStorage.getInstance();            
            Flight flight = storage.get(id);
            if (flight == null) {
                return new Response("Flight not found", Status.NOT_FOUND);
            }
            try {
                hourInt = Integer.parseInt(hour);
            } catch (NumberFormatException ex) {
                return new Response("Hour must be selected", Status.BAD_REQUEST);
            }
            try {
                minuteInt = Integer.parseInt(minute);
            } catch (NumberFormatException ex) {
                return new Response("Minutes must be selected", Status.BAD_REQUEST);
            }  
            
            if (hourInt == 0 && minuteInt == 0) {
                return new Response("Delay duration must be greater than 00:00", Status.BAD_REQUEST);
            }
            
            FlightService.delayFlight(flight, hourInt, minuteInt);
            return new Response("Delay set correctly", Status.OK);
            
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public FlightController() {
        super(AirportFrame.getInstance());
    }

    @Override
    public void update(Observable observable, Object arg, String type) {
        Flight flight = (Flight) arg;
        Object [] flightInfo = new Object[]{flight.getId(), flight.getDepartureLocation().getAirportId(), flight.getArrivalLocation().getAirportId(), (flight.getScaleLocation() == null ? "-" : flight.getScaleLocation().getAirportId()), flight.getDepartureDate(), FlightService.calculateArrivalDate(flight), flight.getPlane().getId(), FlightService.getNumPassengers(flight)};
        notifyObserver(flightInfo, type);
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(this, object, type);
    }
}
