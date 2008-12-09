package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.BEvent;

/**
 * @author ivicaz
 */
public class BDropEvent extends BEvent {
    private BDragEvent BDragEvent;

    /**
     * @param source     BComponent
     * @param bDragEvent BDragEvent
     */
    public BDropEvent(final BComponent source,
                      final BDragEvent bDragEvent) {
        super(source, BuiSystem.getRootNode().getTickStamp());
        if (BDragEvent == null) {
            throw new IllegalArgumentException("BDragEvent = null");
        }

        this.BDragEvent = bDragEvent;
    }

    /**
     * @return BComponent
     */
    @Override
    public BComponent getSource() {
        return (BComponent) super.getSource();
    }

    /**
     * @return BDragEvent
     */
    public BDragEvent getDragEvent() {
        return BDragEvent;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BDropEvent@")
                .append(hashCode())
                .append("{")
                .append("source=")
                .append(getSource())
                .append(", BDragEvent=")
                .append(BDragEvent)
                .append("}");

        return sb.toString();
    }
}
