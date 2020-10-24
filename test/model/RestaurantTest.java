package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	private Restaurant test;
	
	public void setupScenary1() {
		
	}
	
	@Test
	public void testRestaurant() {
		
		setupScenary1();
		
		test = new Restaurant("MC", "152244556", "Juanes");
		
		assertEquals("MC", test.getName());
		assertEquals("152244556", test.getNit());
		assertEquals("Juanes", test.getManager());
	}
	
	@Test
	public void testRestaurant2() {
		
		setupScenary1();
		
		test = new Restaurant("MC", "152244556", "Juanes");
		test.setName("TC");
		test.setManager("Carlos");
		test.setNit("cisuacansc");
		
		assertEquals("TC", test.getName());
		assertEquals("cisuacansc", test.getNit());
		assertEquals("Carlos", test.getManager());
	}
}
