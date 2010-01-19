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

import java.util.HashMap;
import java.util.Map;

/**
 * Removed from BStyleSheet and made into its own class
 *
 * @author torr
 * @since Oct 9, 2008 - 11:27:50 AM
 */
public class Rule {
    public String styleClass;

    public String pseudoClass;

    public Map<String, Object> properties = new HashMap<String, Object>(1);

    public Object get(final HashMap rules, final String key) {
        final Object value = properties.get(key);
        if (value != null) {
            return value;
        }

        final Rule prule = (Rule) properties.get("parent");
        return prule != null
                 ? prule.get(rules, key)
                 : null;
    }

    @Override
    // from Object
    public String toString() {
        return "[class=" + styleClass + ", pclass=" + pseudoClass + "]";
    }
}