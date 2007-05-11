//
// $Id: BInputBox.java,v 1.3 2007/05/02 21:34:01 vivaldi Exp $
//
// BUI - a user interface library for the JME 3D engine
// Copyright (C) 2005, Michael Bayne, All Rights Reserved
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package com.jmex.bui;

import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.BorderLayout;

/**
 * @author timo
 * @since 27Apr07
 */
public class BInputBox extends BContainer {
    private BTitleBar titleBar;
    private BInputMessage message;

    public BInputBox() {
        super(new BorderLayout());
    }

    public BInputBox(final BTitleBar _titleBar,
                     final BInputMessage _message) {
        this();
        titleBar = _titleBar;
        message = _message;

        addComponents();
    }

    public void addComponents() {
        if (titleBar != null) {
            add(titleBar, BorderLayout.NORTH);
        } else {
            throw new RuntimeException("TitleBar for InputBoxes must NOT be null!");
        }

        if (message != null) {
            add(message, BorderLayout.CENTER);
        } else {
            throw new RuntimeException("Message for InputBoxes must NOT be null!");
        }
    }

    public String getInputText() {
        return message.getInputText();
    }

    public void addListener(final ComponentListener listener) {
        super.addListener(listener);

        titleBar.addListener(listener);
        message.addListener(listener);
    }
}
