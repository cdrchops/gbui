package com.jmex.bui.tests;

import com.jmex.bui.*;
import com.jmex.bui.dragndrop.*;
import com.jmex.bui.enumeratedConstants.TitleOptions;
import com.jmex.bui.headlessWindows.BTitledWindow;
import com.jmex.bui.icon.BIcon;

/**
 * @author ivicaz
 */
public class DraggingPlayground extends AbstractBuiTest {
    protected void addGui() {
        setupWindow1(50, 200);
        setupWindow2(450, 200);
        initializeDnd();
    }

    private void initializeDnd() {
        final DragNDrop dnd = DragNDrop.instance();
        dnd.addDragNDropListener(new DragNDrop.DragNDropListener() {
            public void dragInitiated(DragNDrop container, DragEvent event) {
                System.out.println("Drag initiated: " + event);
            }

            public void dropped(DragNDrop container, DropEvent event) {
                System.out.println("Dropped: " + event);
            }
        });
    }

    private void setupWindow1(int x, int y) {
        final BTitledWindow win = new BTitledWindow("le window 1",
                new BTitleBar("lala", "Lala", TitleOptions.MIN_MAX_CLOSE),
                new BStatusBar("ohlala"),
                BuiSystem.getStyle());
        win.setLocation(x, y);
        win.setSize(320, 240);

        final BButton button = new BButton(getImageIcon("../resources/ui/items/fire-ice-claw.png"), "");
        button.addListener(new DragListener(button, new GetIcon(button)));
        button.addListener(new SwitchIconDropTarget());
        win.getComponentArea().add(button);

        BuiSystem.addWindow(win);
    }

    private void setupWindow2(int x, int y) {
        final BTitledWindow win = new BTitledWindow("le window 2",
                new BTitleBar("lala", "Lala 2", TitleOptions.MIN_MAX_CLOSE),
                new BStatusBar("ohlala 2"),
                BuiSystem.getStyle());
        win.setLocation(x, y);
        win.setSize(320, 240);


        final BButton button = new BButton("Empty");
        button.addListener(new DragListener(button, new GetIcon(button)));
        button.addListener(new SwitchIconDropTarget());
        win.getComponentArea().add(button);

        BuiSystem.addWindow(win);
    }

    private class SwitchIconDropTarget extends DropListener {
        protected void drop(DropEvent dropEvent) {
            final BComponent componentSource = dropEvent.getSource();
            if (componentSource instanceof BButton) {
                final BButton dropButton = (BButton) componentSource;
                final DragEvent dragEvent = dropEvent.getDragEvent();
                final GetIcon getIcon = (GetIcon) dragEvent.getDraggedObject();
                dropButton.setText("");
                dropButton.setIcon(getIcon.call());

                final BButton dragButton = (BButton) dragEvent.getSource();
                dragButton.setText("Empty");
                dragButton.setIcon(null);
            }
        }
    }

    private class GetIcon {
        private BButton button;

        private GetIcon(BButton button) {
            this.button = button;
        }

        public BIcon call() {
            return button.getIcon();
        }
    }

    public static void main(String[] args) {
        new DraggingPlayground().start();
    }
}
