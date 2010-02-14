/**
 * $Id: BScrollBar.java,v 1.2 2007/04/27 19:46:29 vivaldi Exp $
 *
 * BUI - a user interface library for the JME 3D engine
 * Copyright (C) 2005, Michael Bayne, All Rights Reserved
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
 */

package com.jmex.bui;

import com.jmex.bui.enumeratedConstants.Orientation;

import com.jmex.bui.layout.BorderLayout;
import com.jmex.bui.util.Insets;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.MouseWheelListener;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.event.ChangeListener;
import com.jmex.bui.event.ChangeEvent;

/**
 * Displays a scroll bar for all your horizontal and vertical scrolling needs.
 */
public class BScrollBar extends BContainer {
    /**
     * Creates a vertical scroll bar with the default range, value and extent.
     */
    public BScrollBar() {
        this(Orientation.VERTICAL);
    }

    /**
     * Creates a scroll bar with the default range, value and extent.
     *
     * @param orientation {@link com.jmex.bui.enumeratedConstants.Orientation}
     */
    public BScrollBar(final Orientation orientation) {
        this(orientation, 0, 100, 0, 10);
    }

    /**
     * Creates a scroll bar with the specified orientation, range, value and extent.
     *
     * @param orientation {@link com.jmex.bui.enumeratedConstants.Orientation}
     * @param min         int
     * @param value       int
     * @param extent      int
     * @param max         int
     */
    public BScrollBar(final Orientation orientation,
                      final int min,
                      final int value,
                      final int extent,
                      final int max) {
        this(orientation, new BoundedRangeModel(min, value, extent, max));
    }

    /**
     * Creates a scroll bar with the specified orientation which will interact with the supplied model.
     *
     * @param orientation {@link com.jmex.bui.enumeratedConstants.Orientation}
     * @param model       BoundedRangeModel
     */
    public BScrollBar(final Orientation orientation,
                      final BoundedRangeModel model) {
        super(new BorderLayout());
        _orient = orientation;
        _model = model;
        _model.addChangeListener(_updater);
    }

    /**
     * Returns a reference to the scrollbar's range model.
     *
     * @return BoundingRangeModel brm
     */
    public BoundedRangeModel getModel() {
        return _model;
    }

    @Override
    // documentation inherited
    public void wasAdded() {
        super.wasAdded();

        // listen for mouse wheel events
        addListener(_wheelListener = _model.createWheelListener());

        // create our buttons and backgrounds
        String oprefix = "scrollbar_" + ((_orient == Orientation.HORIZONTAL) ? "h" : "v");
        _well = new BWell(this);
        _well.setStyleClass(oprefix + "well");
        add(_well, BorderLayout.CENTER);
        
        _thumb = new BThumb(this);
        _thumb.setStyleClass(oprefix + "thumb");
        add(_thumb, BorderLayout.IGNORE);

        _less = new BButton("");
        _less.setStyleClass(oprefix + "less");
        add(_less, _orient == Orientation.HORIZONTAL ?
                   BorderLayout.WEST : BorderLayout.NORTH);
        _less.addListener(_buttoner);
        _less.setAction("less");

        _more = new BButton("");
        _more.setStyleClass(oprefix + "more");
        add(_more, _orient == Orientation.HORIZONTAL ?
                   BorderLayout.EAST : BorderLayout.SOUTH);
        _more.addListener(_buttoner);
        _more.setAction("more");
    }

    @Override
    // documentation inherited
    public void wasRemoved() {
        super.wasRemoved();

        if (_wheelListener != null) {
            removeListener(_wheelListener);
            _wheelListener = null;
        }
        if (_well != null) {
            remove(_well);
            _well = null;
        }
        if (_thumb != null) {
            remove(_thumb);
            _thumb = null;
        }
        if (_less != null) {
            remove(_less);
            _less = null;
        }
        if (_more != null) {
            remove(_more);
            _more = null;
        }
    }

    @Override
    // documentation inherited
    public BComponent getHitComponent(int mx,
                                      int my) {
        // we do special processing for the thumb
        if (_thumb.getHitComponent(mx - _x, my - _y) != null) {
            return _thumb;
        }
        return super.getHitComponent(mx, my);
    }

    /**
     * Recomputes and repositions the scroll bar thumb to reflect the current configuration of the model.
     */
    protected void update() {
        if (!isAdded()) {
            return;
        }
        Insets winsets = _well.getInsets();
        int tx = 0, ty = 0;
        int twidth = _well.getWidth() - winsets.getHorizontal();
        int theight = _well.getHeight() - winsets.getVertical();
        int range = Math.max(_model.getRange(), 1); // avoid div0
        int extent = Math.max(_model.getExtent(), 1); // avoid div0
        if (_orient == Orientation.HORIZONTAL) {
            int wellSize = twidth;
            tx = _model.getValue() * wellSize / range;
            twidth = extent * wellSize / range;
        } else {
            int wellSize = theight;
            ty = (range - extent - _model.getValue()) * wellSize / range;
            theight = extent * wellSize / range;
        }
        _thumb.setBounds(_well.getX() + winsets.left + tx,
                         _well.getY() + winsets.bottom + ty, twidth, theight);
    }

    @Override
    // documentation inherited
    protected String getDefaultStyleClass() {
        return "scrollbar";
    }

    @Override
    // documentation inherited
    protected void layout() {
        super.layout();

        // reposition our thumb
        update();
    }

    protected ChangeListener _updater = new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            update();
        }
    };

    protected ActionListener _buttoner = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            int delta = _model.getScrollIncrement();
            if (event.getAction().equals("less")) {
                _model.setValue(_model.getValue() - delta);
            } else {
                _model.setValue(_model.getValue() + delta);
            }
        }
    };

    protected BoundedRangeModel _model;
    protected Orientation _orient;

    protected BButton _less, _more;
    protected BWell _well;
    protected BThumb _thumb;

    protected MouseWheelListener _wheelListener;
}
class BWell extends BComponent {
	private BScrollBar bar;
	
	public BWell(BScrollBar bar) {
		this.bar = bar;
	}
	
	public boolean dispatchEvent(final BEvent event) {
    	if (event instanceof MouseEvent) {
            MouseEvent mev = (MouseEvent) event;
            switch (mev.getType()) {
                case MouseEvent.MOUSE_PRESSED:
                	// if we're above the thumb, scroll up by a page, if we're
                    // below, scroll down a page
                    int mx = mev.getX() - bar.getAbsoluteX(),
                            my = mev.getY() - bar.getAbsoluteY(), dv = 0;
                    if (bar._orient == Orientation.HORIZONTAL) {
                        if (mx < bar._thumb.getX()) {
                            dv = -1;
                        } else if (mx > bar._thumb.getX() + bar._thumb.getWidth()) {
                            dv = 1;
                        }
                    } else {
                        if (my < bar._thumb.getY()) {
                            dv = 1;
                        } else if (my > bar._thumb.getY() + bar._thumb.getHeight()) {
                            dv = -1;
                        }
                    }
                    if (dv != 0) {
                        dv *= Math.max(1, bar._model.getExtent());
                        bar._model.setValue(bar._model.getValue() + dv);
                    }
                    return true; // consume this event
	        }
	    }
    	
        return super.dispatchEvent(event);
	}
}
class BThumb extends BComponent implements Draggable {
	protected boolean dragging = false;
    protected boolean armed = false;
    protected int _sx,_sy, _sv;
    protected BScrollBar bar;
    
    public BThumb(BScrollBar bar) {
    	this.bar = bar;
    }
    
    public boolean dispatchEvent(final BEvent event) {
    	if (event instanceof MouseEvent) {
            MouseEvent mev = (MouseEvent) event;
            switch (mev.getType()) {
                case MouseEvent.MOUSE_ENTERED:
                    armed = true;
                    break; // we don't consume this event

                case MouseEvent.MOUSE_EXITED:
                    if (!dragging) {
                        armed = false;
                    }
                    break; // we don't consume this event
                case MouseEvent.MOUSE_DRAGGED:
                    if (dragging) {
                        int dv = 0;
                        if (bar._orient == Orientation.HORIZONTAL) {
                            int mx = mev.getX() - bar.getAbsoluteX();
                            dv = (mx - _sx) * bar._model.getRange() /
                                 (bar._well.getWidth() - bar._well.getInsets().getHorizontal());
                        } else {
                            int my = mev.getY() - bar.getAbsoluteY();
                            dv = (_sy - my) * bar._model.getRange() /
                                 (bar._well.getHeight() - bar._well.getInsets().getVertical());
                        }

                        if (dv != 0) {
                            bar._model.setValue(_sv + dv);
                        }
                        return true;
                    }
                    break;
                case MouseEvent.MOUSE_PRESSED:
                    if (mev.getButton() == 0) {
                    	dragging = true;
                    	_sv = bar._model.getValue();
                        _sx = mev.getX() - bar.getAbsoluteX();
                        _sy = mev.getY() - bar.getAbsoluteY();
                    }
                    return true; // consume this event

                case MouseEvent.MOUSE_RELEASED:
                    if (armed && dragging) {
                        // this means the windows reached its final position (window released)
                        armed = false;
                        dragging = false;
                    }
                    return true; // consume this event
            }
        }

        return super.dispatchEvent(event);
    }

	@Override
	public boolean isDragged() {
		return dragging;
	}
}
