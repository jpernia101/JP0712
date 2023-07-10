package com.postools.postools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;

import java.time.LocalDate;

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
	void testIsDateWeekend()  {
		LocalDate isWeekend = LocalDate.of(2023, 7, 9);
		assertTrue(Helpers.isDateWeekend(isWeekend));

		LocalDate isNotWeekend = LocalDate.of(2023, 7, 10);
		assertFalse(Helpers.isDateWeekend(isNotWeekend));
	}

	@Test
	void testIsDateHoliday()  {
		LocalDate laborDay1 = LocalDate.of(2023, 7, 4);
		assertTrue(Helpers.isDateHoliday(laborDay1));

		LocalDate laborDay2 = LocalDate.of(2002, 7, 4);
		assertTrue(Helpers.isDateHoliday(laborDay2));

		LocalDate laborDay3 = LocalDate.of(2015, 7, 11);
		assertFalse(Helpers.isDateHoliday(laborDay3));
	}

	@Test
	void testCheckoutItemCreation() throws InvalidAttributeValueException  {
		CheckoutItem item = new CheckoutItem("CHNS", "07/10/23", 5, 0);
		assertNotNull(item);
	}


	@Test
	void testCheckoutItemCreation_exception_invalidRentalDays() throws InvalidAttributeValueException  {
		assertThrows(InvalidAttributeValueException.class, () -> new CheckoutItem("JAKR", "09/03/15", 0, 50));
	}

	@Test
	void testCheckoutItemCreation_exception_test1() throws InvalidAttributeValueException  {
		assertThrows(InvalidAttributeValueException.class, () -> new CheckoutItem("JAKR", "09/03/15", 5, 101));
	}

	@Test
	void testCheckoutItem_test2() throws InvalidAttributeValueException  {

		CheckoutItem item = new CheckoutItem("LADW", "07/02/20", 3, 10);
		//should only be charged for 1 day , so (1 * 1.99) * 0.90 = 1.79
		assertEquals("1.79", item.getTotal());
	}
}
