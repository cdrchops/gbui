package com.jmex.bui.dragndrop;

import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.BEvent;
import com.jmex.bui.event.EventListener;
import com.jmex.bui.event.MouseEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ivicaz
 */
public final class DragNDrop {
    private static final DragNDrop INSTANCE = new DragNDrop();

    public static DragNDrop instance() {
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
    private DragEvent potentialDrag;
    private DragEvent currentDraggingEvent;
    private DragNDropBuiListener dragEventListener;

    private DragNDrop() {
        dragEventListener = new DragNDropBuiListener();
        BuiSystem.getRootNode().addGlobalEventListener(dragEventListener);
    }

    public void addDragNDropListener(DragNDropListener listener) {
        if (listeners == null)
            listeners = new HashSet<DragNDropListener>();
        listeners.add(listener);
    }

    public void removeDragNDropListener(DragNDropListener listener) {
        if (listeners != null)
            listeners.remove(listener);
    }

    private void startDrag(DragEvent event) {
//    if (isDragging())
//      throw new IllegalStateException("Already dragging: " + currentDraggingEvent);
        currentDraggingEvent = event;
        fireDragInitiated();
    }

    private void drop(int x, int y) {
        if (!isDragging())
            return;

        Iterable<BWindow> allWindows = BuiSystem.getRootNode().getAllWindows();
        for (BWindow allWindow : allWindows) {
            BComponent hitComponent = allWindow.getHitComponent(x, y);
            if (hitComponent != null) {
                DropEvent dropEvent = new DropEvent(hitComponent, currentDraggingEvent);
                hitComponent.dispatchEvent(dropEvent);
                fireDropped(dropEvent);
                currentDraggingEvent = null;
                return;
            }
        }
    }

    public boolean isDragging() {
        return currentDraggingEvent != null;
    }

    private void fireDragInitiated() {
        if (listeners != null)
            for (DragNDropListener listener : listeners)
                listener.dragInitiated(this, currentDraggingEvent);
    }

    private void fireDropped(DropEvent event) {
        if (listeners != null)
            for (DragNDropListener listener : listeners)
                listener.dropped(this, event);
    }

    public void setPotentialDrag(BComponent source, Object dragObject) {
        potentialDrag = new DragEvent(source, dragObject);
    }

    public interface DragNDropListener {
        void dragInitiated(DragNDrop container, DragEvent event);

        void dropped(DragNDrop container, DropEvent event);
    }

    private class DragNDropBuiListener implements EventListener {
        public void eventDispatched(BEvent event) {
            if (event instanceof MouseEvent) {
                MouseEvent e = (MouseEvent) event;
                if (e.getType() == MouseEvent.MOUSE_PRESSED) {
                    startDrag(potentialDrag);
                    potentialDrag = null;
                } else if (e.getType() == MouseEvent.MOUSE_RELEASED) {
                    drop(e.getX(), e.getY());
                }
            }
        }
    }
}