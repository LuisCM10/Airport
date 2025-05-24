/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.PassengerController;
import core.models.Passenger;
import core.models.observers.Observable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class PassengerStorage extends Observable implements Storage {
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
                return passenger;
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
    public void notifyObserver(Object object, String type) {
        observer.update(this, object, type);
    }
    
}
