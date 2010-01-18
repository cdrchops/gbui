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

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.enumeratedConstants.Orientation;
import com.jmex.bui.layout.BorderLayout;
import com.jmex.bui.listener.ChatListener;

/**
 * @author torr
 * @since Apr 10, 2009 - 2:58:38 PM
 */
public class BChatComponent extends BContainer {
    private BTextArea _text = new BTextArea();
    private BTextField _input = new BTextField();
    private String chatWindowName;

    public BChatComponent(String name, ChatListener chatListener) {
        super(name);
        setLayoutManager(new BorderLayout(1, 2));

        add(_text = new BTextArea(), BorderLayout.CENTER);
        add(_input = new BTextField(), BorderLayout.SOUTH);
        add(new BScrollBar(Orientation.VERTICAL, _text.getScrollModel()),
            BorderLayout.EAST);
        add(new BScrollBar(Orientation.HORIZONTAL, 0, 25, 50, 100),
            BorderLayout.NORTH);

        _input.addListener(chatListener);
    }

    public String getChatWindowName() {
        return chatWindowName;
    }

    public void setChatWindowName(final String _chatWindowName) {
        chatWindowName = _chatWindowName;
    }

    public BTextField getInput() {
        return _input;
    }

    public BTextArea getText() {
        return _text;
    }

    public void update(String senderName, String message) {
        if (!getName().equals(senderName)) {
            _text.appendText(senderName + ": ", ColorRGBA.blue);
            _text.appendText(message + "\n");
        }
    }
}

