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

import com.jmex.bui.provider.ResourceProvider;

/**
 * Removed from BStyleSheet and made into its own class
 *
 * @author torr
 * @since Oct 9, 2008 - 11:28:26 AM
 */
public class FontProperty extends Property {
    public String family;
    public String style;
    public int size;

    // from Property
    public Object resolve(ResourceProvider rsrcprov) {
//             System.out.println("Resolving text factory [family=" + family +
//                                ", style=" + style + ", size=" + size + "].");
        return rsrcprov.createTextFactory(family, style, size);
    }
}