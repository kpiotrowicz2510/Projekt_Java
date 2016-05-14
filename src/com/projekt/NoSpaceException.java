package com.projekt;

/**
 * Created by usr on 2016-05-14.
 */
public class NoSpaceException extends Exception {
    public NoSpaceException(){
        super("Brak miejsca!");
    }
}
