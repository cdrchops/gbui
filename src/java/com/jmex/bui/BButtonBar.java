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
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.HGroupLayout;
import com.jmex.bui.layout.Justification;
import com.jmex.bui.layout.Policy;
import com.jmex.bui.util.Dimension;

import java.util.ArrayList;

public class BButtonBar extends BContainer {
    private static final Dimension DEFAULT_SIZE = new Dimension(50, 30);
    private ArrayList<BButton> buttons = new ArrayList<BButton>(2);
    private DialogOptions dialogOptions;

    public BButtonBar(String _name, DialogOptions options) {
        super(_name, new HGroupLayout(Justification.CENTER, Policy.EQUALIZE));
        dialogOptions = options;
        createButtons();
    }

    public void setButtonListener(ActionListener listener) {
        for (BButton button : buttons) {
            button.addListener(listener);
        }
    }

    private void createButtons() {
        BButton button;
        if (dialogOptions != null) {
            switch (dialogOptions) {
                case CANCEL:
                    button = new BButton("Cancel", UserResponse.CANCEL.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    break;
                case OK:
                    button = new BButton("OK", UserResponse.OK.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    break;
                case YES_NO:
                    button = new BButton("Yes", UserResponse.YES.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    button = new BButton("No", UserResponse.NO.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    break;
                case OK_CANCEL:
                	button = new BButton("OK", UserResponse.OK.toString());
                    button.setSize(50, 12);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    button = new BButton("Cancel", UserResponse.CANCEL.toString());
                    button.setSize(50, 12);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    break;
                case YES_CANCEL:
                    button = new BButton("Yes", UserResponse.YES.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    button = new BButton("Cancel", UserResponse.CANCEL.toString());
                    button.setSize(50, 12);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    break;
                case YES_NO_CANCEL:
                    button = new BButton("Yes", UserResponse.YES.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    button = new BButton("No", UserResponse.NO.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    button = new BButton("Cancel", UserResponse.CANCEL.toString());
                    button.setPreferredSize(DEFAULT_SIZE);
                    button.setStyleClass("dialogbutton");
                    add(button);
                    buttons.add(button);
                    break;
                default:
                    throw new RuntimeException("Option not implemented");
            }
        }
    }

    public DialogOptions getDialogOptions() {
        return dialogOptions;
    }
}
