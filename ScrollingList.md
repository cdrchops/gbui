# Scrolling List #

The example that comes in BUI for the BScrollingList is very generic.  Newbies might have to work with this for hours before coming up with a solution to even part of their problem.

Here's the code for ScrollingListTest

```
BWindow window = new BDecoratedWindow(style, null);
window.setLayoutManager(GroupLayout.makeVStretch());

BScrollingList<String, BButton> list =
    new BScrollingList<String, BButton>() {
        public BButton createComponent(String str) {
            return new BButton(str);
        }
    };

window.add(list);

root.addWindow(window);
window.setSize(400, 400);
window.setLocation(25, 25);

for (int i = 0; i < 100; i++) {
    list.addValue("Item #" + i, true);
}
```

Basically this code takes a  ` String `  and makes a  ` BButton `  that is added to the  ` BScrollingList `  (list) then that is added to the window.

So what if you want to add something different to your  ` BScrollingList `  like a  ` BLabel `  or even a  ` BButton `  that has a title, an action and a listener?

With this code, it's not possible... written in stone are the carvings that this must be a  ` String `  which a  ` BButton `  will display to the user.

Admittedly, it's a simple test, definitely not something that looks to be intended for anyone elses example, but an internal example... a test... hence it's in the test package.

So, let's modify the  ` ScrollingListTest `  above... we'll start with a new class --  ` ExtendedScrollingListTest `

ExtendedScrollingListTest is the same as the ScrollingListTest except that the code below goes in the createWindows method and it extends [[bui:tutorials:basetest2|BaseTest2]] instead of BaseTest.

You'll have to use the jme-bui.jar from my downloads page or the BuiSystem class won't be there.

```
//create our new window
BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

//create a scrolling list
BScrollingList<BScrollMessage, BButton> list = new BScrollingList<BScrollMessage, BButton>() {
    public BButton createComponent(BScrollMessage str) {
        BButton b = new BButton(str.getDisplayName(), str.getActionEventName());
        b.addListener(str.getListener());
        return b;
    }
};

//add that list to our window
window.add(list);

//iterate through 40 times and add the same button to our list
for (int i = 0; i < 40; i++) {
    list.addValue(new BScrollMessage("Message", "messageAction", listener), true);
}

//make a new button
BButton button = new BButton("Back", "back");
button.addListener(listener);
window.add(button);

//add our window to our root node
BuiSystem.addWindow(window);

//set our size
window.setSize(400, 400);

 //center the window
window.center();
```

Doesn't look too bad does it... So let's look at what I did a little differently.

This code is a local class created simply for handling our details.  BScrollingList<V, C extends BComponent> that means that it takes any class for the first part and the second part must be an extension of BComponent (BLabel, BButton, etc).

```
BScrollingList<BScrollMessage, BButton>
```

As you can see, the class I created for the first part of this Generic "argument" is called BScrollMessage.

What I wanted BScrollMessage to do was:
  * contain a message to display
  * contain an actionMessage to be performed when the BComponent was clicked on
  * contain an ActionListener that would handle the event when the BComponent was clicked on

```
import com.jmex.bui.event.ActionListener;

public class BScrollMessage {
    String displayName;
    String actionEventName;
    ActionListener listener;

    public BScrollMessage(final String {{{displayName,
                         final String {{{actionEventName,
                         final ActionListener {{{listener) {
        displayName = {{{displayName;
        actionEventName = {{{actionEventName;
        listener = {{{listener;
    }

    public String getActionEventName() {
        return actionEventName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ActionListener getListener() {
        return listener;
    }
}
```

You can see by our implementation when we add the value that I take care of the constructor then.  You don't have to, I did for this example.

`list.addValue(new BScrollMessage("Message", "messageAction", listener), true);`

Here's what it looks like:

http://gerbildrop.com/downloads/extscrolllist.png?200

http://gerbildrop.com/downloads/extscrolllist2.png?200

Where's the listener?  The listener is part of [BaseTest2](BaseTest2.md).  You can create your own ActionListener or pass one in to your class... whatever you want.  For more about [ActionListener](ActionListener.md)s check this page out [ActionListener](ActionListener.md)