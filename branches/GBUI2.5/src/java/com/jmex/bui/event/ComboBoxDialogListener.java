package com.jmex.bui.event;

import com.jmex.bui.BComponent;
import com.jmex.bui.UserResponse;

/**
 * ComboBoxDialogListener is used for getting a response
 * from a BComboBoxDialog
 * @author Joakim Lindskog
 *
 */
public interface ComboBoxDialogListener {
	/**
     * 
     * @param response UserResponse
     * @param choice the chosen item in the combo box
     * @param source BComponent
     */
    void responseAvailable(UserResponse response, Object choice, BComponent source);
}
