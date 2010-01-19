package com.jmex.bui.event;

import com.jmex.bui.BComponent;
import com.jmex.bui.UserResponse;

/**
 * DialogListener is used for listening for user responses on dismissable dialogs and windows.
 *
 * @author Lucian Cristian Beskid
 */
public interface DialogListener {
    /**
     * 
     * @param response UserResponse
     * @param source BComponent
     */
    void responseAvailable(UserResponse response, BComponent source);
}
