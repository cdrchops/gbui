//
// $Id: BDraggableWindow.java,v 1.4 2007/05/08 22:13:49 vivaldi Exp $
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

package com.jmex.bui.headlessWindows;

import com.jme.input.MouseInput;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.layout.BLayoutManager;

/**
 * @author timo
 * @since 27Apr07
 */
public class BDraggableWindow extends BWindow {
    private boolean _armed;
    private boolean _pressed;

    public BDraggableWindow(final String name,
                            final BStyleSheet style,
                            final BLayoutManager layout) {
        super(name, style, layout);
    }

    public BDraggableWindow(final BStyleSheet style,
                            final BLayoutManager layout) {
        super(style, layout);
    }

    // documentation inherited
    public boolean dispatchEvent(final BEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent mev = (MouseEvent) event;
            switch (mev.getType()) {
                case MouseEvent.MOUSE_ENTERED:
                    _armed = _pressed;
                    break; // we don't consume this event

                case MouseEvent.MOUSE_EXITED:
                    _armed = false;
                    break; // we don't consume this event
                case MouseEvent.MOUSE_DRAGGED:
                    if (isDraggable()) {
                        setBeenDrug(true);

                        int finalX = MouseInput.get().getXAbsolute() - getLocation()[0];
                        int finalY = MouseInput.get().getYAbsolute() - getLocation()[1];

                        setLocation((getLocation()[0] + finalX) - _width / 2,
                                    (getLocation()[1] + finalY) - _height);
                    }
                    break;
                case MouseEvent.MOUSE_PRESSED:
                    if (mev.getButton() == 0) {
                        _pressed = true;
                        _armed = true;
                    } else if (mev.getButton() == 1) {

                        // clicking the right mouse button after arming the
                        // component disarms it
                        _armed = false;
                    }
                    return true; // consume this event

                case MouseEvent.MOUSE_RELEASED:
                    if (_armed && _pressed) {
                        setLocation(getLocation()[0], getLocation()[1]);
                        // create and dispatch an action event
                        fireAction(mev.getWhen(), mev.getModifiers());
                        _armed = false;
                    }
                    _pressed = false;
                    return true; // consume this event
            }
        }

        return super.dispatchEvent(event);
    }

    protected void fireAction(final long when,
                              final int modifiers) {
        emitEvent(new ActionEvent(this, when, modifiers, getName() + "activate"));
    }
}
