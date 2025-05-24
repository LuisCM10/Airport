/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.PlaneController;
import core.models.Plane;
import core.models.observers.Observable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class PlaneStorage extends Observable implements Storage{

    private ArrayList<Plane> planes;
    private static PlaneStorage instance;
    
    public PlaneStorage() {
        super(new PlaneController());
        this.planes = new ArrayList<>();
    }    
    
    
    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }
    
    @Override
    public boolean add(Object object) {
        Plane plane = (Plane) object;
        for (Plane p : this.planes) {
            if (p.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        notifyObserver(plane, "PlaneInfo");
        return true;
    }

    @Override
    public Plane get(Object id) {
        String idStr = (String) id;
        for (Plane plane : this.planes) {
            if (plane.getId().equals(idStr)) {
                return plane;
            }
        }
        return null;
    }

    @Override
    public boolean del(Object id) {
        String idStr = (String) id;
        for (Plane plane : this.planes) {
            if (plane.getId().equals(idStr)) {
                this.planes.remove(plane);
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
