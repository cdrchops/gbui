package com.jmex.bui;

/**
 * The possible user responses for the standard dialog.
 * @author Lucian Cristian Beskid
 */
public enum UserResponse {
    NONE("None"),
    YES("Yes"),
    NO("No"),
    OK("OK"),
    CANCEL("Cancel");

    private String label;
    private UserResponse(String label) {
	this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
