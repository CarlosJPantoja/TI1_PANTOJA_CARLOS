package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

	private Product test;
	
	public void setupScenary1() {
		
	}
	
	@Test
	public void testProduct() {
		
		setupScenary1();
		
		test = new Product("5441", "XISXA", "ASNCISA", 15.02, "incsamias");
		
		assertEquals("5441", test.getCode());
		assertEquals("XISXA", test.getName());
		assertEquals("ASNCISA", test.getDescripcion());
		assertEquals(15.02, test.getCost());
		assertEquals("incsamias", test.getNitRestaurant());
	}
	
	@Test
	public void testProduct2() {
		
		setupScenary1();
		
		test = new Product("5441", "XISXA", "ASNCISA", 15.02, "incsamias");
		
		test.setCode("6641");
		test.setName("ascniis");
		test.setDescripcion("Caasa");
		test.setCost(14.02);
		test.setNitRestaurant("52665");
		
		assertEquals("6641", test.getCode());
		assertEquals("ascniis", test.getName());
		assertEquals("Caasa", test.getDescripcion());
		assertEquals(14.02, test.getCost());
		assertEquals("52665", test.getNitRestaurant());
	}

}
