/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class CakeFactory {
    public Cake createCake(String type) {
        Cake cake = null;
        CakeIngredientFactory ingredientFactory = null;

        if (type.equals("apple")) {
            ingredientFactory = new AppleCakeFactory();
            cake = new AppleCake(ingredientFactory);
            cake.setName("Apple Cake");
            
        } else if (type.equals("cheese")) {
            ingredientFactory = new CheeseCakeFactory();
            cake = new CheeseCake(ingredientFactory);
            cake.setName("Cheese Cake");
            
        } else if (type.equals("chocolate")) {
            ingredientFactory = new ChocolateCakeFactory();
            cake = new ChocolateCake(ingredientFactory);
            cake.setName("Chocolate Cake");
        }
        
        return cake;
    }
}
