package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTest {
	
	private Order test;

	public void setupScenary1() {
		
	}

	@Test
	public void testOrder() {
		
		setupScenary1();
		
		test = new Order("kjasasc", "12/15/2502", "jjascas", "asjas", "ajcsa", 15, "delivered");
		
		assertEquals("kjasasc", test.getCode());
		assertEquals("jjascas", test.getIdCustomer());
		assertEquals("asjas", test.getNitRestaurant());
	}
	
	@Test
	public void testOrder2() {
		
		setupScenary1();
		
		test = new Order("kjasasc", "12/15/2502", "jjascas", "asjas", "ajcsa", 15, "delivered");
		
		test.setCode("545188");
		test.setIdCustomer("496215");
		test.setNitRestaurant("54615251");
		
		assertEquals("545188", test.getCode());
		assertEquals("496215", test.getIdCustomer());
		assertEquals("54615251", test.getNitRestaurant());
	}

}
