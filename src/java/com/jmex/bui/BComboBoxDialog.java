package com.jmex.bui;

import com.jmex.bui.enumeratedConstants.DialogOptions;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ComboBoxDialogListener;
import com.jmex.bui.headlessWindows.BTitledWindow;
import com.jmex.bui.layout.BorderLayout;
import com.jmex.bui.layout.GroupLayout;
import com.jmex.bui.layout.Justification;

public class BComboBoxDialog extends BTitledWindow {

    private ComboBoxDialogListener listener;
    private BComboBox comboBox;

    /**
     *
     * @param name String name of the component
     * @param titleBar BTitleBar
     * @param message BDialogMessage
     * @param comboBox BComboBox
     * @param options DialogOptions
     * @param style BStyleSheet stylesheet
     */
    public BComboBoxDialog(final String name,
                      final BTitleBar titleBar,
                      final BDialogMessage message,
                      final BComboBox comboBox,
                      final DialogOptions options,
                      final BStyleSheet style) {
        super(name, titleBar, null, style);
        if (message == null) {
            throw new IllegalArgumentException("The message for BDialogBox cannot be null");
        }

        final BContainer tmp = getComponentArea();

        //Add message
        tmp.setStyleClass("greymessagebg");
        tmp.setLayoutManager(new BorderLayout());
        tmp.add(message, BorderLayout.NORTH);
        
        //Add combo box
		BContainer cont = new BContainer(GroupLayout.makeVert(Justification.CENTER));
		cont.add(comboBox);
        this.comboBox = comboBox;
        tmp.add(cont, BorderLayout.CENTER);
        
        //Add buttons
        final BButtonBar buttons = new BButtonBar("", options);
        buttons.setButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                fireResponse(event);
            }
        });

        tmp.add(buttons, BorderLayout.SOUTH);
    }

    private void fireResponse(final ActionEvent event) {
        final UserResponse response = UserResponse.valueOf(event.getAction().toUpperCase());
        if (response != null && comboBox.getSelectedValue() != null) {
            if (listener != null) {
                listener.responseAvailable(response, comboBox.getSelectedValue(), this);
            }
            dismiss();
        }
    }

    public void setDialogListener(final ComboBoxDialogListener listener) {
        this.listener = listener;
    }
}
