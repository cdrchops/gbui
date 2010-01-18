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

package com.jmex.bui.layout;

/**
 * A class used to make our policy constants type-safe.
 *
 * @author torr
 * @since Mar 24, 2009 - 9:32:57 AM
 */
public enum Policy {
    /**
     * Do not adjust the widgets on this axis.
     */
    NONE(0),

    /**
     * Stretch all the widgets to their maximum possible size on this axis.
     */
    STRETCH(1),

    /**
     * Stretch all the widgets to be equal to the size of the largest widget on this axis.
     */
    EQUALIZE(2),

    /**
     * Only valid for off-axis policy, this leaves widgets alone unless they are larger in the off-axis direction than
     * their container, in which case it constrains them to fit on the off-axis.
     */
    CONSTRAIN(3);

    int code;

    Policy(int code) {
        this.code = code;
    }
}