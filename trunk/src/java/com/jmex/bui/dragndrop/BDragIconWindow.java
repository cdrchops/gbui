package com.jmex.bui.dragndrop;

import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.background.BlankBackground;
import com.jmex.bui.icon.BIcon;
import com.jmex.bui.layout.BorderLayout;

/**
 * @author ivicaz
 */
public class BDragIconWindow extends BWindow {
    private BLabel iconLabel;

    /**
     * Constructor
     */
    public BDragIconWindow() {
        super("dragging-notification-window", BuiSystem.getStyle(), new BorderLayout());
        setBackground(DEFAULT, new BlankBackground());
        iconLabel = new BLabel("");
        add(iconLabel, BorderLayout.CENTER);
    }

    /**
     * @param icon BIcon
     */
    public void setIcon(final BIcon icon) {
        iconLabel.setIcon(icon);
    }
}
