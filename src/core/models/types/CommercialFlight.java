/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.types;

/**
 *
 * @author Usuario
 */
    public class CommercialFlight implements FlightType {
    @Override
    public double calculateFare(double basePrice) {
        return basePrice * 1.2;
    }

    @Override
    public String getTypeName() {
        return "Comercial";
    }
}

