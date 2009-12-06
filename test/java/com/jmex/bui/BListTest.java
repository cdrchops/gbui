package com.jmex.bui;

import static org.junit.Assert.*;

import org.junit.Test;

public class BListTest {
	private static final String ITEM_A = "one";
	private static final String ITEM_B = "two";

	@Test
	public void test_Can_select_an_item_in_the_list() throws Exception {
		BList list = new BList();
		list.addValue(ITEM_A);
		list.setSelectedValue(ITEM_A);
		assertEquals(ITEM_A, list.getSelectedValue());
	}
	
	@Test
	public void test_Remove_all_removes_all_values() throws Exception {
		BList list = new BList();
		list.addValue(ITEM_A);
		list.addValue(ITEM_B);
		list.setSelectedValue(ITEM_B);
		list.removeAll();
		assertEquals(null, list.getSelectedValue());
	}
	
	@Test
	public void test_Remove_index_removes_the_value() throws Exception {
		BList list = new BList();
		list.addValue(ITEM_A);
		list.addValue(ITEM_B);
		list.setSelectedValue(ITEM_B);
		list.remove(1);
		assertEquals(null, list.getSelectedValue());
	}
}
