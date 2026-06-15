/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class AppleCakeFactory implements CakeIngredientFactory{

    @Override
    public Dough createDough() {
        return new SpongeDough();
    }

    @Override
    public filling createFilling() {
        return new AppleFilling();
    }

    @Override
    public BaseTopping createBaseTopping() {
        return new CinnamonDust();
    }
    
    
}
