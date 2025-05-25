/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.PassengerController;
import core.models.Passenger;
import core.models.observers.Observable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class PassengerStorage extends Observable implements Storage, uploadData {

    private ArrayList<Passenger> passengers;
    private static PassengerStorage instance;

    public PassengerStorage() {
        super(new PassengerController());
        this.passengers = new ArrayList<>();
    }

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public boolean add(Object object) {
        Passenger passenger = (Passenger) object;
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        notifyObserver(passenger, "PassInfo");
        return true;
    }

    @Override
    public Passenger get(Object id) {
        long idLong = (long) id;
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == idLong) {
                return passenger.clone();
            }
        }
        return null;
    }

    @Override
    public boolean del(Object id) {
        long idLong = (long) id;
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == idLong) {
                this.passengers.remove(passenger);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean getDataToJSON() {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get("json/passengers.json")));
        } catch (IOException ex) {
            return false;
        }
        JSONArray passengerArray = new JSONArray(content);
        for (int i = 0; i < passengerArray.length(); i++) {
            JSONObject p = passengerArray.getJSONObject(i);
            this.add(new Passenger(
                    p.getLong("id"),
                    p.getString("firstname"),
                    p.getString("lastname"),
                    LocalDate.parse(p.getString("birthDate")),
                    p.getInt("countryPhoneCode"),
                    p.getLong("phone"),
                    p.getString("country")));
        }
        return true;
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(object, type);
    }

}
