/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

/**
 *
 * @author ASUS
 */
public interface Storage{

    public boolean add(Object object);
    public Object get(Object id);
    public boolean del(Object id);   
 
}
