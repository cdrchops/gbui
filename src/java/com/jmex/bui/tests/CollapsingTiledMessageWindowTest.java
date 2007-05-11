//
// $Id: CollapsingTiledMessageWindowTest.java,v 1.8 2007/05/08 22:13:50 vivaldi Exp $
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

import com.jmex.bui.BMessageBox;
import com.jmex.bui.controller.BTiledWindowController;
import com.jmex.bui.enumeratedConstants.TitleOptions;
import com.jmex.bui.headlessWindows.BMessageWindow;
import com.jmex.bui.headlessWindows.BMessageWindowUtil;

/**
 * @author timo
 * @since 27Apr07
 */
public class CollapsingTiledMessageWindowTest extends BaseTest2 {
    protected void createWindows() {
        BTiledWindowController bc = new BTiledWindowController(new CollapsingWindowListenerImpl());

        //each portion is split up so that the reformatter doesn't change it
        for (int i = 0; i < 3; i++) {
            BMessageBox mb = createMessageBox("name" + i);
            BMessageWindow mw = BMessageWindowUtil.createTiledMessageWindow("name" + i, mb, bc.getListener());
            bc.addWindow(mw);
        }
    }

    private BMessageBox createMessageBox(final String name) {
        return BMessageWindowUtil.createMessageBox(name,
                                                   "title",
                                                   "titlemessage",
                                                   TitleOptions.MIN_MAX_CLOSE,
                                                   "message",
                                                   "status",
                                                   "statusbar");
    }

    public static void main(String[] args) {
        new CollapsingTiledMessageWindowTest().start();
    }
}