//
// $Id: BMessageWindowUtil.java,v 1.7 2007/05/08 22:13:49 vivaldi Exp $
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

package com.jmex.bui.headlessWindows;

import com.jmex.bui.BLabel;
import com.jmex.bui.BMessage;
import com.jmex.bui.BMessageBox;
import com.jmex.bui.BStatusBar;
import com.jmex.bui.BTitleBar;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.enumeratedConstants.TitleOptions;
import com.jmex.bui.event.ComponentListener;

/**
 * @author timo
 * @since 27Apr07
 */
public class BMessageWindowUtil {
    public static void createInfoMessageWindow(final String _name,
                                               final String message,
                                               final ComponentListener listener) {
        BMessageBox db = createBasicMessageBox(_name, message);
        db.addListener(listener);

        finishWindow(_name, db);
    }

    public static void createInfoMessageWindow(final String _name,
                                               final String message,
                                               final String statusMessage,
                                               final ComponentListener listener) {
        BMessageBox db = createBasicMessageBox(_name, message, statusMessage);
        db.addListener(listener);

        finishWindow(_name, db);
    }

    private static void finishWindow(final String _name,
                                     final BMessageBox db) {
        BMessageWindow instance = new BMessageWindow(_name);
        instance.add(db);

        instance.setSize(400, 200);
        BuiSystem.getRootNode().addWindow(instance);

        instance.center();
    }

    private static BMessageWindow finishTiledWindow(final String _name,
                                                    final BMessageBox db,
                                                    final ComponentListener listener) {
        BMessageWindow instance = new BMessageWindow(_name);

        instance.add(db);
        instance.setMessageBox(db);
        instance.addListener(listener);
        instance.setSize(400, 200, 400, 30);
        return instance;
    }

    public static BMessageWindow createTiledMessageWindow(final String _name,
                                                          final BMessageBox _message,
                                                          final ComponentListener _listener) {

        _message.addListener(_listener);

        return finishTiledWindow(_name, _message, _listener);
    }

    public static void createMessageWindow(final String _name,
                                           final BMessageBox _message) {
        finishWindow(_name, _message);
    }

    public static BMessageWindow createTiledInfoMessageWindow(final String _name,
                                                              final String message,
                                                              final ComponentListener listener) {
        BMessageBox db = createBasicMessageBox(_name, message);
        db.addListener(listener);

        return finishTiledWindow(_name, db, listener);
    }

    public static BMessageWindow createTiledInfoMessageWindow(final String _name,
                                                              final String message,
                                                              final String statusMessage,
                                                              final ComponentListener listener) {
        BMessageBox db = createBasicMessageBox(_name, message, statusMessage);
        db.addListener(listener);

        return finishTiledWindow(_name, db, listener);
    }

    public static BMessageBox createMessageBox(final String name,
                                               final String title,
                                               final String titleClass,
                                               final TitleOptions options,
                                               final String message,
                                               final String status,
                                               final String statusClass) {
        BTitleBar tb = new BTitleBar(name, new BLabel(title, titleClass), options);

        BMessage _message = new BMessage(name, new BLabel(message));

        BStatusBar statusBar = null;

        if (status != null) {
            statusBar = new BStatusBar(name, status, statusClass);
        }

        return new BMessageBox(name, tb, _message, statusBar);
    }

    public static BMessageBox createBasicMessageBox(final String name,
                                                    final String message) {
        return createMessageBox(name, "Message Box", "titlemessage", TitleOptions.CLOSE, message, null, null);
    }

    public static BMessageBox createBasicMessageBox(final String name,
                                                    final String message,
                                                    final String statusBar) {
        return createMessageBox(name,
                                "Message Box",
                                "titlemessage",
                                TitleOptions.CLOSE,
                                message,
                                statusBar,
                                "statusbar");
    }
}
