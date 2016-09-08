package ru.sbt.les18_DesignPatterns.structural.compositer09;

/*
 *  Java Design Pattern Essentials
 *  Copyright 2010, Ability First Limited
 *
 *  This source code is provided to accompany the book and is provided AS-IS without warranty of any kind.
 *  It is intended for educational and illustrative purposes only, and may not be re-published
 *  without the express written permission of the publisher.
 */

import ru.sbt.les18_DesignPatterns.structural.compositer09.Item;

public class Part extends Item {

    public Part(String description, double cost) {
        super(description, cost);
    }

    // Empty implementation for unit parts...
    public void addItem(Item item) {}
    public void removeItem(Item item) {}
    public Item[] getItems() {return new Item[0];}

}
