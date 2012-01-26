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

package com.jmex.bui;

import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.junit.Assert.*;

import com.jmex.bui.base.BaseTest;
import com.jmex.bui.layout.GroupLayout;

public class ScrollingListTest extends BaseTest {
	private BScrollingList<String, BButton> list;

	@Test
	public void performanceCheck() throws LWJGLException {
		System.setProperty("org.lwjgl.librarypath", "D:\\java\\gbui\\lib\\lwjgl\\native\\windows");
		Display.setDisplayMode(new DisplayMode(500, 500));
		Display.setFullscreen(false);
		start();

		assertTrue(list.isShowing());
	}
	
	@Override
	protected void createWindows() {
		final BWindow window = new BDecoratedWindow(BuiSystem.getStyle(), null);

		window.setLayoutManager(GroupLayout.makeVStretch());

		list = new BScrollingList<String, BButton>() {
			@Override
			public BButton createComponent(String str) {
				return new BButton(str);
			}
		};

		window.add(list);

		BuiSystem.getRootNode().addWindow(window);
		window.setSize(400, 400);
		window.setLocation(25, 25);

		for (int i = 0; i < 100; i++) {
			list.addValue("Item #" + i, true);
		}
	}
}
