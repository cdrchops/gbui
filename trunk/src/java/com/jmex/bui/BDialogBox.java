/**
 *
 * $Id:$
 * $Copyright:$
 *
 * BUI - a user interface library for the JME 3D engine
 * Copyright (C) 2005-2006, Michael Bayne, All Rights Reserved
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package com.jmex.bui;

import com.jmex.bui.enumeratedConstants.DialogOptions;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.DialogListener;
import com.jmex.bui.headlessWindows.BTitledWindow;
import com.jmex.bui.layout.BorderLayout;

public class BDialogBox extends BTitledWindow {

    private DialogListener listener;

    /**
     *
     * @param name String name of the component
     * @param titleBar BTitleBar
     * @param message BDialogMessage
     * @param options DialogOptions
     * @param style BStyleSheet stylesheet
     */
    public BDialogBox(final String name,
                      final BTitleBar titleBar,
                      final BDialogMessage message,
                      final DialogOptions options,
                      final BStyleSheet style) {
        super(name, titleBar, null, style);
        if (message == null) {
            throw new IllegalArgumentException("The message for BDialogBox cannot be null");
        }

        final BContainer tmp = getComponentArea();

        tmp.setStyleClass("greymessagebg");
        tmp.setLayoutManager(new BorderLayout());
        tmp.add(message, BorderLayout.NORTH);

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
        if (response != null) {
            if (listener != null) {
                listener.responseAvailable(response, this);
            }
            dismiss();
        }
    }

    public void setDialogListener(final DialogListener listener) {
        this.listener = listener;
    }
}
