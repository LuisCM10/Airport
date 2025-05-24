/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.observers;

/**
 *
 * @author ASUS
 */
public interface Observer  {

    public abstract void update(Observable observable, Object arg, String type);   
    
}
