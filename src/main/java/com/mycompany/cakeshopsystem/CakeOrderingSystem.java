/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class CakeOrderingSystem {
    private static volatile CakeOrderingSystem uniqueIns; 
    private List<OrderObserver> observers; 
    private CakeFactory cakeFactory; 
    private int orderNumber = 0;

    private CakeOrderingSystem() {
        observers = new ArrayList<>();
        cakeFactory = new CakeFactory(); 
    }
    
    public static CakeOrderingSystem getInstance() {
        if (uniqueIns == null) {
            synchronized (CakeOrderingSystem.class) {
                if (uniqueIns ==null) {
                    uniqueIns =new CakeOrderingSystem();
                }
            }
        }
        return uniqueIns;
    }
    
    
    public void addObserver(OrderObserver o) {
        observers.add(o);
    }

    public void removeObserver(OrderObserver o) {
        observers.remove(o);
    }

    
    public void notifyObservers(Cake cake ,int orderNumber ) {
         for (OrderObserver obs : observers) {
             obs.update(cake,orderNumber );
         }
    }
         
         
    public void takeOrder(String type, boolean addCream, boolean addSkittles, boolean addChocoChips) {
         Cake cake = cakeFactory.createCake(type);
        
        if (cake != null) {
            
            if (addCream) {
                cake = new Cream(cake);
            }
            if (addSkittles) {
                cake = new Skittles(cake);
            }
            if (addChocoChips) {
                cake = new ChocolateChips(cake);
            }

            cake.prepare();
            cake.bake();
            cake.cut();
            cake.box();
            
            System.out.println("\n>> Notification: Order Completed!");
            orderNumber++;
            notifyObservers(cake, orderNumber);
            
        } else {
            System.out.println("Sorry, cake type not available.");
        }
    }
    
    
    
    
    
    
}
