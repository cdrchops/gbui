//
// $Id: BTiledWindowController.java,v 1.7 2007/05/08 22:13:49 vivaldi Exp $
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

package com.jmex.bui.controller;

import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.headlessWindows.BAbstractMessageWindow;
import com.jmex.bui.listener.ListenerUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author timo
 * @since 27Apr07
 */
public class BTiledWindowController extends BComponent {
    private ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            handleInput(event);
        }
    };

    private List<BAbstractMessageWindow> lst = new LinkedList<BAbstractMessageWindow>();
    private ComponentListener externalListener;
    protected static int windowCount;
    protected int width;
    protected int height;

    public BTiledWindowController(ComponentListener _listener) {
        width = DisplaySystem.getDisplaySystem().getWidth() / 2;
        height = DisplaySystem.getDisplaySystem().getHeight() / 2;

        addListener(listener);
        externalListener = _listener;
    }

    public void addWindow(final BAbstractMessageWindow mw) {
        BuiSystem.getRootNode().addWindow(mw);

        lst.add(mw);

        if (windowCount == 0
            && !mw.hasBeenDrug()) {
            mw.center();
        } else {
            mw.setLocation((width - mw.getWidth() / 2) + (mw.getMinimizedHeight() * windowCount),
                           (height - mw.getHeight() / 2) - (mw.getMinimizedHeight() * windowCount));
        }

        windowCount++;
    }

    public void handleInput(final ActionEvent event) {
        Object source = event.getSource();
        String action = ListenerUtil.getActionName(event.getAction());
        String componentName = ListenerUtil.getComponentName(event.getAction(), action);

        if (source instanceof BButton) {
            processWindowButtonEvent(componentName, event);
        } else if (source instanceof BWindow) {
            processWindow(componentName);
        }
    }

    private void processWindow(final String msg) {
        for (BAbstractMessageWindow bWindow : lst) {
            if (bWindow.getName().equals(msg)) {
                bWindow.setLayer(1);
            } else {
                bWindow.setLayer(0);
            }
        }
    }

    private void processWindowButtonEvent(final String componentName,
                                          final ActionEvent event) {
        for (BAbstractMessageWindow bWindow : lst) {

            if (bWindow.getName().equals(componentName)) {
                bWindow.setLayer(1);
                event.dispatch(externalListener);
            } else {
                bWindow.setLayer(0);
            }
        }
    }

    public ComponentListener getListener() {
        return listener;
    }
}
