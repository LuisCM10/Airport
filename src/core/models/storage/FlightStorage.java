/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.FlightController;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.observers.Observable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class FlightStorage extends Observable implements Storage, uploadData {

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
        flight.getPlane().addFlight(flight);
        this.flights.add(flight);        
        notifyObserver(flight, "FlightInfo");
        return true;
    }

    @Override
    public Flight get(Object id) {
        String idStr = (String) id;
        for (Flight flight : this.flights) {
            if (flight.getId().equals(idStr)) {
                return flight.clone();
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
                Plane plane = PlaneStorage.getInstance().get(planeId);
                String departureId = f.getString("departureLocation");
                Location departure = LocationStorage.getInstance().get(departureId);
                String arrivalId = f.getString("arrivalLocation");
                Location arrival = LocationStorage.getInstance().get(arrivalId);
                Location scale = null;
                if (!f.isNull("scaleLocation")) {
                    String scaleId = f.getString("scaleLocation");
                    scale = LocationStorage.getInstance().get(scaleId);
                }
                LocalDateTime departureDate = LocalDateTime.parse(f.getString("departureDate"));
                int hArrival = f.getInt("hoursDurationArrival");
                int mArrival = f.getInt("minutesDurationArrival");
                int hScale = f.getInt("hoursDurationScale");
                int mScale = f.getInt("minutesDurationScale");                
                this.add(new Flight(id, plane, departure, scale, arrival, departureDate, hArrival, mArrival, hScale, mScale));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(object, type);
    }

}
