package com.jmex.bui.dragndrop;

import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;

/**
 * @author ivicaz
 */
public abstract class DropListener implements EventListener {
    public void eventDispatched(BEvent event) {
        if (event instanceof DropEvent) {
            DropEvent e = (DropEvent) event;
            drop(e);
        }
    }

    protected abstract void drop(DropEvent dropEvent);
}