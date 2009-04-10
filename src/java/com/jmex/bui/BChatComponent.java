/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui;

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.enumeratedConstants.Orientation;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.BorderLayout;

/**
 * @author torr
 * @since Apr 10, 2009 - 2:58:38 PM
 */
public class BChatComponent extends BContainer {
    private BTextArea _text;
    private BTextField _input;

    public BChatComponent() {
        setLayoutManager(new BorderLayout(1, 2));
        add(_text = new BTextArea(), BorderLayout.CENTER);
        add(_input = new BTextField(), BorderLayout.SOUTH);
        add(new BScrollBar(Orientation.VERTICAL, _text.getScrollModel()),
            BorderLayout.EAST);
        add(new BScrollBar(Orientation.HORIZONTAL, 0, 25, 50, 100),
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
    }
}
