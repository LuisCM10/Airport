/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.FlightController;
import core.controllers.utils.Response;
import core.models.Flight;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class FlightStorage implements Storage, uploadData {

    private static FlightStorage instance;
    private ArrayList<Flight> flights;

    public FlightStorage() {
        this.flights = new ArrayList<>();
    }

    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    @Override
    public boolean add(Object object) {
        Flight flight = (Flight) object;
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);        
        return true;
    }

    @Override
    public Flight get(Object id) {
        String idStr = (String) id;
        for (Flight flight : this.flights) {
            if (flight.getId().equals(idStr)) {
                return flight;
            }
        }
        return null;
    }

    @Override
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
    public boolean getDataToJSON() { 
        try {
            String content;
            // Leer vuelos
            content = new String(Files.readAllBytes(Paths.get("json/flights.json")));
            JSONArray flightArray = new JSONArray(content);
            for (int i = 0; i < flightArray.length(); i++) {
                JSONObject f = flightArray.getJSONObject(i);

                String id = f.getString("id");
                String planeId = f.getString("plane");
                String departureId = f.getString("departureLocation");
                String arrivalId = f.getString("arrivalLocation");
                String scaleId = f.getString("scaleLocation");
                String departureDate = f.getString("departureDate");
                String hArrival = f.getString("hoursDurationArrival");
                String mArrival = f.getString("minutesDurationArrival");
                String hScale = f.getString("hoursDurationScale");
                String mScale = f.getString("minutesDurationScale");
                Response response = FlightController.loadFlight(id, planeId, departureDate, arrivalId, scaleId, departureDate, arrivalId, arrivalId, departureDate, departureDate);
                if (response.getStatus() >= 400) {
                    return false;
                }                
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
}
