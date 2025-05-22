/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.views.AirportFrame;
import javax.swing.UIManager;
import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.types.AirportType;
import core.models.types.InternationalAirport;
import core.models.types.NationalAirport;
import core.services.FlightManager;
import core.services.PassengerFlightManager;
import core.services.PassengerService;
import core.services.PlaneFlightManager;
import core.services.PlaneFlightManagerImpl;
import core.services.PlaneService;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {
        PlaneFlightManager flightManager = new PlaneFlightManagerImpl();
        PlaneService planeService = new PlaneService(flightManager);
        FlightManager passengerFlightManager = new PassengerFlightManager();  
        PassengerService passengerService = new PassengerService(passengerFlightManager); 

        try {
            // Cargar datos de aeropuertos
            ArrayList<Location> locations = new ArrayList<>();
            String content = new String(Files.readAllBytes(Paths.get("json/locations.json")));
            JSONArray locationsArray = new JSONArray(content);
            for (int i = 0; i < locationsArray.length(); i++) {
                JSONObject loc = locationsArray.getJSONObject(i);
                String airportId = loc.getString("airportId");
                String airportName = loc.getString("airportName");
                String airportCity = loc.getString("airportCity");
                String airportCountry = loc.getString("airportCountry");
                double airportLatitude = loc.getDouble("airportLatitude");
                double airportLongitude = loc.getDouble("airportLongitude");
                AirportType airportType = (airportCountry.equals("USA")) ? new NationalAirport() : new InternationalAirport();
                Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude, airportType);
                locations.add(location);
            }

            // Cargar datos de aviones
            ArrayList<Plane> planes = new ArrayList<>();
            content = new String(Files.readAllBytes(Paths.get("json/planes.json")));
            JSONArray planesArray = new JSONArray(content);
            for (int i = 0; i < planesArray.length(); i++) {
                JSONObject planeJson = planesArray.getJSONObject(i);
                String id = planeJson.getString("id");
                String brand = planeJson.getString("brand");
                String model = planeJson.getString("model");
                int maxCapacity = planeJson.getInt("maxCapacity");
                String airline = planeJson.getString("airline");
                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                planes.add(plane);
            }

            // Cargar datos de pasajeros
            ArrayList<Passenger> passengers = new ArrayList<>();
            content = new String(Files.readAllBytes(Paths.get("json/passengers.json")));
            JSONArray passengersArray = new JSONArray(content);
            for (int i = 0; i < passengersArray.length(); i++) {
                JSONObject passengerJson = passengersArray.getJSONObject(i);
                long id = passengerJson.getLong("id");
                String firstname = passengerJson.getString("firstname");
                String lastname = passengerJson.getString("lastname");
                LocalDate birthDate = LocalDate.parse(passengerJson.getString("birthDate"));
                int countryPhoneCode = passengerJson.getInt("countryPhoneCode");
                long phone = passengerJson.getLong("phone");
                String country = passengerJson.getString("country");
                Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
                passengers.add(passenger);
            }

            // Cargar datos de vuelos
            ArrayList<Flight> flights = new ArrayList<>();
            content = new String(Files.readAllBytes(Paths.get("json/flights.json")));
            JSONArray flightsArray = new JSONArray(content);
            for (int i = 0; i < flightsArray.length(); i++) {
                JSONObject flightJson = flightsArray.getJSONObject(i);
                String id = flightJson.getString("id");
                String planeId = flightJson.getString("plane");
                String departureLocationId = flightJson.getString("departureLocation");
                String arrivalLocationId = flightJson.getString("arrivalLocation");
                String scaleLocationId = flightJson.optString("scaleLocation", null);
                LocalDateTime departureDate = LocalDateTime.parse(flightJson.getString("departureDate"));
                int hoursDurationArrival = flightJson.getInt("hoursDurationArrival");
                int minutesDurationArrival = flightJson.getInt("minutesDurationArrival");
                int hoursDurationScale = flightJson.getInt("hoursDurationScale");
                int minutesDurationScale = flightJson.getInt("minutesDurationScale");

                Plane plane = planes.stream().filter(p -> p.getId().equals(planeId)).findFirst().orElse(null);
                Location departureLocation = locations.stream().filter(l -> l.getAirportId().equals(departureLocationId)).findFirst().orElse(null);
                Location arrivalLocation = locations.stream().filter(l -> l.getAirportId().equals(arrivalLocationId)).findFirst().orElse(null);
                Location scaleLocation = (scaleLocationId != null) ? locations.stream().filter(l -> l.getAirportId().equals(scaleLocationId)).findFirst().orElse(null) : null;

                Flight flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
                flights.add(flight);
            }

            // Establecer relaciones entre objetos
    for (Flight flight : flights) {
                planeService.assignFlight(flight.getPlane(), flight);

                for (Passenger passenger : flight.getPassengers()) {
                    passengerService.addFlight(passenger, flight);  // <-- Cambiado para usar servicio
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




