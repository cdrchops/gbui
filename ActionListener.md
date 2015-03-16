To create an ActionListener you can:
  * implement the ActionListener class in a subclass
  * or you can create an Anonymous class

Anonymous class

```
private ActionListener listener = new ActionListener() {
    public void actionPerformed(ActionEvent event) {
        handleInput(event);
    }
};
```

If you create an Anonymous ActionListener like this you'll have to define the `handleInput(ActionEvent)` method in your class.

```
public void handleInput(ActionEvent event) {
    //this is where you'll handle stuff
}
```

So, what can we do with the ActionEvent when it's been fired off by the BComponent

Check out ActionEvent