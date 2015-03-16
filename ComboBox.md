A `ComboBox` is a box that you see with no initial value.  When you click on it, it drops down and shows you all of the values that you could select.  In HTML this would be called a `Select` tag with nested `Option` tags.

Let's get started:

Building a `ComboBox` is just as easy as any of the other `BComponents`.

Again, we'll be working with [BaseTest2](BaseTest2.md) as our base class

```
package com.jmex.bui.tests;

import com.jmex.bui.BComboBox;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.GroupLayout;

public class ComboBoxTest extends BaseTest2 {
    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        BComboBox bc = new BComboBox();

        window.add(bc);
        window.setSize(100, 25);

        BuiSystem.addWindow(window);
        window.center();
    }

    public static void main(String[] args) {
        new ComboBoxTest().start();
    }
}
```

That's it to create a `BComboBox`...

![http://gbui.googlecode.com/svn/wiki/images/combobox1.png](http://gbui.googlecode.com/svn/wiki/images/combobox1.png) ![http://gbui.googlecode.com/svn/wiki/images/combobox2.png](http://gbui.googlecode.com/svn/wiki/images/combobox2.png)

Oh, you want to see items displayed?  Let's move on to adding `Item`s

We can add a `String` or any other `Object`, but remember `BComboBox` is an extension of `BLabel`.  If you view the source code, you'll see that `BComboBox` has an inner class called `Item`.  We're going to use both.

To add a simple `String` to display we simply:

```
    bc.addItem("this");
```

Not really that impressive, but it does display...

{{bui:tutorials:combobox:combobox3.png}}

However, it doesn't do anything.

If we add an `ActionListener` as we've done in the past, then we can work with our information.  Let's do it.

```
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
        }
    };
```

Add that to our `BComboBox`:

```
bc.addListener(listener2);
```

Great!  Now we have a `BComboBox` that displays, has an item, and a listener... but it still doesn't do anything.  Our code should look something like this:

```
package com.jmex.bui.tests;

import com.jmex.bui.BComboBox;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.GroupLayout;

public class ComboBoxTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
        }
    };

    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        BComboBox bc = new BComboBox();

        bc.addItem("this");

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
```

So what's wrong with it?  How are we going to get any kind of value out of the `BComboBox`?

For this example, we're going to make the `BComboBox` a private member of our `ComboBoxTest` class.

```
package com.jmex.bui.tests;

import com.jmex.bui.BComboBox;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.GroupLayout;

public class ComboBoxTest extends BaseTest2 {
    private ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
        }
    };

    private BComboBox bc;
    protected void createWindows() {
        BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVStretch());

        bc = new BComboBox();

        bc.addItem("this");

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
```

Still does nothing, but we're a little closer to our goal.  Let's add some information to our `ActionListener` and see if we can get something to print to our output (`System.out.println()`)

Add this line just below the `actionPerformed(ActionEvent)` line.

```
Object o = bc.getSelectedItem();
```

Now we have an `Object` to work with.  Remember `BComboBox` items are an `Object` e.g. `String` or `Item` we've told it we are giving it a `String` but what if we had given it an `Item`?  Then we want to ensure that we're getting back an `Object` that we'll check and see what type it is.

```
if (o instanceof String) {
    System.out.println(o);
}
```

Alright... now when we click on the only label that drops down we will see "this" display in our output on the console.  A little closer, but not quite right.  We're still not doing any good with our `BComboBox`.  What if you want to display one thing and have the "actionMessage" be another?

That's where we use `Item` which is the correct usage.  Anytime you use a `BComboBox` you should use `Item`.

`Item` takes two parameters both `String`s.  The first one is the "actionMessage" or "value" the second is the "displayMessage" or "label" (not to be confused with a `BLabel`.

So, let's get rid of our line:

```
bc.addItem("this");
```

We're going to replace it with five new lines:

```
Item item = new Item("this", "this2");
Item item2 = new Item("this3", "this4");

bc.addItem(item);
bc.addItem(item2);
```

Now, we have two `Item`s that will display the first one with the label "this2" the second one with the label "this4".  If we click on them, what do you think is going to come back?  Well we haven't covered that, but what will return is "this2" and "this4" respectively.

That's not really what we want though... we could have done that with just the `String`.  What we really want is "this" and "this3" respectively.  These are the values we're going to act upon and decide what to do.

Let's modify our code just a little bit.

In the `actionPerformed(ActionEvent)` method remove all of the code and replace it with:

```
    System.out.println(bc.getSelectedValue());
```

Now, you send the value to the console.   If you wanted to act upon that... like if value is bob then go to another window... you could.

 A few notes about methods in `BComboBox` 

There are two methods that you will more than likely use most often.

`getSelectedItem()` and `getSelectedValue()`

`getSelectedItem()` returns the label of the Item.

`getSelectedValue()` returns the Object of the Item (that first parameter we passed in), in our case the "actionMessage"

These next methods are ones that you might want to know about, but probably won't use as often.

`clearItems()` If you need to remove all items from your `BComboBox` so you can replace them (a dynamic `BComboBox`)

`addItem(int, Object)` if you need to add an `Item` to a specific location in your `BComboBox`

`getItem(int)` if you need to get a specific `Item` from the `BComboBox`

`getItemCount()` if you need to get the total number of `Item`s in the `BComboBox`

`selectItem(int)` if you need to set a default selection or based on another choice you can select this item.

`selectItem(Object)` same as above, but with the object rather than the specific item by number in the `BComboBox`

`selectValue(Object)` same as above, but by value

`setItems(Collection)` set all of your `Item`s at one time from a `Collection` rather than adding them one by one... say for a pre-made selection list of items

`setItems(Object[])` same as above but with an `Object[]` rather than a `Collection`

As you can see, the `BComboBox` is quite flexible and one of the easiest components to use.