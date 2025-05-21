/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Usuario
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonLoader {

    public static List<Location> loadLocations(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Location>>(){}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error cargando locations: " + e.getMessage());
            return null;
        }
    }

    public static List<Passenger> loadPassengers(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Passenger>>(){}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error cargando passengers: " + e.getMessage());
            return null;
        }
    }

    public static List<Plane> loadPlanes(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Plane>>(){}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error cargando planes: " + e.getMessage());
            return null;
        }
    }

    public static List<Flight> loadFlights(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Flight>>(){}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error cargando flights: " + e.getMessage());
            return null;
        }
    }
}
