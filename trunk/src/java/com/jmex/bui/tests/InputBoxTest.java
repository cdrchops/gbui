//
// $Id: InputBoxTest.java,v 1.6 2007/05/04 17:36:18 vivaldi Exp $
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
import com.jmex.bui.headlessWindows.InputWindow;
import com.jmex.bui.listener.ListenerUtil;

/**
 * @author timo
 * @since 27Apr07
 */
public class InputBoxTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            String action = ListenerUtil.getActionName(event.getAction());
            String componentName = ListenerUtil.getComponentName(event.getAction(), action);
            System.out.println("component name " + componentName);
            System.out.println("result is " + InputWindow.getInputText(BuiSystem.getWindow(componentName)));
        }
    };

    protected void createWindows() {
        InputWindow.createInfoInputWindow("inputTest1", "Message", listener2);
    }

    public static void main(String[] args) {
        new InputBoxTest().start();
    }
}