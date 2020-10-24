package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

	private Customer test;
	
	public void setupScenary1() {
		
	}
	
	@Test
	public void testCustomer() {
		
		setupScenary1();
		
		test = new Customer("5441", "XISXA", "ASNCISA", "cashbcasuais", (long) 8464665, "incsamias");
		
		assertEquals("5441", test.getIdType());
		assertEquals("XISXA", test.getId());
		assertEquals("ASNCISA", test.getName());
		assertEquals("cashbcasuais", test.getLastName());
		assertEquals((long)8464665, test.getPhone());
		assertEquals("incsamias", test.getAddress());
	}
	
	@Test
	public void testCustomer2() {
		
		setupScenary1();
		
		
		test = new Customer("fafwfas", "ascasc", "asccsa", "scacscs", (long) 846524665, "sacacsa");
		
		test.setIdType("5441");
		test.setId("XISXA");
		test.setName("ASNCISA");
		test.setLastName("cashbcasuais");
		test.setPhone((long)8464665);
		test.setAddress("incsamias");
		
		assertEquals("5441", test.getIdType());
		assertEquals("XISXA", test.getId());
		assertEquals("ASNCISA", test.getName());
		assertEquals("cashbcasuais", test.getLastName());
		assertEquals((long)8464665, test.getPhone());
		assertEquals("incsamias", test.getAddress());
	}

}
