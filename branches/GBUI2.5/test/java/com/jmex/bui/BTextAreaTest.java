package com.jmex.bui;

import static org.junit.Assert.*;

import java.awt.Font;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jmex.bui.enumeratedConstants.TextEffect;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.text.AWTTextFactory;
import com.jmex.bui.text.BText;
import com.jmex.bui.text.BTextFactory;
import com.jmex.bui.util.Dimension;
import com.jmex.bui.util.Rectangle;

public class BTextAreaTest {
	private BTextArea textArea;

	@Before
	public void setUp() {
		textArea = new BTextArea();
	}
	
	@Test
	public void test_Get_set_text() throws Exception {
		textArea.setText("hello to you");
		assertEquals("hello to you", textArea.getText());
	}
	
	@Test
	public void test_Get_appended_text() throws Exception {
		textArea.setText("some text here");
		textArea.appendText("some more text to be added here");
		assertEquals("some text here\nsome more text to be added here", textArea.getText());
	}
	
	@Test
	public void test_Add_new_line_to_text() throws Exception {
		final BTextFactory factory = new BTextFactory() {

			@Override
			public BText createText(String text, ColorRGBA color, TextEffect effect, int effectSize, ColorRGBA effectColor, boolean useAdvance) {
				return null;
			}

			@Override
			public int getHeight() {
				return 0;
			}

			@Override
			public BText[] wrapText(final String text, ColorRGBA color, TextEffect effect, int effectSize, ColorRGBA effectColor, int maxWidth) {
				return new BText[] { new BText() {

					@Override
					public int getCursorPos(int index) {
						return 0;
					}

					@Override
					public int getHitPos(int x, int y) {
						return 0;
					}

					@Override
					public int getLength() {
						return text.length();
					}

					@Override
					public Dimension getSize() {
						return new Dimension(10, 10);
					}

					@Override
					public void render(Renderer render, int x, int y, float alpha) {
					}

					@Override
					public void wasAdded() {
					}

					@Override
					public void wasRemoved() {
					}
					
				}};
			}
			
		};
		textArea = new BTextArea() {
			@Override
			public boolean isAdded() {
				return true;
			}
			
			@Override
			public BTextFactory getTextFactory() {
				return factory;
			}
		};
		textArea.setSize(50, 50);
		textArea.appendText("Available commands:\n" + "   items\n");
		textArea.appendText("\nUsage: items <subcommands> <item type>\n" +
			       "  \n" + 
			       "Available subcommands\n" +
			       "  add   Used to add an item to the player backpack\n" +
			       "  \n" +
			       "Available Item types\n" +
			       "  *           A random Item\n" + 
			       "  jewlery_1   An iron ring\n" + 
			       "  \n" + 
			       "Typing 'items ?' will always return this help for the item commands\n\n");
		textArea.validate();
	}
}
