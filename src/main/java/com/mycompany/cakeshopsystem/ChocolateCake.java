/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class ChocolateCake extends Cake  {
   CakeIngredientFactory ingredientFactory; 
      
   public ChocolateCake(CakeIngredientFactory ingredientFactory) {
     this.ingredientFactory = ingredientFactory;
     
    }
       
    @Override
    void prepare() {
        System.out.println("Preparing "+ name); 
        dough = ingredientFactory.createDough();
        filling = ingredientFactory.createFilling();
        topping =ingredientFactory.createBaseTopping();
    }

    @Override
    public String getBaseType() {
        return"chocolate Cake"; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
