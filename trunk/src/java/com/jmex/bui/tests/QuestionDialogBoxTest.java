//
// $Id: QuestionDialogBoxTest.java,v 1.5 2007/05/02 21:34:07 vivaldi Exp $
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

import com.jmex.bui.headlessWindows.DialogWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author timo
 * @since 27Apr07
 */
public class QuestionDialogBoxTest extends BaseTest2 {
    @Override
    protected void createWindows() {
        DialogWindow.createQuestionDialogWindow("qmessage1", "message", listener);
    }

    public static void main(String[] args) {
	Logger.getLogger("com.jmex.bui").setLevel(Level.WARNING);
        new QuestionDialogBoxTest().start();
    }
}