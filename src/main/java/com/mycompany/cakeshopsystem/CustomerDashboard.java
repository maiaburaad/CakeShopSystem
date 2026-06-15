/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class CustomerDashboard implements OrderObserver  {

    @Override
    public void update(Cake cake, int orderNumber) {
        System.out.println("Welcome! >>Customer Display");
        System.out.println("Your order #" + orderNumber +
                       " is ready (" + cake.getName() + ").");    
        System.out.println("Please pick it up, have a good day!");
        System.out.println("--------------");
    }
    
}
