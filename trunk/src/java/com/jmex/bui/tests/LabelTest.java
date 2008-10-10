/*
 * $ID: LabelTest.java,v 1.2 2007/04/27 19:46:33 vivaldi Exp $
 * $COPYRIGHT:$
 *
 * BUI - a user interface library for the JME 3D engine
 * Copyright (C) 2005, Michael Bayne, All Rights Reserved
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
package com.jmex.bui.tests;

import com.jmex.bui.BConstants;
import com.jmex.bui.BContainer;
import com.jmex.bui.BDecoratedWindow;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.BRootNode;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.icon.ImageIcon;
import com.jmex.bui.layout.GroupLayout;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Does something extraordinary.
 *
 * @author Michael Bayne
 */
public class LabelTest extends BaseTest implements BConstants {
    @Override
    protected void createWindows(final BRootNode root,
                                 final BStyleSheet style) {
        final BWindow window = new BDecoratedWindow(style, null);
        window.setLayoutManager(GroupLayout.makeVStretch());

        BImage image = null;

        try {
            image = new BImage(getClass().getClassLoader().getResource("rsrc/textures/scroll_right.png"));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        final ImageIcon icon = new ImageIcon(image);
        final String[] aligns = {"left", "center", "right"};
        final int[] orients = {HORIZONTAL, VERTICAL, OVERLAPPING};

        for (int yy = 0; yy < 3; yy++) {
            final BContainer cont = new BContainer(GroupLayout.makeHStretch());
            window.add(cont);
            for (int xx = 0; xx < 3; xx++) {
                final BLabel label = new BLabel("This is a lovely label "
                                                + aligns[xx]
                                                + "/"
                                                + orients[yy]
                                                + ".",
                                                aligns[xx]);
                label.setIcon(icon);
                label.setOrientation(orients[yy]);
                cont.add(label);
            }
        }

        root.addWindow(window);
        window.setSize(400, 400);
        window.setLocation(25, 25);
    }

    public static void main(String[] args) {
        Logger.getLogger("com.jmex.bui").setLevel(Level.WARNING);
        final LabelTest test = new LabelTest();
        test.start();
    }
}
