package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.BEvent;

/**
 * @author ivicaz
 */
public class BDragEvent extends BEvent {
    private Object draggedObject;

    /**
     * @param source        BComponent
     * @param draggedObject Object
     */
    public BDragEvent(final BComponent source, final Object draggedObject) {
        super(source, BuiSystem.getRootNode().getTickStamp());
        this.draggedObject = draggedObject;
    }

    /**
     * @return BComponent
     */
    @Override
    public BComponent getSource() {
        return (BComponent) super.getSource();
    }

    /**
     * @return Object
     */
    public Object getDraggedObject() {
        return draggedObject;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BDragEvent@").append(hashCode()).append("{");
        builder.append("source = ").append(getSource()).append(", ");
        builder.append("draggedObject = ").append(draggedObject).append("}");

        return builder.toString();
    }
}
