package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;
import com.jmex.bui.event.MouseEvent;

/**
 * @author ivicaz
 */
public class DragListener implements EventListener {
    private Object dragObject;
    private BComponent source;

    public DragListener(BComponent source, Object dragObject) {
        this.source = source;
        this.dragObject = dragObject;
    }

    public void eventDispatched(BEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;
            if (e.getType() == MouseEvent.MOUSE_ENTERED)
                DragNDrop.instance().setPotentialDrag(source, dragObject);
        }
    }
}