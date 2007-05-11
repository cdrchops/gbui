//
// $Id: BaseTest2.java,v 1.8 2007/05/02 21:34:06 vivaldi Exp $
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

package com.jmex.bui.tests;

import com.jme.app.SimpleGame;
import com.jme.input.KeyBindingManager;
import com.jme.input.MouseInput;
import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BButton;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.listener.ListenerUtil;

/** A base class for our various visual tests. */
public abstract class BaseTest2 extends SimpleGame {
    protected ActionListener listener = new ActionListener() {
        public void actionPerformed(final ActionEvent event) {
            if (event.getSource() instanceof BButton) {
                String action = ListenerUtil.getActionName(event.getAction());
                String componentName = ListenerUtil.getComponentName(event.getAction(), action);
                if (action.equals("close")) {
                    BuiSystem.getWindow(componentName).dismiss();
                } else if (action.equals("ok")) {
                    BuiSystem.getWindow(componentName).dismiss();
                }
            }
        }
    };

    protected void simpleInitGame() {
        // we don't hide the cursor
        MouseInput.get().setCursorVisible(true);

//        BuiSystem.init(new PolledRootNode(timer, input), "/rsrc/style.bss");
//
//        rootNode.attachChild(BuiSystem.getRootNode());

        createWindows();

        // these just get in the way
        KeyBindingManager.getKeyBindingManager().remove("toggle_pause");
        KeyBindingManager.getKeyBindingManager().remove("toggle_wire");
        KeyBindingManager.getKeyBindingManager().remove("toggle_lights");
        KeyBindingManager.getKeyBindingManager().remove("toggle_bounds");
        KeyBindingManager.getKeyBindingManager().remove("camera_out");

        lightState.setEnabled(false);

        display.getRenderer().setBackgroundColor(ColorRGBA.gray);
    }

    protected void simpleUpdate() {}

    protected abstract void createWindows();
}