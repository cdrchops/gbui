package com.jmex.bui.tests;

import com.jmex.bui.BComboBox;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.GroupLayout;

public class ComboBoxTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.out.println(bc.getSelectedItem());
            System.out.println(bc.getSelectedValue());
        }
    };

    private BComboBox bc;

    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        bc = new BComboBox();

        Item item = new Item("this", "this2");
        bc.addItem(item);

        Item item2 = new Item("this3", "this4");
        bc.addItem(item2);

        bc.addListener(listener2);
        window.add(bc);
        window.setSize(100, 25);

        BuiSystem.addWindow(window);
        window.center();
    }

    public static void main(String[] args) {
        new ComboBoxTest().start();
    }
}
