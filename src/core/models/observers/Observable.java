/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.observers;

/**
 *
 * @author ASUS
 */
public abstract class Observable {
    protected Observer observer;
    
    public Observable(Observer observer) {
        this.observer =  observer;
    }
        
    public abstract void notifyObserver(Object object, String type);
    
}
