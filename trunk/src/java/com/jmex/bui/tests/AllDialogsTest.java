//
// $Id: AllDialogsTest.java,v 1.6 2007/05/02 21:34:06 vivaldi Exp $
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
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.headlessWindows.BMessageWindowUtil;
import com.jmex.bui.headlessWindows.DialogWindow;
import com.jmex.bui.headlessWindows.InputWindow;
import com.jmex.bui.listener.ListenerUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author timo
 * @since 27Apr07
 */
public class AllDialogsTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(final ActionEvent event) {
            String action = ListenerUtil.getActionName(event.getAction());
            String componentName = ListenerUtil.getComponentName(event.getAction(), action);
            System.out.println("result is " + InputWindow.getInputText(BuiSystem.getWindow(componentName)));
            listener.actionPerformed(event);
        }
    };

    @Override
    protected void createWindows() {
        DialogWindow.createQuestionDialogWindow("qmessage1", "message", listener);
        DialogWindow.createWarningDialogWindow("warnMessage1", "message", listener);
        DialogWindow.createInfoDialogWindow("infoMessage1", "message", listener);
        DialogWindow.createErrorDialogWindow("errorMessage1", "message", listener);
        InputWindow.createInfoInputWindow("inputTest1", "Message", listener2);
        BMessageWindowUtil.createInfoMessageWindow("message", "message", listener);
        BMessageWindowUtil.createInfoMessageWindow("message2", "message", "status message", listener);
    }

    public static void main(String[] args) {
	Logger.getLogger("com.jmex.bui").setLevel(Level.WARNING);
        new AllDialogsTest().start();
    }
}
