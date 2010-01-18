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

package com.jmex.bui.listener;

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BChatComponent;
import com.jmex.bui.BChatWindow;
import com.jmex.bui.BComponent;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BTextField;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;

/**
 * @author torr
 * @since Apr 10, 2009 - 3:07:28 PM
 */
public class ChatListener implements ActionListener {
    String inputText;
    String chatWindowName;
    BChatWindow parent;

    public ChatListener(BChatWindow parent) {
        this.parent = parent;
    }

    public void actionPerformed(final ActionEvent event) {
        final BComponent bc = ((BComponent) event.getSource()).getParent();
        if (bc instanceof BChatComponent) {

            final BChatComponent bcc = (BChatComponent) bc;
            final BTextField _input = bcc.getInput();
            final BTextArea _text = bcc.getText();
            final String inputText = _input.getText();

            if (inputText != null && !inputText.equals("")) {
                _text.appendText("You said: ", ColorRGBA.red);
                _text.appendText(inputText + "\n");
                _input.setText("");
                parent.update(bcc.getName(), inputText);
            }
        }
    }

    public void setChatWindowName(String name) {
        chatWindowName = name;
    }
}
