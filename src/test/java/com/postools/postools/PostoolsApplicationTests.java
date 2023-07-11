package com.postools.postools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.directory.InvalidAttributeValueException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.postools.postools.ToolsFactory.Tool;
import com.postools.postools.ToolsFactory.ToolFactory;

@SpringBootTest
class PostoolsApplicationTests {

	// CheckoutItem checkoutItem;
	@Test
	void testCreateJackhammer() {
		Tool jackHammer = ToolFactory.createTool("JAKR");
		assertEquals("JACKHAMMER", jackHammer.getToolType());
		assertEquals("RIGID", jackHammer.getToolBrand());
	}

	@Test
	void testCreateJackhammer2() {
		Tool jackHammer = ToolFactory.createTool("JAKD");
		assertEquals("JACKHAMMER", jackHammer.getToolType());
		assertEquals("DEWALT", jackHammer.getToolBrand());
	}

	@Test
	void testCreateLadder() {
		Tool ladder = ToolFactory.createTool("LADW");
		assertEquals("LADDER", ladder.getToolType());
	}

	@Test
	void testCreateChainsaw() {
		Tool ladder = ToolFactory.createTool("CHNS");
		assertEquals("CHAINSAW", ladder.getToolType());
	}

	@Test
	void testCreateTool_exception() {
		assertThrows(IllegalArgumentException.class, () -> ToolFactory.createTool("ERR"));
	}

	@Test
	void testIsDateWeekend() {
		LocalDate isWeekend = LocalDate.of(2023, 7, 9);
		assertTrue(Helpers.isDateWeekend(isWeekend));

		LocalDate isNotWeekend = LocalDate.of(2023, 7, 10);
		assertFalse(Helpers.isDateWeekend(isNotWeekend));
	}

	@Test
	void getObservedDatesOfHoliday() {
		ArrayList<LocalDate> observedDates = new ArrayList<>();
		//[ "2020/07/03", "2020/09/07" ]
		observedDates.add(LocalDate.of(2020, 7, 3));
		observedDates.add(LocalDate.of(2020, 9, 7));
		assertEquals(observedDates,Helpers.getObservedDatesOfHoliday(2020));
	}

	@Test
	void testCheckoutItemCreation() throws InvalidAttributeValueException {
		CheckoutItem item = new CheckoutItem("CHNS", "07/10/23", 5, 0);
		assertNotNull(item);
	}

	@Test
	void testCheckoutItemCreation_exception_invalidRentalDays() throws InvalidAttributeValueException {
		assertThrows(InvalidAttributeValueException.class, () -> new CheckoutItem("JAKR", "09/03/15", 0, 50));
	}

	@Test
	void testCheckoutItemCreation_exception_test1() throws InvalidAttributeValueException {
		assertThrows(InvalidAttributeValueException.class, () -> new CheckoutItem("JAKR", "09/03/15", 5, 101));
	}

	@Test
	void testCheckoutItem_test2() throws InvalidAttributeValueException {

		CheckoutItem item = new CheckoutItem("LADW", "07/02/20", 3, 10);
		// should only be charged for 2 days (saturday & sunday) , so (2 * 1.99) * 0.90
		// = 3.58
		assertEquals("3.58", item.getTotal());
	}

	@Test
	void testCheckoutItem_test3() throws InvalidAttributeValueException {

		CheckoutItem item = new CheckoutItem("CHNS", "07/02/15", 5, 25);
		// should only be charged for 3 days (Friday, Monday & Tuesday) , so (3 * 1.49) * 0.75
		// = 3.35
		assertEquals("3.35", item.getTotal());
	}

	@Test
	void testCheckoutItem_test4() throws InvalidAttributeValueException {

		CheckoutItem item = new CheckoutItem("JAKD", "09/03/15", 6, 0);
		// should only be charged for 3  days ( Fri, Tues , weds) , so (3 * 2.99) 
		// = 8.97
		//(labor day and sat sunday exempt)
		assertEquals("8.97", item.getTotal());
	}

	@Test
	void testCheckoutItem_test5() throws InvalidAttributeValueException {
		// should only be charged for 5  days  , so (5 * 2.99) 
		// = 14.95
		CheckoutItem item = new CheckoutItem("JAKR", "07/02/15", 9, 0);
		
		assertEquals("14.95", item.getTotal());
	}

	@Test
	void testCheckoutItem_test6() throws InvalidAttributeValueException {

		CheckoutItem item = new CheckoutItem("JAKR", "07/02/20", 4, 50);
		// should only be charged for 1 day (Monday ) , so (1 * 2.99) * 0.50
		// = 1.79
		assertEquals("1.50", item.getTotal());
	}
}
