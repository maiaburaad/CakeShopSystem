/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hp
 */
public class ManagerDashboard implements OrderObserver {

    private Map<String,Integer> sales = new HashMap<>();

    @Override
   public void update(Cake cake, int orderNumber) {
   sales.put(
    cake.getBaseType(),
    sales.getOrDefault(cake.getBaseType(), 0) + 1
           
     );

    System.out.println("--[Manager Dashboard]--");
    for (String name : sales.keySet()) {
        System.out.println(name + " " + sales.get(name));
    }
        System.out.println("----------");
    


   }

}