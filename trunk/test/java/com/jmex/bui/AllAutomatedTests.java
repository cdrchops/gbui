package com.jmex.bui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses ( {
	BTextFieldTest.class,
	BListTest.class,
	BGroupContainerSingleSelectionTest.class,
	BToggleButtonTest.class
})
@RunWith(Suite.class)
public class AllAutomatedTests {

}
