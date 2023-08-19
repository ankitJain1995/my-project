/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foodie.gui;

/**
 *
 * @author ankitjain
 */
public class demo {
    private String name;
    private  double salary;
    
    private static void hello()
    {
        System.out.println("helllo");
    }
    }

class demo1 extends demo{
    
    public void displaysal(double salary)
    {
        System.out.println(salary);
    }
}

class test{
    public static void main(String[] args) {
        demo1 d = new demo1();
        d.displaysal(2345);
        
    }
}