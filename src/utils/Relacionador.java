/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Usuario
 */
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Relacionador {
    public static List<VueloCompleto> relacionarTodo(
        List<Flight> flights,
        List<Plane> planes,
        List<Location> locations
    ) {
        List<VueloCompleto> vuelosCompletos = new ArrayList<>();

        for (Flight f : flights) {
            Plane p = buscarPlane(f.getPlane(), planes);
            Location dep = buscarLocation(f.getDepartureLocation(), locations);
            Location arr = buscarLocation(f.getArrivalLocation(), locations);
            Location scale = f.getScaleLocation() != null ? buscarLocation(f.getScaleLocation(), locations) : null;

            if (p != null && dep != null && arr != null) {
                VueloCompleto vuelo = new VueloCompleto(f, p, dep, arr, scale);
                vuelosCompletos.add(vuelo);
            } else {
                System.out.println("Error: no se pudo relacionar vuelo " + f.getId());
            }
        }

        return vuelosCompletos;
    }

    private static Plane buscarPlane(String id, List<Plane> planes) {
        for (Plane p : planes) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    private static Location buscarLocation(String code, List<Location> locations) {
        for (Location l : locations) {
            if (l.getCode().equals(code)) return l;
        }
        return null;
    }
}


