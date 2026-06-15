/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class ChocolateChips extends CakeDecorator {
    public ChocolateChips(Cake cake) {
        this.cake = cake;
    }

    @Override
    public String getName() {
        return cake.getName() + ", with Chocolate Chips";
    }

    @Override
    void prepare() {
        cake.prepare();
        System.out.println("Adding delicious chocolate chips...");
    }
    
    @Override
    public String toString() {
        return cake.toString() + " + Added: Chocolate Chips\n";
    }
}
