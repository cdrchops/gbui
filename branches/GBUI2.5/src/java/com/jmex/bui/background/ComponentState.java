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

package com.jmex.bui.background;

/**
 * @author timo
 * @since Mar 18, 2008 4:37:31 PM
 */
public enum ComponentState {
    /**
     * The default component state. This is used to select the component's style pseudoclass among other things.
     */
    DEFAULT(""),

    /**
     * A component state indicating that the mouse is hovering over the component. This is used to select the
     * component's style pseudoclass among other things.
     */
    HOVER("hover"),

    /**
     * A component state indicating that the component is disabled. This is used to select the component's style
     * pseudoclass among other things.
     */
    DISABLED("disabled"),

    /**
     * Indicates that this button is in the down state.
     */
    DOWN("down");

    String statePClasses;

    ComponentState(String str) {
        statePClasses = str;
    }
}
