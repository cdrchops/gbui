/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui.tests;

import com.jmex.bui.BRootNode;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BComboBox;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.layout.GroupLayout;

/**
 * @author torr
 * @since Mar 18, 2008 - 1:08:33 PM
 */
public class BorderlessWindowTest extends BaseTest2 {
    public static void main(String[] args) {
        new BorderlessWindowTest().start();
    }
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.out.println(bc.getSelectedItem());
            System.out.println(bc.getSelectedValue());
        }
    };

    private BComboBox bc;

    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());
        window.setStyleClass("champion");

        bc = new BComboBox();

        BComboBox.Item item = new BComboBox.Item("this", "this2");
        bc.addItem(item);

        BComboBox.Item item2 = new BComboBox.Item("this3", "this4");
        bc.addItem(item2);

        bc.addListener(listener2);
        window.add(bc);
        window.setSize(100, 25);

        BuiSystem.addWindow(window);
        window.center();

    }
}
