package com.jmex.bui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.Timer;

import com.jme.renderer.ColorRGBA;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BLabel;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BTextField;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.base.BaseTest;
import com.jmex.bui.layout.GroupLayout;
import com.jmex.bui.layout.Justification;

public class ColorTest extends BaseTest {
	@Override
	protected void createWindows() {
		BWindow window = new BWindow(BuiSystem.getStyle(), GroupLayout.makeVert(Justification.TOP));
        window.setSize(settings.getWidth()/2, settings.getHeight()/2);
        window.setLocation(settings.getWidth()/2-window.getWidth()/2, settings.getHeight()/2-window.getHeight()/2);
        BuiSystem.addWindow(window);
        
        final BLabel label = new BLabel("Text");
        window.add(label);
        final BTextField field = new BTextField("TextField");
        field.setPreferredSize(100, 25);
        window.add(field);
        final BTextField field2 = new BTextField("TextField2");
        window.add(field2);
        field2.setEnabled(false);
        final BTextArea area = new BTextArea("Text\non\nseveral\nlines");
        window.add(area);
        final BButton button = new BButton("Button");
        window.add(button);
        
        Timer timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameTaskQueueManager.getManager().update(new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						label.setColor(ColorRGBA.green.clone());
						field.setColor(ColorRGBA.cyan.clone(), BComponent.DEFAULT);
						field2.setColor(ColorRGBA.yellow.clone(), BComponent.DISABLED);
						area.setColor(new ColorRGBA(0.4f, 1f, 0.4f, 0.7f));
						button.setColor(ColorRGBA.orange, BComponent.HOVER);
						button.setColor(ColorRGBA.black, BButton.DOWN);
						return null;
					}
				});
			}
        });
        timer.start();
	}
	
	public static void main(String[] args) {
		ColorTest test = new ColorTest();
		test.start();
	}
}
