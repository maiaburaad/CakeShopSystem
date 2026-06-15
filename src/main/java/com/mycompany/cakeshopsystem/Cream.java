/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class Cream extends CakeDecorator {
    public Cream(Cake cake){
        this.cake=cake; 
        
    }

    @Override
    public String getName() {
        return cake.getName()+ ", with cream";
    }

    @Override
    void prepare() {
        cake.prepare();
        System.out.println("Adding rich ream...");
    }
    @Override
    public String toString() {
        return cake.toString() + " + Added: Cream\n";
    }
    
}
