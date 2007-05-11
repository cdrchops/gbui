package com.jmex.bui.tests;

import com.jmex.bui.BDecoratedWindow;
import com.jmex.bui.BuiSystem;

public class DecoratedWindowTest extends BaseTest2 {
    protected void createWindows() {
        BDecoratedWindow wind = new BDecoratedWindow(BuiSystem.getStyle(), "title");
        wind.setSize(400, 400);
        BuiSystem.addWindow(wind);
        wind.center();
    }

    public static void main(String[] args) {
        new DecoratedWindowTest().start();
    }
}
