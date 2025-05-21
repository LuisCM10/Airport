/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.types;

/**
 *
 * @author Usuario
 */
public class InternationalAirport implements AirportType {
    @Override
    public String getTypeName() {
        return "Internacional";
    }
    @Override
    public boolean allowsInternationalFlights() {
        return true;
    }
}



