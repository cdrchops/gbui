//
// $Id: CollapsingWindowListenerImpl.java,v 1.4 2007/05/08 22:13:50 vivaldi Exp $
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

import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BAbstractMessageWindow;
import com.jmex.bui.listener.CollapsingWindowListener;

/**
 * @author timo
 * @since 07May07
 */
public class CollapsingWindowListenerImpl extends CollapsingWindowListener {
    protected void maximize(BAbstractMessageWindow messageWindow) {
        if (!messageWindow.isMaximized()) {
            BuiSystem.getRootNode().removeWindow(messageWindow);

            messageWindow.removeAll();

            messageWindow.setSize(messageWindow.getMaximizedWidth(), messageWindow.getMaximizedHeight());

            messageWindow.addComponents();

            messageWindow.setStyleClass(messageWindow.getMaximizedStyleClass());

            messageWindow.setMaximized(true);

            BuiSystem.getRootNode().addWindow(messageWindow);
        } else {
            minimize(messageWindow);
        }
    }

    protected void minimize(BAbstractMessageWindow messageWindow) {
        if (messageWindow.isMaximized()) {
            BuiSystem.getRootNode().removeWindow(messageWindow);

            messageWindow.removeAll();

            messageWindow.setMaximized(false);

            messageWindow.addTitleBar();

            messageWindow.setSize(messageWindow.getMinimizedWidth(), messageWindow.getMinimizedHeight());
            messageWindow.setStyleClass(messageWindow.getMinimizedStyleClass());
            BuiSystem.getRootNode().addWindow(messageWindow);
        } else {
            maximize(messageWindow);
        }
    }

    private void print(String message) {
        System.out.println(message);
    }

    protected void no(final BAbstractMessageWindow messageWindow) {
        print("no");
    }

    protected void ok(final BAbstractMessageWindow messageWindow) {
        print("ok");
    }

    protected void yes(final BAbstractMessageWindow messageWindow) {
        print("yes");
    }
}
