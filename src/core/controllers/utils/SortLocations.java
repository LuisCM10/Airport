/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Location;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class SortLocations implements Comparator<Location> {
    @Override
    public int compare(Location l1, Location l2) {
        String comparator = l1.getAirportId();        
        return comparator.compareTo(l2.getAirportId());
    }
    
    public static ArrayList<Location> sortLocations (ArrayList<Location> locations) {
        locations.sort(new SortLocations());
        return locations;    
    }
}
