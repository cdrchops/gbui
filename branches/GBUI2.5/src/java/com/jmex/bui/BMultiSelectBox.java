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

import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BMultiSelectBox<V> extends BScrollingList<V, BToggleButton> {
    private SelectionMode selectionMode = SelectionMode.SINGLE;
    private SelectionListener selectionListener = new SelectionListener();

    /**
     * The selection mode the list uses.
     *
     * @author Lucian Cristian Beskid
     */
    public enum SelectionMode {
        SINGLE,
        MULTIPLE
    }

    /**
     * SelectionListener makse sure only one element in the list is selected.
     *
     * @author Lucian Cristian Beskid
     */
    private static class SelectionListener implements ActionListener {
        public boolean enabled = true;
        public BToggleButton lastSelection = null;

        public void actionPerformed(ActionEvent event) {
            if (enabled) {
                BToggleButton button = (BToggleButton) event.getSource();
                if ((button != lastSelection) && button.isSelected()) {
                    if (lastSelection != null) {
                        lastSelection.setSelected(false);
                    }
                    lastSelection = button;
                } else {
                    lastSelection = null;
                }
            }
        }
    }

    public BMultiSelectBox() {
        super();
    }

    public BMultiSelectBox(Collection<V> values) {
        super(values);
    }

    public SelectionMode getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(SelectionMode _selectionMode) {
        this.selectionMode = _selectionMode;
        switch (_selectionMode) {
            case MULTIPLE:
                selectionListener.enabled = false;
                break;
            case SINGLE:
            default:
                selectionListener.enabled = true;
        }
    }

    public void removeValue(V value) {
        Entry<V, BToggleButton> entry = null;
        for (Entry<V, BToggleButton> e : _values) {
            if (e.value.equals(value)) {
                entry = e;
                break;
            }
        }
        if (entry != null) {
            if (entry.component.isSelected()) {
                selectionListener.lastSelection = null;
            }
            _values.remove(entry);
            _vport.remove(entry.component);
            _vport.invalidate();
        }
    }

    public ArrayList<V> getSelected() {
        ArrayList<V> list = new ArrayList<V>();
        for (Entry<V, BToggleButton> entry : _values) {
            if (entry.component.isSelected()) {
                list.add(entry.value);
            }
        }
        return list;
    }

    public V getFirstSelection() {
        for (Entry<V, BToggleButton> entry : _values) {
            if (entry.component.isSelected()) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return _values.isEmpty();
    }

    public void clearSelection() {
        for (Entry<V, BToggleButton> entry : _values) {
            if ((entry.component != null) && entry.component.isSelected()) {
                entry.component.setSelected(false);
            }
        }
    }

    protected final BToggleButton createComponent(V value) {
        BToggleButton component = createListEntry(value);
        component.addListener(selectionListener);
        return component;
    }

    protected abstract BToggleButton createListEntry(V value);
}
