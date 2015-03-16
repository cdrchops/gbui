This is simply my general term for how your GUI with BUI flows.

If you haven't read the [BuiSystem](BuiSystem.md) tutorial, now is a good time to read it.  If you have read it, you may want to review it.

Here's an example flow from a login screen to the main menu.

```
BWindow Login
BButton Login (action login)

BWindow MainMenu
```

Not a whole lot there, but we know that when the user clicks the Login `BButton` they're going to send a login `ActionEvent` which will process and send the user to the `MainMenu BWindow`

Does this sound simple or hard yet?

The answer is fairly simple.

Let's start with our two windows and our test class:

```
package com.jmex.bui.tests;

import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.layout.GroupLayout;

public class TwoWindowTest extends BaseTest2 {
    protected void createWindows() {
        BWindow login = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        BWindow mainMenu = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());
    }

    public static void main(String[] args) {
        new TwoWindowTest().start();
    }
}
```

Ok so not much yet.  The `BuiSystem.getStyle()` retrieves the `BStyleSheet` from our `BuiSystem` class which was set in BaseTest2 when the `SimpleGame` initialized.

On to the rest:
```
package com.jmex.bui.tests;

import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BButton;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.GroupLayout;

public class TwoWindowTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            handleInput(event);
        }
    };

    protected void createWindows() {
        BWindow login = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        BWindow mainMenu = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        BButton loginButton = new BButton("Login", "login");
        loginButton.addListener(listener2);

        login.add(loginButton);
    }

    public static void main(String[] args) {
        new TwoWindowTest().start();
    }

    public void handleInput(ActionEvent event) {

    }
}
```

You'll notice that I added a `BButton` with an "action message" of `login`.  When the `BButton` is clicked it will submit that "action message" to the `ActionListener`, which in this case, goes to `handleInput(ActionEvent)` for us to tell it what to do.

Let's see the miracle of Navigation at work:

This code is quite a bit different from the code just above.

I have taken the `loginWindow` setup and the `mainMenuWindow` setup and moved them to their own methods.  I have added a `BButton` to go back from the `mainMenuWindow` to the `loginWindow`

```
package com.jmex.bui.tests;

import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BButton;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.GroupLayout;

public class TwoWindowTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            handleInput(event);
        }
    };

    private BWindow login;
    private BWindow mainMenu;

    protected void createWindows() {
        setupLoginWindow();
        setupMainMenuWindow();
    }

    public static void main(String[] args) {
        new TwoWindowTest().start();
    }

    public void handleInput(ActionEvent event) {
        String action = event.getAction();
        if (action.equals("login")) {
            BuiSystem.push(login);

            BuiSystem.addWindow(mainMenu);
            mainMenu.center();
            login.dismiss();
        } else if (action.equals("back")) {
            BuiSystem.back(mainMenu);
        }
    }

    public void setupLoginWindow() {
        login = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());
        login.setSize(400,400);

        BButton loginButton = new BButton("Login", "login");
        loginButton.addListener(listener2);

        login.add(loginButton);
        BuiSystem.addWindow(login);
        login.center();
    }

    public void setupMainMenuWindow() {
        mainMenu = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());
        mainMenu.setSize(400, 400);

        BButton backButton = new BButton("Back", "back");
        backButton.addListener(listener2);
        mainMenu.add(backButton);
    }
}
```

Let's look at this code a little more in depth:

Let's start with _[BaseTest2](BaseTest2.md)_ then we'll move on to the rest below.

We're creating our `ActionListener` to handle the `ActionEvents` here.

```
public class TwoWindowTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            handleInput(event);
        }
    };
```

Now we create our variables for our `login` `BWindow` and our `mainMenu` `BWindow`

```
    private BWindow login;
    private BWindow mainMenu;
```

Call the `setupLoginWindow()` and `setupMainMenuWindows()` which will handle all the code for setting the `login` and `mainMenu` `BWindow`s up respectively.

```
    protected void createWindows() {
        setupLoginWindow();
        setupMainMenuWindow();
    }
```

Comments inline with the code

```
    public void setupLoginWindow() {
        //instantiate our login window
        //set our style from our BuiSystem and set our layout to stretch everything vertically
        login = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        //set the size of our login window to 400x400
        login.setSize(400,400);

        //create a new BButton called "loginButton" with the display "Login" and an "actionMessage" of "login"
        BButton loginButton = new BButton("Login", "login");

        //add our listener2 to the loginButton so it knows what to do with the "actionMessage" when the button is clicked
        loginButton.addListener(listener2);

        //add the loginButton to our login window
        login.add(loginButton);

        //add our login window to our BRootNode
        BuiSystem.addWindow(login);
        //center our window -- this could go anywhere in the code I simply place it after my addWindow so I remember that I did it
        login.center();
    }

    //pretty much the same as our login window
    public void setupMainMenuWindow() {
        mainMenu = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());
        mainMenu.setSize(400, 400);

        //create a new BButton that displays "Back" and has an "actionMessage" of "back"
        BButton backButton = new BButton("Back", "back");
        backButton.addListener(listener2);
        mainMenu.add(backButton);
    }
}
```

Just our `main` method.  [JME](http://jmonkeyengine.com/) has many options for starting an application.  I chose this route because I know what the other options are and for these tests you don't need to worry about starting up with dialogs or without, sizes, etc... I probably should, because most people won't have JME running prior to these tutorials, but I have a config file already created so I don't have to worry about it.

If you want to know more about JME startup options check out [JME](http://jmonkeyengine.com).

If you have a problem with the application starting up windowed or whatever... just change your code to [this](JmeStartUpOptions.md)

```
    public static void main(String[] args) {
        new TwoWindowTest().start();
    }
```

Note that we get the `String` action from `event.getAction()`

Then we're going to check it against our logic statements.

```
    public void handleInput(ActionEvent event) {
        String action = event.getAction();
```

If we've clicked the `login` `BButton` then we're going to:
  * push the `login` `BWindow` onto the `Stack<BWindow>` in our [BuiSystem](BuiSystem.md).
  * add our `mainMenu` `BWindow` to our `BRootNode`
  * center our `mainMenu` `BWindow`
  * tell our `login` `BWindow` to go away (`dismiss()`)

This is going to show our `mainMenu` `BWindow` in the center of the screen with a [ginormous](http://dictionary.reference.com/browse/ginormous) `BButton` labeled `back` across the entire thing.

```
        if (action.equals("login")) {
            BuiSystem.push(login);

            BuiSystem.addWindow(mainMenu);
            mainMenu.center();
            login.dismiss();
```

Now we've clicked the `back` `BButton` so we're going to tell our `BuiSystem`:
  * That we want to get our last window off the `Stack<BWindow>`, which just a second ago we pushed it onto the `Stack<BWindow>`... the `login` `BWindow`.
  * That we want the window we've passed in to the `back(BWindow)` method to `dismiss()` just like we did a few lines up.

```
        } else if (action.equals("back")) {
            BuiSystem.back(mainMenu);
        }
    }

```


What does it look like?  I won't dignify that with an answer... However, here are some screenshots.

Login

![http://gbui.googlecode.com/svn/wiki/images/login.png](http://gbui.googlecode.com/svn/wiki/images/login.png)

Back

![http://gbui.googlecode.com/svn/wiki/images/back.png](http://gbui.googlecode.com/svn/wiki/images/back.png)


[TwoWindowsTest.java](Code.md)