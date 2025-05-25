/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.controllers.PlaneController;
import core.models.Plane;
import core.models.observers.Observable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class PlaneStorage extends Observable implements Storage, uploadData {

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

    public ArrayList<Plane> getPlanes() {
        return planes;
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
                return plane.clone();
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
    public boolean getDataToJSON() {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get("json/planes.json")));
        } catch (IOException ex) {
            return false;
        }
        JSONArray planesArray = new JSONArray(content);
        for (int i = 0; i < planesArray.length(); i++) {
            JSONObject p = planesArray.getJSONObject(i);
            if(!this.add(new Plane(
                    p.getString("id"),
                    p.getString("brand"),
                    p.getString("model"),
                    p.getInt("maxCapacity"),
                    p.getString("airline")
            ))) {
                return false;
            }            
        }
        return true;
    }

    @Override
    public void notifyObserver(Object object, String type) {
        observer.update(object, type);
    }
}
