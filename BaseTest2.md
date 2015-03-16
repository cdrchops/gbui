
```
package com.jmex.bui.tests;

import com.jme.app.SimpleGame;
import com.jme.input.KeyBindingManager;
import com.jme.input.MouseInput;
import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BButton;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.listener.ListenerUtil;

/** A base class for our various visual tests. */
public abstract class BaseTest2 extends SimpleGame {
    // our basic ActionListener
    protected ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() instanceof BButton) {
                // In ListenerUtil our getActionName looks for an indexOf... so I wouldn't name a component minimizeclose or you'll have problems
                String action = ListenerUtil.getActionName(event.getAction());

                // takes the action and retrieves the name of the BComponent that we're using to call with so we can use the BComponent by name if need be
                String componentName = ListenerUtil.getComponentName(event.getAction(), action);

                //obvious, but if the action is close, then get the componentName and dismiss() it.
                if (action.equals("close")) {
                    BuiSystem.getWindow(componentName).dismiss();
                } else if (action.equals("ok")) {
                    //obvious, but if the action is ok, then get the componentName and dismiss() it.
                    BuiSystem.getWindow(componentName).dismiss();
                }
            }
        }
    };

    //abstract implemenation required by SimpleGame
    protected void simpleInitGame() {
        // we don't hide the cursor
        MouseInput.get().setCursorVisible(true);

        //init our BuiSystem.  We want our input to come from the InputHandler defined in various superclasses of SimpleGame
        // our timer is going to be the Timer
        // we've defined a stylesheet here, however, by default BuiSystem.init() will pull this stylesheet out of the jme-bui.jar
        BuiSystem.init(new PolledRootNode(timer, input), "/rsrc/style.bss");

        //we attach our BRootNode to the Game rootNode so we can actually use it
        //BRootNode extends Geometry so we're using OpenGL (in the form of LWJGL) for a lot of displays
        rootNode.attachChild(BuiSystem.getRootNode());

        //call the abstract createWindows() method
        createWindows();

        // these just get in the way -- remove these keys
        KeyBindingManager.getKeyBindingManager().remove("toggle_pause");
        KeyBindingManager.getKeyBindingManager().remove("toggle_wire");
        KeyBindingManager.getKeyBindingManager().remove("toggle_lights");
        KeyBindingManager.getKeyBindingManager().remove("toggle_bounds");
        KeyBindingManager.getKeyBindingManager().remove("camera_out");

        lightState.setEnabled(false);

        //simple enough... set the background color to gray
        display.getRenderer().setBackgroundColor(ColorRGBA.gray);
    }

    //all of our subclasses for tests will implement this method
    protected abstract void createWindows();
}
```