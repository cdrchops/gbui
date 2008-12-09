package com.jmex.bui.dragndrop;

import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;

/**
 * @author ivicaz
 */
public abstract class BDropListener implements EventListener {
    /**
     * @param event BEvent
     */
    public void eventDispatched(final BEvent event) {
        if (event instanceof BDropEvent) {
            final BDropEvent e = (BDropEvent) event;
            drop(e);
        }
    }

    /**
     * @param bDropEvent BDropEvent
     */
    protected abstract void drop(final BDropEvent bDropEvent);
}
