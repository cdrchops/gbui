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

package com.jmex.bui.icon;

import com.jme.renderer.Renderer;

/**
 * Takes up space.
 */
public class BlankIcon extends BIcon {
    public BlankIcon(int width,
                     int height) {
        _width = width;
        _height = height;
    }

    // documentation inherited
    public int getWidth() {
        return _width;
    }

    // documentation inherited
    public int getHeight() {
        return _height;
    }

    // documentation inherited
    public void render(Renderer renderer,
                       int x,
                       int y,
                       float alpha) {
        // nothing doing
    }

    protected int _width, _height;
}
