/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Passenger;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class SortPassengers implements Comparator<Passenger>{

    @Override
    public int compare(Passenger p1, Passenger p2) {
        return Long.compare(p1.getId(), p2.getId());
    }
    
    public static ArrayList<Passenger> sortPassengers(ArrayList<Passenger> object) {
        object.sort(new SortPassengers());
        return object;
    }
    
}
