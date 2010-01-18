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

import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;

public class NavigationalController implements EventListener {
    public static final String BACK_ACTION = "close window";
    public static final String SHOW_ACTION = "show window";

    public NavigationalController(BWindow topWindow) {
        BuiSystem.getRootNode().addGlobalEventListener(this);
        BuiSystem.push(topWindow);
        showWindow(topWindow);
    }

    public void eventDispatched(BEvent event) {
        if (event instanceof ActionEvent) {
            ActionEvent actionEvent = (ActionEvent) event;
            if (BACK_ACTION.equals(actionEvent.getAction())) {
                // hide top window
                if (BuiSystem.getHistory().size() > 0) {
                    BWindow window = BuiSystem.pop();
                    if (window.isAdded()) {
                        window.dismiss();
                        // show the previous one
                        if (BuiSystem.getHistory().size() > 0) {
                            window = BuiSystem.getHistory().peek();
                            showWindow(window);
                        }
                    }
                }
            }
            // show the window
            else if (SHOW_ACTION.equals(actionEvent.getAction())) {
                BWindow window;
                if (BuiSystem.getHistory().size() > 0) {
                    window = BuiSystem.getHistory().peek();
                    if (window.isAdded()) {
                        window.dismiss();
                    }
                }
                window = (BWindow) actionEvent.getSource();
                BuiSystem.getHistory().push(window);
                showWindow(window);
            }
        }
    }

    private void showWindow(BWindow window) {
        if (!window.isAdded()) {
            BuiSystem.addWindow(window);
            window.pack();
            window.center();
        }
    }

    public void setGUIVisible(boolean visible) {
        if (BuiSystem.getHistory().size() > 0) {
            BWindow window = BuiSystem.getHistory().peek();
            if (visible) {
                showWindow(window);
            } else {
                if (window.isAdded()) {
                    window.dismiss();
                }
            }
        }
    }

    /**
     * Navigate all way back to the first window.
     */
    public void navigateToTop() {
        boolean wasAttached = false;
        if (BuiSystem.getHistory().size() > 1) {
            BWindow window = BuiSystem.getHistory().peek();
            if (window.isAdded()) {
                wasAttached = true;
                window.dismiss();
            }
        }

        while (BuiSystem.getHistory().size() > 1) {
            BuiSystem.getHistory().pop();
        }

        if (wasAttached) {
            BWindow window = BuiSystem.getHistory().peek();
            BuiSystem.addWindow(window);
            window.pack();
            window.center();
        }
    }

    public void navigateBack() {
        if (BuiSystem.getHistory().size() > 1) {
            BWindow window = BuiSystem.getHistory().pop();
            if (window.isAdded()) {
                window.dismiss();
                // show the previous window
                window = BuiSystem.getHistory().peek();
                BuiSystem.addWindow(window);
                window.pack();
                window.center();
            }
        }
    }

    public boolean isTopWindowVisible() {
        return (BuiSystem.getHistory().size() == 1);
    }
}