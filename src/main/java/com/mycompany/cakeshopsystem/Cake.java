/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public abstract class Cake {
    String name; 
    Dough dough; 
    filling filling;
    BaseTopping topping; 
    
    abstract void prepare();
    
    void bake() {
        System.out.println("Bake for 25-30 minutes, at 360");
    }

    void cut() {
        System.out.println("Cutting the cake into diagonal slices");
    }

    void box() {
        System.out.println("Packing the cake in our signature CakeShop box");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
   
    @Override
    public String toString() {
        String display = "---- " + name + " ----\n";
        if (dough != null)   display += dough.getDescription() + "\n";
        if (filling != null) display += filling.getDescription() + "\n";
        if (topping != null) display += topping.getDescription() + "\n";
        
        return display;
    }
    
    public abstract String getBaseType();

    
}
