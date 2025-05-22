/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Passenger;
import core.models.Flight;
import java.time.LocalDate;
import java.time.Period;

public class PassengerService {
    private final FlightManager flightManager;

    public PassengerService(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    public void addFlight(Passenger passenger, Flight flight) {
        flightManager.addFlight(passenger, flight);
    }

    public String getFullName(Passenger passenger) {
        return passenger.getFirstname() + " " + passenger.getLastname();
    }

    public String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }

    public int calculateAge(Passenger passenger) {
        LocalDate birthDate = passenger.getBirthDate();
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getNumFlights(Passenger passenger) {
        return flightManager.getFlights(passenger).size();
    }
}

