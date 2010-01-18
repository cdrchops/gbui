/**
 *
 * $Id:$
 * $Copyright:$
 *
 * BUI - a user interface library for the JME 3D engine
 * Copyright (C) 2005-2006, Michael Bayne, All Rights Reserved
 *
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

package com.jmex.bui;

import com.jmex.bui.layout.BLayoutManager;

/**
 * @author torr
 * @since Apr 10, 2009 - 3:14:36 PM
 */
public class BChatWindow extends BWindow {
    public BChatWindow(final String name, final BStyleSheet style, final BLayoutManager layout) {
        super(name, style, layout);
    }

    public BChatWindow(final BStyleSheet style, final BLayoutManager layout) {
        super(style, layout);
    }

    public void update(String senderName, String message) {
        applyOperation(new ChatChildOp(senderName, message));
    }
}