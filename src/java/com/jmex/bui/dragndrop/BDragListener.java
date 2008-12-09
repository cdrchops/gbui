package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.icon.BIcon;

/**
 * @author ivicaz
 */
public class BDragListener implements EventListener {
    private Object dragObject;
    private IconRequest iconRequest;
    private BComponent source;

    /**
     * @param source     BComponent
     * @param dragObject Object
     */
    public BDragListener(final BComponent source,
                         final Object dragObject) {
        this(source, dragObject, (BIcon) null);
    }

    /**
     * @param source      BComponent
     * @param dragObject  Object
     * @param iconRequest BIcon
     */
    public BDragListener(final BComponent source,
                         final Object dragObject,
                         final BIcon iconRequest) {
        this(source, dragObject, new SimpleIconRequest(iconRequest));
    }

    /**
     * @param source      BComponent
     * @param dragObject  Object
     * @param iconRequest IconRequest
     */
    public BDragListener(final BComponent source,
                         final Object dragObject,
                         final IconRequest iconRequest) {
        this.source = source;
        this.dragObject = dragObject;
        this.iconRequest = iconRequest;
    }

    /**
     * @param event BEvent
     */
    public void eventDispatched(final BEvent event) {
        if (event instanceof MouseEvent) {
            final MouseEvent e = (MouseEvent) event;
            final BDragNDrop dnd = BDragNDrop.instance();

            if (e.getType() == MouseEvent.MOUSE_ENTERED) {
                if (iconRequest.getIcon() != null) {
                    dnd.setDragIconDisplacement(-iconRequest.getIcon().getWidth() / 2, -iconRequest.getIcon().getHeight() / 2);
                    dnd.setPotentialDrag(source, dragObject, iconRequest.getIcon());
                }
            } else if (e.getType() == MouseEvent.MOUSE_MOVED) { // Yes, its stupid to constantly do that, but I have no fucking other choice!
                if (iconRequest.getIcon() != null) {
                    dnd.setDragIconDisplacement(-iconRequest.getIcon().getWidth() / 2, -iconRequest.getIcon().getHeight() / 2);
                    dnd.setPotentialDrag(source, dragObject, iconRequest.getIcon());
                }
            } else if (e.getType() == MouseEvent.MOUSE_EXITED) {
                dnd.removePotentialDrag(source);
            }
        }
    }

    /**
     *
     */
    public static interface IconRequest {
        BIcon getIcon();
    }

    private static class SimpleIconRequest implements IconRequest {
        private BIcon icon;

        /**
         * @param icon BIcon
         */
        private SimpleIconRequest(final BIcon icon) {
            this.icon = icon;
        }

        /**
         * @return BIcon
         */
        public BIcon getIcon() {
            return icon;
        }
    }
}
