package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.BRootNode;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.icon.BIcon;
import com.jmex.bui.util.Point;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ivicaz
 */
public final class BDragNDrop implements EventListener {
    private static final BDragNDrop INSTANCE = new BDragNDrop();

    /**
     * @return BDragNDrop
     */
    public static BDragNDrop instance() {
        return INSTANCE;
    }

    private Set<DragNDropListener> listeners;

    /**
     * Because Bcomponents that have ActionListener are eating mouse events,
     * we're unable to catch mouse down/up on those components. Therefore,
     * whenever mouse enters the component, we create a potential for drag,
     * which is kept until mouse enters another component (or no component).
     * If the user then presses mouse button, potential drag becomes actual drag
     * and drag & drop process goes on.
     */
    private BDragEvent potentialBDrag;
    private BIcon potentialDragIcon;
    private BDragEvent currentDraggingEventB;
    private BDragIconWindow BDragIconWindow;
    private Point dragIconDisplacement;

    /**
     * Constructor
     */
    private BDragNDrop() {
        BuiSystem.getRootNode().addGlobalEventListener(this);
        BDragIconWindow = new BDragIconWindow();
        dragIconDisplacement = new Point(0, 0);
    }

    /**
     * @param listener DragNDropListener
     */
    public void addDragNDropListener(final DragNDropListener listener) {
        if (listeners == null) {
            listeners = new HashSet<DragNDropListener>();
        }

        listeners.add(listener);
    }

    /**
     * @param listener DragNDropListener
     */
    public void removeDragNDropListener(final DragNDropListener listener) {
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    /**
     *
     */
    private void detachNotifierWindow() {
        BRootNode rootNode = BuiSystem.getRootNode();
        if (rootNode.getAllWindows().contains(BDragIconWindow)) {
            rootNode.removeWindow(BDragIconWindow);
        }
    }

    /**
     * @return boolean
     */
    public boolean isDragging() {
        return currentDraggingEventB != null;
    }

    /**
     *
     */
    private void fireDragInitiated() {
        if (listeners != null) {
            for (DragNDropListener listener : listeners) {
                listener.dragInitiated(this, currentDraggingEventB);
            }
        }
    }

    /**
     * @param eventB BDropEvent
     */
    private void fireDropped(final BDropEvent eventB) {
        if (listeners != null) {
            for (DragNDropListener listener : listeners) {
                listener.dropped(this, eventB);
            }
        }
    }

    /**
     * @param source     BComponent
     * @param dragObject Object
     * @param dragIcon   BIcon
     */
    public void setPotentialDrag(final BComponent source,
                                 final Object dragObject,
                                 final BIcon dragIcon) {
        if (potentialBDrag != null
            && potentialBDrag.getSource().equals(source)) {// Skip constant object creation
            return;
        }

        potentialBDrag = new BDragEvent(source, dragObject);
        potentialDragIcon = dragIcon;
    }

    /**
     * @param source BComponent
     */
    public void removePotentialDrag(final BComponent source) {
        if (potentialBDrag != null
            && potentialBDrag.getSource().equals(source)) {
            potentialBDrag = null;
        }
    }

    /**
     * @return Point
     */
    public Point getDragIconDisplacement() {
        return dragIconDisplacement;
    }

    /**
     * @param x int
     * @param y int
     */
    public void setDragIconDisplacement(final int x, final int y) {
        dragIconDisplacement.x = x;
        dragIconDisplacement.y = y;
    }

    /**
     * @param event BEvent
     */
    public void eventDispatched(final BEvent event) {
        if (event instanceof MouseEvent) {
            final MouseEvent e = (MouseEvent) event;
            if (leftButtonPressed(e) && potentialBDrag != null) {
                startDrag(potentialBDrag);
                updateIconWindowLocation(e);
            } else if (leftButtonReleased(e) && isDragging()) {
                drop(e.getX(), e.getY());
            } else if (isDragging() && mouseIsMovingWithButtonDown(e)) {
                updateIconWindowLocation(e);
            }
        }
    }

    /**
     * @param e MouseEvent
     * @return boolean
     */
    private boolean leftButtonReleased(final MouseEvent e) {
        return e.getType() == MouseEvent.MOUSE_RELEASED
               && e.getButton() == MouseEvent.BUTTON1;
    }

    /**
     * @param e MouseEvent
     * @return boolean
     */
    private boolean leftButtonPressed(final MouseEvent e) {
        return e.getType() == MouseEvent.MOUSE_PRESSED
               && e.getButton() == MouseEvent.BUTTON1;
    }

    /**
     * @param e MouseEvent
     * @return boolean
     */
    private boolean mouseIsMovingWithButtonDown(final MouseEvent e) {
        return e.getType() == MouseEvent.MOUSE_DRAGGED;
    }

    /**
     * @param e MouseEvent
     */
    private void updateIconWindowLocation(final MouseEvent e) {
        BDragIconWindow.setLocation(e.getX() + dragIconDisplacement.x, e.getY() + dragIconDisplacement.y);
    }

    /**
     * @param eventB BDragEvent
     */
    private void startDrag(final BDragEvent eventB) {
        currentDraggingEventB = eventB;
        attachNotifierWindowToMouse();
        fireDragInitiated();
    }

    /**
     *
     */
    private void attachNotifierWindowToMouse() {
        if (BDragIconWindow != null) {
            BDragIconWindow.setIcon(potentialDragIcon);
        }

        BuiSystem.getRootNode().addWindow(BDragIconWindow);
    }

    /**
     * @param x int
     * @param y int
     */
    private void drop(final int x, final int y) {
        detachNotifierWindow();

        // Set me to null only if component below is not draggable
        // Determine that by figuring if component set itself as potential drag
        // This is necesarry precaution in case we drop on invalid target, like
        // outside of window
        if (potentialBDrag == null
            || !potentialBDrag.getSource().equals(notifyHitComponent(x, y))) {
            potentialBDrag = null;
        }

        currentDraggingEventB = null;
    }

    /**
     * @param x int
     * @param y int
     * @return BComponent
     */
    private BComponent notifyHitComponent(final int x, final int y) {
        final Iterable<BWindow> allWindows = BuiSystem.getRootNode().getAllWindows();
        for (BWindow allWindow : allWindows) {
            final BComponent hitComponent = allWindow.getHitComponent(x, y);
            if (hitComponent != null) {
                final BDropEvent BDropEvent = new BDropEvent(hitComponent, currentDraggingEventB);
                hitComponent.dispatchEvent(BDropEvent);
                fireDropped(BDropEvent);

                return hitComponent;
            }
        }

        // Don't crash here because we need ability not to hit anything
        return null;
    }

    public interface DragNDropListener {
        /**
         * @param container BDragNDrop
         * @param eventB    BDragEvent
         */
        void dragInitiated(final BDragNDrop container, final BDragEvent eventB);

        /**
         * @param container BDragNDrop
         * @param eventB    BDropEvent
         */
        void dropped(final BDragNDrop container, final BDropEvent eventB);
    }
}
