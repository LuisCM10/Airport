/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.models.services.PassengerFlightManager;
import core.models.services.PassengerService;
import core.models.services.PlaneService;
import core.models.services.PlaneFlightManager;
import core.models.services.FlightService;
import core.models.services.FlightManager;
import core.models.services.PlaneFlightManagerImpl;
import com.formdev.flatlaf.FlatDarkLaf;
import core.views.AirportFrame;
import javax.swing.UIManager;

import core.models.*;
import core.models.types.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Inicializar servicios
        PlaneFlightManager flightManager = new PlaneFlightManagerImpl();
        PlaneService planeService = new PlaneService(flightManager);
        FlightManager passengerFlightManager = new PassengerFlightManager();
        PassengerService passengerService = new PassengerService(passengerFlightManager);
        FlightService flightService = new FlightService(); // NUEVO SERVICIO

        try {
            // 1. Leer ubicaciones
            ArrayList<Location> locations = new ArrayList<>();
            String content = new String(Files.readAllBytes(Paths.get("json/locations.json")));
            JSONArray locationsArray = new JSONArray(content);
            for (int i = 0; i < locationsArray.length(); i++) {
                JSONObject loc = locationsArray.getJSONObject(i);
                String airportId = loc.getString("airportId");
                String airportName = loc.getString("airportName");
                String airportCity = loc.getString("airportCity");
                String airportCountry = loc.getString("airportCountry");
                double latitude = loc.getDouble("airportLatitude");
                double longitude = loc.getDouble("airportLongitude");

                AirportType type = (airportCountry.equals("USA")) ? new NationalAirport() : new InternationalAirport();
                Location location = new Location(airportId, airportName, airportCity, airportCountry, latitude, longitude, type);
                locations.add(location);
            }

            // 2. Leer aviones
            ArrayList<Plane> planes = new ArrayList<>();
            content = new String(Files.readAllBytes(Paths.get("json/planes.json")));
            JSONArray planesArray = new JSONArray(content);
            for (int i = 0; i < planesArray.length(); i++) {
                JSONObject p = planesArray.getJSONObject(i);
                Plane plane = new Plane(
                    p.getString("id"),
                    p.getString("brand"),
                    p.getString("model"),
                    p.getInt("maxCapacity"),
                    p.getString("airline")
                );
                planes.add(plane);
            }

            // 3. Leer pasajeros
            ArrayList<Passenger> passengers = new ArrayList<>();
            content = new String(Files.readAllBytes(Paths.get("json/passengers.json")));
            JSONArray passengerArray = new JSONArray(content);
            for (int i = 0; i < passengerArray.length(); i++) {
                JSONObject p = passengerArray.getJSONObject(i);
                Passenger passenger = new Passenger(
                    p.getLong("id"),
                    p.getString("firstname"),
                    p.getString("lastname"),
                    LocalDate.parse(p.getString("birthDate")),
                    p.getInt("countryPhoneCode"),
                    p.getLong("phone"),
                    p.getString("country")
                );
                passengers.add(passenger);
            }

            // 4. Leer vuelos
            ArrayList<Flight> flights = new ArrayList<>();
            content = new String(Files.readAllBytes(Paths.get("json/flights.json")));
            JSONArray flightArray = new JSONArray(content);
            for (int i = 0; i < flightArray.length(); i++) {
                JSONObject f = flightArray.getJSONObject(i);

                String id = f.getString("id");
                String planeId = f.getString("plane");
                String departureId = f.getString("departureLocation");
                String arrivalId = f.getString("arrivalLocation");
                String scaleId = f.optString("scaleLocation", null);

                Plane plane = planes.stream().filter(p -> p.getId().equals(planeId)).findFirst().orElse(null);
                Location departure = locations.stream().filter(l -> l.getAirportId().equals(departureId)).findFirst().orElse(null);
                Location arrival = locations.stream().filter(l -> l.getAirportId().equals(arrivalId)).findFirst().orElse(null);
                Location scale = (scaleId != null && !scaleId.isEmpty())
                        ? locations.stream().filter(l -> l.getAirportId().equals(scaleId)).findFirst().orElse(null)
                        : null;

                LocalDateTime departureDate = LocalDateTime.parse(f.getString("departureDate"));
                int hArrival = f.getInt("hoursDurationArrival");
                int mArrival = f.getInt("minutesDurationArrival");
                int hScale = f.getInt("hoursDurationScale");
                int mScale = f.getInt("minutesDurationScale");

                Flight flight = new Flight(id, plane, departure, scale, arrival, departureDate, hArrival, mArrival, hScale, mScale);
                flights.add(flight);
            }

            // 5. Relacionar vuelos con aviones y pasajeros
            for (Flight flight : flights) {
                planeService.assignFlight(flight.getPlane(), flight);
            }

            // 6. Asignar a cada pasajero su primer vuelo usando FlightService
            for (int i = 0; i < Math.min(flights.size(), passengers.size()); i++) {
                Flight flight = flights.get(i);
                Passenger passenger = passengers.get(i);

                flightService.addPassenger(flight, passenger);            // Asignar al vuelo
                passengerService.addFlight(passenger, flight);           // Asignar al pasajero

                System.out.println("✅ Pasajero " + passenger.getFirstname() + " agregado al vuelo " + flight.getId());

                // Mostrar info adicional con FlightService
                System.out.println("🛫 Salida: " + flight.getDepartureDate());
                System.out.println("🛬 Llegada estimada: " + flightService.calculateArrivalDate(flight));

                // Simular retraso de 1h15
                flightService.delayFlight(flight, 1, 15);
                System.out.println("⏱️ Nuevo horario de salida: " + flight.getDepartureDate());

                System.out.println("👤 Total pasajeros en vuelo " + flight.getId() + ": " + flightService.getNumPassengers(flight));
                System.out.println("----------------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Interfaz gráfica (opcional)
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            new AirportFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





