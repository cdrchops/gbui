package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.BEvent;

/**
 * @author ivicaz
 */
public class DragEvent extends BEvent {
    private Object draggedObject;

    public DragEvent(BComponent source, Object draggedObject) {
        super(source, BuiSystem.getRootNode().getTickStamp());

        this.draggedObject = draggedObject;
    }

    public BComponent getSource() {
        return (BComponent) super.getSource();
    }

    public Object getDraggedObject() {
        return draggedObject;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("DragEvent@").append(hashCode()).append("{");
        builder.append("source = ").append(getSource()).append(", ");
        builder.append("draggedObject = ").append(draggedObject).append("}");

        return builder.toString();
    }
}
