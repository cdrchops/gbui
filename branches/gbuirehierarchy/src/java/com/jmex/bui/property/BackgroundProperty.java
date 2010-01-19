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

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BImage;
import com.jmex.bui.background.BlankBackground;
import com.jmex.bui.background.ImageBackground;
import com.jmex.bui.background.TintedBackground;
import com.jmex.bui.enumeratedConstants.ImageBackgroundMode;
import com.jmex.bui.provider.ResourceProvider;
import com.jmex.bui.util.Insets;

import java.io.IOException;

/**
 * Removed from BStyleSheet and made into its own class
 *
 * @author torr
 * @since Oct 9, 2008 - 11:28:44 AM
 */
public class BackgroundProperty extends Property {
    public String type;
    public ColorRGBA color;
    public String ipath;
    public ImageBackgroundMode scaleMode = ImageBackgroundMode.SCALE_XY;
    public Insets frame;

    // from Property
    public Object resolve(ResourceProvider rsrcprov) {
        if (type.equals("solid")) {
            return new TintedBackground(color);
        } else if (type.equals("image")) {
            BImage image;
            try {
                image = rsrcprov.loadImage(ipath);
            } catch (IOException ioe) {
                System.err.println("Failed to load background image '" + ipath + "': " + ioe);
                return new BlankBackground();
            }
            return new ImageBackground(scaleMode, image, frame);
        } else {
            return new BlankBackground();
        }
    }
}