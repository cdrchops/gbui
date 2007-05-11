//
// $Id: CollapsingWindowListener.java,v 1.3 2007/05/08 22:13:50 vivaldi Exp $
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

package com.jmex.bui.listener;

import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.headlessWindows.BAbstractMessageWindow;

/**
 * @author timo
 * @since 07May07
 */
public abstract class CollapsingWindowListener implements ActionListener {
    public void actionPerformed(final ActionEvent event) {
        String action = ListenerUtil.getActionName(event.getAction());
        String componentName = ListenerUtil.getComponentName(event.getAction(), action);
        BAbstractMessageWindow messageWindow = (BAbstractMessageWindow) BuiSystem.getWindow(componentName);

        if (action.equals("minimize")) {
            minimize(messageWindow);
        } else if (action.equals("maximize")) {
            maximize(messageWindow);
        } else if (action.equals("close")) {
            close(messageWindow);
        } else if (action.equals("yes")) {
            yes(messageWindow);
        } else if (action.equals("ok")) {
            ok(messageWindow);
        } else if (action.equals("no")) {
            no(messageWindow);
        } else if (action.equals("cancel")) {
            cancel(messageWindow);
        }
    }

    protected abstract void minimize(BAbstractMessageWindow messageWindow);

    protected abstract void maximize(BAbstractMessageWindow messageWindow);

    protected abstract void yes(BAbstractMessageWindow messageWindow);

    protected abstract void no(BAbstractMessageWindow messageWindow);

    protected abstract void ok(BAbstractMessageWindow messageWindow);

    protected void close(BAbstractMessageWindow messageWindow) {
        messageWindow.dismiss();
    }

    protected void cancel(BAbstractMessageWindow messageWindow) {
        messageWindow.dismiss();
    }
}
