/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui;

import com.jmex.bui.base.BaseTest2;
import com.jmex.bui.layout.BorderLayout;

/**
 * @author torr
 * @since Apr 10, 2009 - 2:44:23 PM
 */
public class ChatTest extends BaseTest2 {
    @Override
    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), new BorderLayout(1, 2));
        window.add(new BChatComponent(), BorderLayout.WEST);
        window.add(new BChatComponent(), BorderLayout.EAST);

        window.setBounds(20, 140, 400, 250);
        BuiSystem.addWindow(window);
    }

    public static void main(String[] args) {
        new ChatTest().start();
    }
}
