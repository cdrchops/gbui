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

package com.jmex.bui.property;

import com.jmex.bui.BImage;
import com.jmex.bui.icon.BlankIcon;
import com.jmex.bui.icon.ImageIcon;
import com.jmex.bui.provider.ResourceProvider;

import java.io.IOException;

/**
 * Removed from BStyleSheet and made into its own class
 *
 * @author torr
 * @since Oct 9, 2008 - 11:29:01 AM
 */
public class IconProperty extends Property {
    public String type;
    public String ipath;
    public int width, height;

    // from Property
    public Object resolve(ResourceProvider rsrcprov) {
        if (type.equals("image")) {
            BImage image;
            try {
                image = rsrcprov.loadImage(ipath);
            } catch (IOException ioe) {
                System.err.println("Failed to load icon image '" + ipath + "': " + ioe);
                return new BlankIcon(10, 10);
            }
            return new ImageIcon(image);
        } else if (type.equals("blank")) {
            return new BlankIcon(width, height);
        } else {
            return new BlankIcon(10, 10);
        }
    }
}