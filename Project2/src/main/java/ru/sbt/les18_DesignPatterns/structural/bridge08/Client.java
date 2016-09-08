package ru.sbt.les18_DesignPatterns.structural.bridge08;

/*
 *  Java Design Pattern Essentials
 *  Copyright 2010, Ability First Limited
 *
 *  This source code is provided to accompany the book and is provided AS-IS without warranty of any kind.
 *  It is intended for educational and illustrative purposes only, and may not be re-published
 *  without the express written permission of the publisher.
 */

//import chapter01.*;

public class Client {

    public static void main(String[] args) {
        	
    Engine engine = new StandardEngine(1300);
    AbstractDriverControls controls = new StandardControls(engine);
        
    controls.accelerate();
    
    }
}
