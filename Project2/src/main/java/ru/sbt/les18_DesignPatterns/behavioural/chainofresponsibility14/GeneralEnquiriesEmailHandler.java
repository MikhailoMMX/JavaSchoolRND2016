package ru.sbt.les18_DesignPatterns.behavioural.chainofresponsibility14;

/*
 *  Java Design Pattern Essentials
 *  Copyright 2010, Ability First Limited
 *
 *  This source code is provided to accompany the book and is provided AS-IS without warranty of any kind.
 *  It is intended for educational and illustrative purposes only, and may not be re-published
 *  without the express written permission of the publisher.
 */

public class GeneralEnquiriesEmailHandler extends AbstractEmailHandler {

    protected String[] matchingWords() {
        return new String[0]; // match anything
    }

    protected void handleHere(String email) {
        System.out.println("Email handled by general enquiries.");
    }

}
