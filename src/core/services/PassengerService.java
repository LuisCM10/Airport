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

    // Agregar vuelo al pasajero
    public void addFlight(Passenger passenger, Flight flight) {
        passenger.getFlights().add(flight);
    }

    // Obtener nombre completo
    public String getFullName(Passenger passenger) {
        return passenger.getFirstname() + " " + passenger.getLastname();
    }

    // Generar número de teléfono completo con código país
    public String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }

    // Calcular edad basado en fecha de nacimiento
    public int calculateAge(Passenger passenger) {
        LocalDate birthDate = passenger.getBirthDate();
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // Obtener número de vuelos
    public int getNumFlights(Passenger passenger) {
        return passenger.getFlights().size();
    }
}

