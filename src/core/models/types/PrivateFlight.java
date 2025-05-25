/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.types;

/**
 *
 * @author Usuario
 */
public class PrivateFlight implements FlightType {
    @Override
    public double calculateFare(double basePrice) {
        return basePrice * 2.5;
    }

    @Override
    public String getTypeName(){
        return "Privado";
    }
}
