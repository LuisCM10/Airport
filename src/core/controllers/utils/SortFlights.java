/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Flight;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class SortFlights implements Comparator<Flight>{

    @Override
    public int compare(Flight f1, Flight f2) {
        LocalDateTime comparator = f1.getDepartureDate();
        return comparator.compareTo(f2.getDepartureDate());
    }
    
    public static ArrayList<Flight> sortFlights (ArrayList<Flight> flights) {
        flights.sort(new SortFlights());
        return flights;    
    }
    
}
