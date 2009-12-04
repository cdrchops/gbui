package com.jmex.bui;

import static org.junit.Assert.*;

import org.junit.Test;

public class BListTest {
	private static final String ITEM_A = "one";

	@Test
	public void test_Can_select_an_item_in_the_list() throws Exception {
		BList list = new BList();
		list.addValue(ITEM_A);
		list.setSelectedValue(ITEM_A);
		assertEquals(ITEM_A, list.getSelectedValue());
	}
}
