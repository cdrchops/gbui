_BuiSystem ` is a concept I came up with for use with BUI.  I found that in [http://jmonkeyengine.com JME] there's only one {{{DisplaySystem` that is called by `DisplaySystem.getDisplaySystem()`.  You don't have to pass values around all over the place, just access it.  Why not do that for BUI?  So I did._

Let's cover some of the internals of the `BuiSystem`

```
private static BRootNode rootNode;
private static BStyleSheet style;
private static Stack<BWindow> history;
```

The `BRootNode` is the root node of the entire BUI application you're running... it's like the parent of all that is BUI.

The `BStyleSheet` is the `*.bss` file that tells BUI how to layout your styles... whether your `BButtons` are images or text or if your `BWindow` has a line around it and what color is that line.  `BStyleSheet` and `*.bss` are like `css` `Cascading Style Sheets` for `HTML_.  You can name the .bss file any extension you want as long as the format of the .bss is consistent with what the {{{BStyleSheet` processor is expecting.

The `Stack<BWindow>` is our "navigation manager" of sorts.  We `push` a `BWindow` onto the `Stack<BWindow>` when we have a chance that we'll want to go back to that `BWindow_.  We {{{pop` a `BWindow` off the `Stack<BWindow>` when we're ready to go back to that {{{BWindow_._

You can initialize the `BuiSystem`{ by passing a `BRootNode` or child thereof, a path to your `BStylesheet` and a `Stack<BWindow>` for the history.

```
BuiSystem.init(_root, "/rsrc/styles.bss", new Stack<BWindow>());
```

You don't need to do ANY of these.  If you don't, then it will create a BPolledRootNode with the Timer.getTimer() and a new InputHandler()
the stylesheet will come from the bui jar and the Stack for history will just be instantiated.

in your code you can now just access the BStyleSheet, History and BRootNode:

```
BuiSystem.getStyle();
```

```
BuiSystem.getRootNode();
```

With the history, it's a little different.  Either you're going to `push` or `pop` a `BWindow`.

```
BuiSystem.push(WINDOW); // where WINDOW is your instantiation of a window
```

```
BuiSystem.back(); // where back pops the last window off of the stack and returns it to the display for the user to see
```

You can also get a specific window by name:

```
BuiSystem.getWindow("myWindowName");//where myWindowName is the name you gave to your window
```

The `BuiSystem` `BRootNode` is not a replacement for components added to a specific `BWindow`.  You won't want to get a `rootNode` from the `BuiSystem` to reference in your subcomponent if your intent is to modify the node structure of the windows root node.  If you want to access the `BuiSystem` `BRootNode` to do a function (eg. go back in your display to a previous display) then you would be using `BuiSystem.back()` correctly.