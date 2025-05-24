/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.observers.Observable;
import core.models.observers.Observer;
import core.models.services.FlightService;
import core.models.services.PassengerService;
import core.models.storage.PassengerStorage;
import core.views.AirportFrame;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class PassengerController extends Observable implements Observer {

    public static Response createPassenger(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {

        try {
            long idLong, phoneLong;
            int yearInt, monthInt, dayInt, phoneCodeInt;
            LocalDate birthDate;
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                } else if (id.length() > 15) {
                    return new Response("Id must have a maximum of 15 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            try {
                yearInt = Integer.parseInt(year);
                if (yearInt < 0) {
                    return new Response("Year must be positive", Status.BAD_REQUEST);
                }
                if (year.length() > 4) {
                    return new Response("Year must have a maximun of 4 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Year must be numeric", Status.BAD_REQUEST);
            }
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException ex) {
                return new Response("Month must be selected", Status.BAD_REQUEST);
            }
            try {
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException ex) {
                return new Response("Day must be selected", Status.BAD_REQUEST);
            }
            try {
                birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            } catch (DateTimeException ex) {
                return new Response("Birthdate must be valid", Status.BAD_REQUEST);
            }
            try {
                phoneCodeInt = Integer.parseInt(phoneCode);
                if (phoneCodeInt < 0) {
                    return new Response("Phone code must be positive", Status.BAD_REQUEST);
                }
                if (phoneCode.length() > 3) {
                    return new Response("Phone code must have a maximun of 3 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }
            try {
                phoneLong = Long.parseLong(phone);
                if (phoneLong < 0) {
                    return new Response("Phone must be positive", Status.BAD_REQUEST);
                } else if (phone.length() > 11) {
                    return new Response("Phone must have a maximum of 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            PassengerStorage storage = PassengerStorage.getInstance();
            if (!storage.add(new Passenger(idLong, firstname, lastname, birthDate, phoneCodeInt, phoneLong, country))) {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Passanger created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response readPassenger(String id) {
        try {
            long idLong;
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Passanger id must be positive", Status.BAD_REQUEST);
                } else if (id.length() > 15) {
                    return new Response("Passanger id must have a maximum of 15 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Passanger id must be numeric", Status.BAD_REQUEST);
            }

            PassengerStorage storage = PassengerStorage.getInstance();
            Passenger passenger = storage.get(idLong);
            if (passenger == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }
            return new Response("Person found", Status.OK, passenger);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response updatePassenger(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {

        try {
            long idLong, phoneLong;
            int yearInt, monthInt, dayInt, phoneCodeInt;
            LocalDate birthDate;
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                } else if (id.length() > 15) {
                    return new Response("Id must have a maximum of 15 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            PassengerStorage storage = PassengerStorage.getInstance();
            Passenger passenger = storage.get(idLong);
            if (passenger == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            try {
                yearInt = Integer.parseInt(year);
                if (yearInt < 0) {
                    return new Response("Year must be positive", Status.BAD_REQUEST);
                }
                if (year.length() > 4) {
                    return new Response("Year must have a maximun of 4 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Year must be numeric", Status.BAD_REQUEST);
            }
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException ex) {
                return new Response("Month must be selected", Status.BAD_REQUEST);
            }
            try {
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException ex) {
                return new Response("Day must be selected", Status.BAD_REQUEST);
            }
            try {
                birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            } catch (DateTimeException ex) {
                return new Response("Birthdate must be valid", Status.BAD_REQUEST);
            }
            try {
                phoneCodeInt = Integer.parseInt(phoneCode);
                if (phoneCodeInt < 0) {
                    return new Response("Phone code must be positive", Status.BAD_REQUEST);
                }
                if (phoneCode.length() > 3) {
                    return new Response("Phone code must have a maximun of 3 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }
            try {
                phoneLong = Long.parseLong(phone);
                if (phoneLong < 0) {
                    return new Response("Phone must be positive", Status.BAD_REQUEST);
                } else if (phone.length() > 11) {
                    return new Response("Phone must have a maximum of 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            passenger.setFirstname(firstname);
            passenger.setLastname(lastname);
            passenger.setBirthDate(birthDate);
            passenger.setCountryPhoneCode(phoneCodeInt);
            passenger.setPhone(phoneLong);
            passenger.setCountry(country);
            return new Response("Passanger update successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response addFlightPassenger(String passengerId, String flightId) {
        try {
            long idLong;
            try {
                idLong = Long.parseLong(passengerId);
                if (idLong < 0) {
                    return new Response("Passanger id must be positive", Status.BAD_REQUEST);
                } else if (passengerId.length() > 15) {
                    return new Response("Passanger id must have a maximum of 15 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Passanger id must be numeric", Status.BAD_REQUEST);
            }

            PassengerStorage storage = PassengerStorage.getInstance();
            Passenger passenger = storage.get(idLong);
            if (passenger == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }
            Response response = FlightController.readFlight(flightId);
            if (response.getStatus() >= 400) {
                return response;
            }
            Flight flight = (Flight) response.getObject();
            if (FlightService.getNumPassengers(flight) >= flight.getPlane().getMaxCapacity()) {
                return new Response("Flight is already full", Status.BAD_REQUEST);
            }
            if (passenger.getFlights().contains(flight)) {
                return new Response("Passenger already on flight", Status.BAD_REQUEST);
            }
            FlightService.addPassenger(flight, passenger);
            return new Response("Passenger succesfully added to the flight", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response ObserverPassenger(Passenger passenger) {
        return null;
    }

    public PassengerController() {
        super(AirportFrame.getInstance());
    }

    @Override
    public void update(Observable observable, Object arg, String type) {
        Passenger passenger = (Passenger) arg;
        Object[] passengerInfo = new Object[]{passenger.getId(), PassengerService.getFullName(passenger), passenger.getBirthDate(), PassengerService.calculateAge(passenger), PassengerService.generateFullPhone(passenger), passenger.getCountry(), PassengerService.getNumFlights(passenger)};
        notifyObserver(passengerInfo, type);
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(this, object, type);
    }
}
