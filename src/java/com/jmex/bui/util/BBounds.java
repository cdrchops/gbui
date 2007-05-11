//
// $Id: BBounds.java,v 1.3 2007/05/02 21:34:07 vivaldi Exp $
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

package com.jmex.bui.util;

/**
 * @author timo
 * @since 27Apr07
 */
public class BBounds {
    private int width;
    private int height;
    private int x;
    private int y;

    public BBounds(final int _x,
                   final int _y,
                   final int _width,
                   final int _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int _height) {
        this.height = _height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int _width) {
        this.width = _width;
    }

    public int getX() {
        return x;
    }

    public void setX(final int _x) {
        this.x = _x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int _y) {
        this.y = _y;
    }
}
