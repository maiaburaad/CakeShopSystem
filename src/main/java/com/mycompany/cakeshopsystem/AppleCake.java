/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cakeshopsystem;

/**
 *
 * @author Hp
 */
public class AppleCake extends Cake  {
   CakeIngredientFactory ingredientFactory; 
      
   public AppleCake(CakeIngredientFactory ingredientFactory) {
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
    return "apple Cake";
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
