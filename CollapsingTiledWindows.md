Collapsing and Tiled Windows WIP -- not fully done yet

There are times when you want multiple windows to display.  They can, but don't have to, collapse.

Using the `BTiledWindowController` and the `BTiledWindowContainer` and our `ActionListener` implementation - `CollapsingWindowListener` - we're going to go through the `CollapsingTiledMessageWindowTest` one piece at a time.

First, we need to know what we're going to do:
  * Tile all windows that display on our screen
  * Handle their actions appropriately (the buttons on the window)
  * Handle moving the window to the front when it's clicked on

We start with `BTiledWindowController`.  This controller is designed to control our window operations in terms of moving the windows to the front or back (the Z index and layering) as well as dispatching events from the buttons.

The `BTiledWindowController` is a Controller and a Container.
It has properties of a Controller in that it can:
  * dispatch our processes and events
  * handle layers for us (z index)
  * handle other layout features (tiling)

It also has properties of a Container:
  * stores multiple "components"

The `BTiledWindowController` contains an internal listener.  We want our Controller to call this internal listener first to process our layering by retrieving the window and pushing it to the front of our display... Then we want it to dispatch to an external listener that handles the events of our components (buttons) inside the included windows.

We're going to take the `CollapsingWindowListener` and extend it to handle our button events.

You can view these changes by looking at the `CollapsingTiledMessageWindowTest` and the `CollapsingWindowListenerImpl`