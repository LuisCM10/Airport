/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Plane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class SortPlanes implements Comparator<Plane>{

    @Override
    public int compare(Plane p1, Plane p2) {
        String comparator = p1.getId();        
        return comparator.compareTo(p2.getId());
    }
    
    public static ArrayList<Plane> sortPlanes(ArrayList<Plane> planes) {
        planes.sort(new SortPlanes());
        return planes;
    }
    
}
