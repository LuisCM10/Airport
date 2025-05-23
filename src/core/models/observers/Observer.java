/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.observers;

/**
 *
 * @author ASUS
 */
public abstract class Observer {
    protected Observable observable;

    public Observer() {
        this.observable = null;
    }
    
    public abstract void notify(Object object, String message);
    
    public void setObservable(Observable observable) {
        this.observable = observable;
    }
    
    
}
