package com.jmex.bui;

/**
 * The possible user responses for the standard dialog.
 *
 * @author Lucian Cristian Beskid
 */
public enum UserResponse {
    NONE,
    YES,
    NO,
    OK,
    CANCEL;

    public String toDisplay() {
        final String str = toString();        
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}