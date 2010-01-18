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

package com.jmex.bui.event;

public class StateChangedEvent extends BEvent {
	private static final long serialVersionUID = 1L;
	private final SelectionState state;
	public enum SelectionState {Selected, Unselected};

	public StateChangedEvent(Object source, long when, SelectionState state) {
		super(source, when);
		this.state = state;
	}

	public SelectionState getType() {
		return state;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(StateChangedEvent.class)) {
			StateChangedEvent event = (StateChangedEvent) obj;
			return event.state == state && event.getSource() ==  getSource();
		}
		return false;
	}

	public static StateChangedEvent create(Object source, boolean selected) {
		SelectionState state = selected?SelectionState.Selected: SelectionState.Unselected;
		return new StateChangedEvent(source, System.currentTimeMillis(), state);
	}
}
