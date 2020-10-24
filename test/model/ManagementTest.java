package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ManagementTest {
	
	private Management manage;
	
	public void setupScenary1() {
		manage = new Management();
	}
	
	@Test
	public void testAddCustomer() {
		
		setupScenary1();
	
		manage.addCustomer("5441", "XISXA", "ASNCISA", "cashbcasuais", (long) 8464665, "incsamias");
		
		assertEquals(1, manage.getCustomers().size());
	}
	
	@Test
	public void testAddProduct() {
		
		setupScenary1();
	
		manage.addProduct("5441", "XISXA", "ASNCISA", 15.02, "incsamias");
		
		assertEquals(1, manage.getProducts().size());
	}

}
