/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui;

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.base.BaseTest2;
import com.jmex.bui.enumeratedConstants.Orientation;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.BorderLayout;

/**
 * @author torr
 * @since Apr 10, 2009 - 2:44:23 PM
 */
public class ChatTest extends BaseTest2 {
    protected BTextArea _text;
    protected BTextField _input;

    @Override
    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), new BorderLayout(1, 2));
        window.add(_text = new BTextArea(), BorderLayout.CENTER);
        window.add(_input = new BTextField(), BorderLayout.SOUTH);
        window.add(new BScrollBar(Orientation.VERTICAL, _text.getScrollModel()),
                   BorderLayout.EAST);
        window.add(new BScrollBar(Orientation.HORIZONTAL, 0, 25, 50, 100),
                   BorderLayout.NORTH);
        _input.addListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String inputText = _input.getText();
                if (inputText != null && !inputText.equals("")) {
                    _text.appendText("You said: ", ColorRGBA.red);
                    _text.appendText(_input.getText() + "\n");
                    _input.setText("");
                }
            }
        });
        window.setBounds(20, 140, 400, 250);
        BuiSystem.addWindow(window);
    }

    public static void main(String[] args) {
        new ChatTest().start();
    }
}
