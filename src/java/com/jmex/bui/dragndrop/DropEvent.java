package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.BEvent;

/**
 * @author ivicaz
 */
public class DropEvent extends BEvent {
    private DragEvent dragEvent;

    public DropEvent(BComponent source, DragEvent dragEvent) {
        super(source, BuiSystem.getRootNode().getTickStamp());
        if (dragEvent == null)
            throw new IllegalArgumentException("dragEvent = null");
        this.dragEvent = dragEvent;
    }

    @Override
    public BComponent getSource() {
        return (BComponent) super.getSource();
    }

    public DragEvent getDragEvent() {
        return dragEvent;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("DropEvent@").append(hashCode()).append("{").
                append("source=").append(getSource()).append(", dragEvent=").append(dragEvent).append("}").toString();
    }
}
