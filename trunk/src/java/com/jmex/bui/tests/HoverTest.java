package com.jmex.bui.tests;

import com.jmex.bui.BButton;
import com.jmex.bui.BComboBox;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.layout.GroupLayout;

public class HoverTest extends BaseTest2 {
    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        BComboBox combo = new BComboBox();

        for (int i = 0; i < 7; i++) {
            Item item = new Item("item" + i, "itemA" + i);
            combo.addItem(item);
        }

        window.add(combo);


        for (int i = 0; i < 15; i++) {
            BButton button = new BButton("bob" + i);
            window.add(button);

        }


        window.setSize(400, 400);

        BuiSystem.addWindow(window);

        window.center();
    }

    public static void main(String[] args) {
        new HoverTest().start();
    }
}
