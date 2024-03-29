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

package com.jmex.bui.text;

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BConstants;
import com.jmex.bui.enumeratedConstants.TextEffect;

/**
 * Creates instances of {@link BText} using a particular technology and a particular font configuration.
 */
public abstract class BTextFactory {
    /**
     * Returns the height of our text.
     */
    public abstract int getHeight();

    /**
     * Creates a text instance using our the font configuration associated with this text factory
     * and the foreground color specified.
     *
     * @param text String
     * @param color ColorRGBA
     *
     * @return BText
     */
    public BText createText(final String text,
                            final ColorRGBA color) {
        return createText(text, color, TextEffect.NORMAL, BConstants.DEFAULT_SIZE.getValue(), null, false);
    }

    /**
     * Creates a text instance using our the font configuration associated with this text factory
     * and the foreground color, text effect and text effect color specified.
     *
     * @param text String
     * @param color ColorRGBA
     * @param effect TextEffect
     * @param effectSize int
     * @param effectColor colorRGBA
     * @param useAdvance boolean if true, the advance to the next insertion point will be included in the
     *
     * @return BText
     */
    public abstract BText createText(String text,
                                     ColorRGBA color,
                                     TextEffect effect,
                                     int effectSize,
                                     ColorRGBA effectColor,
                                     boolean useAdvance);

    /**
     * Wraps a string into a set of text objects that do not exceed the specified width.
     */
    public BText[] wrapText(final String text,
                            final ColorRGBA color,
                            final int maxWidth) {
        return wrapText(text, color, TextEffect.NORMAL, BConstants.DEFAULT_SIZE.getValue(), null, maxWidth);
    }

    /**
     * Wraps a string into a set of text objects that do not exceed the specified width.
     */
    public BText[] wrapText(final String text, final ColorRGBA color, final TextEffect effect, final int maxWidth) {
        return wrapText(text, color, effect, BConstants.DEFAULT_SIZE.getValue(), null, maxWidth);
    }

    /**
     * Wraps a string into a set of text objects that do not exceed the
     * specified width.
     */
    public abstract BText[] wrapText(String text,
                                     ColorRGBA color,
                                     TextEffect effect,
                                     int effectSize,
                                     ColorRGBA effectColor,
                                     int maxWidth);
}
