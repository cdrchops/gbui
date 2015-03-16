`ActionEvent` is the event fired off by a `BComponent` to the listeners of your `BComponent`

To find out what **action** was fired off you can simply ask the `ActionEvent`

```
String action = event.getAction();
```

From the example above it's clear that `getAction()` returns a `String`.  From there you check the `String` against known actions you want performed.

e.g.
```
if (action.equals("back")) {
    //we're going to go back
}
```

What about filtering by a specific `BComponent` type?  For example, you only want to work with an event that is from a `BButton`.

```
Object source = event.getSource();
if (source instanceof BButton) {
    //now perform an action
}
```

What if you want to process one event from your `BComponent` then pass the rest of the processing on to another listener?

```
event.dispatch(listener); // where listener is a defined listener in your class or another class that has a local instance in this class
```

See also [Navigation](NavigationFlow.md)