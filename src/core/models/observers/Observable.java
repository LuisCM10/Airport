/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.observers;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public abstract class Observable {
    protected ArrayList<Observer> observers;
    
    public Observable() {
        this.observers = new ArrayList<>();
    }
    
    public boolean addObserver(Observer observer) {
        this.observers.add(observer);
        observer.setObservable(this);
        System.out.println(observer + " is observing " + this);
        return true;
    }
    
    public void notifyAll(Object object, String type) {
        switch (type) {
            case "Passenger":
                
        }
    }
}
