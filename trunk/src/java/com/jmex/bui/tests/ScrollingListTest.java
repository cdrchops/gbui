//
// $Id: ScrollingListTest.java,v 1.3 2007/05/02 20:23:04 vivaldi Exp $
//
// BUI - a user interface library for the JME 3D engine
// Copyright (C) 2006, Pär Winzell, All Rights Reserved
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

import java.util.logging.Level;

import com.jme.util.LoggingSystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BConstants;
import com.jmex.bui.BDecoratedWindow;
import com.jmex.bui.BRootNode;
import com.jmex.bui.BScrollingList;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.layout.GroupLayout;

public class ScrollingListTest extends BaseTest
        implements BConstants {
    protected void createWindows(BRootNode root,
                                 BStyleSheet style) {
        BWindow window = new BDecoratedWindow(style, null);
        window.setLayoutManager(GroupLayout.makeVStretch());

        BScrollingList<String, BButton> list =
                new BScrollingList<String, BButton>() {
                    public BButton createComponent(String str) {
                        return new BButton(str);
                    }
                };

        window.add(list);

        root.addWindow(window);
        window.setSize(400, 400);
        window.setLocation(25, 25);

        for (int i = 0; i < 100; i++) {
            list.addValue("Item #" + i, true);
        }
    }

    public static void main(String[] args) {
        LoggingSystem.getLogger().setLevel(Level.WARNING);
        ScrollingListTest test = new ScrollingListTest();
        test.start();
    }
}
