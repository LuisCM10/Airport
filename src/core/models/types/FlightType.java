/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.types;

/**
 *
 * @author Usuario
 */
public interface FlightType {
    double calculateFare(double basePrice);
    String getTypeName();
}
