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

package com.jmex.bui.controller;

import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.headlessWindows.BDraggableWindow;

/**
 * @author Joakim Lindskog
 * @since 13feb10
 */
public class BDraggableWindowController implements ActionListener {
    private BDraggableWindow activeWindow;

    public void actionPerformed(ActionEvent event) {
        BComponent source = (BComponent) event.getSource();

        if (source == activeWindow) {
            return;
        }
        if (source instanceof BDraggableWindow) {
            if (event.getAction().equals(BDraggableWindow.WINDOW_ACTIVATE_ACTION)) {
                activateWindow((BDraggableWindow) source);
            }
        } else {
            BWindow window = source.getWindow();
            if (window instanceof BDraggableWindow) {
            	if (event.getAction().equals(BDraggableWindow.WINDOW_ACTIVATE_ACTION)) {
                    activateWindow((BDraggableWindow) source);
                }
            }
        }
    }

    private void activateWindow(BDraggableWindow window) {
        if (window == null) {
            return;
        }
        if (activeWindow != null) {
            activeWindow.setLayer(0);
        }
        activeWindow = window;
        activeWindow.setLayer(1);
    }
}
