/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.PassengerController;
import core.models.observers.Observable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Passenger extends Observable implements Cloneable {

    private final long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private int countryPhoneCode;
    private long phone;
    private String country;
    private ArrayList<Flight> flights;

    public Passenger(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country) {
        super(new PassengerController());
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.countryPhoneCode = countryPhoneCode;
        this.phone = phone;
        this.country = country;
        this.flights = new ArrayList<>();
    }

    // Getters (obligatorios para PassengerService)
    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public long getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    public void addFlight (Flight flight) {
        this.flights.add(flight);
        flight.addPassenger(this);
        notifyObserver(null, "PassAddFlight");
    }

    // Setters si vas a modificar datos
    public void setFirstname(String firstname) {
        this.firstname = firstname;
        notifyObserver(null, "PassUpload");
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCountryPhoneCode(int countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }
    
    

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update( object, type);
    }

    @Override
    public Passenger clone() {
        Passenger clone = new Passenger(this.id, this.firstname, this.lastname, this.birthDate, this.countryPhoneCode, this.phone, this.country);
        clone.setFlights(this.flights);
        return clone;
    }
    
    

}
