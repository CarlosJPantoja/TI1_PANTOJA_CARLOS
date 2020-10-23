package model;
 
import exceptions.*;
import java.util.ArrayList;

public class Management {
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Product> products;
	private ArrayList<Customer> customers;
	private ArrayList<Order> orders;
	
	public Management() {
		restaurants = new ArrayList<Restaurant>();
		products = new ArrayList<Product>();
		customers = new ArrayList<Customer>();
		orders = new ArrayList<Order>();
	}
	
	public int searchObject(String id, ArrayList<?> list) {
		int position = -1;
		for(int i=0; i<list.size() && position==-1; i++) {
			if((list.get(i)).toString().equals(id)) {
				position=i;
			}
		}
		return position;
	}
	
	public String addRestaurant(String name, String nit, String manager) {
		String msg = "";
		int position = searchObject(nit, restaurants);
		if(position==-1) {
			Restaurant test = new Restaurant(name, nit, manager);
			restaurants.add(test);
			msg = "\nThe restaurant was successfully added\n";
		}else {
			msg = "\nThe restaurant was not added, the entered nit already exists\n";
		}
		return msg;
	}
	
	public String updateRestaurant(String name, String nit, String manager) {
		String msg = "\nThe restaurant was successfully update\n";
		int position = searchObject(nit, restaurants);
		Restaurant test = restaurants.get(position);
		test.setName(name);
		test.setManager(manager);
		return msg;
	}
	
	public String addProduct(String code, String name, String descripcion, double cost, String nitRestaurant) {
		String msg = "";
		int position = searchObject(code, products);
		if(position==-1) {
			Product test = new Product(code, name, descripcion, cost, nitRestaurant);
			products.add(test);
			msg = "\nThe product was successfully added\n";
		}else {
			msg = "\nThe product was not added, the entered code already exists\n";
		}
		return msg;
	}
	
	public String updateProduct(String code, String name, String descripcion, double cost) {
		String msg = "\nThe product was successfully update\n";
		int position = searchObject(code, products);
		Product test = products.get(position);
		test.setName(name);
		test.setDescripcion(descripcion);
		test.setCost(cost);
		return msg;
	}
	
	public String addCustomer(String idType, String id, String name, String lastName, String phone, String address) {
		String msg = "";
		int position = searchObject(id, customers);
		if(position==-1) {
			Customer test = new Customer(idType, id, name, lastName, phone, address);
			position = positionToAddCustomer(test);
			customers.add(position, test);
			msg = "\nThe customer was successfully added\n";
		}else {
			msg = "\nThe customer was not added, the entered id already exists\n";
		}
		return msg;
	}
	
	public String updateCustomer(String idType, String id, String name, String lastName, String phone, String address) {
		String msg = "\nThe customer was successfully update\n";
		int position = searchObject(id, customers);
		customers.remove(position);
		Customer test = new Customer(idType, id, name, lastName, phone, address);
		position = positionToAddCustomer(test);
		customers.add(position, test);
		return msg;
	}
	
	public String addOrder(String idCustomer, String nitRestaurant, ArrayList<String> products, ArrayList<Integer> quantitys) {
		String msg = "";
		Order test = new Order(orders.get(orders.size()-1).getCode(), idCustomer, nitRestaurant, products, quantitys);
		orders.add(test);
		msg = "\nOrder "+test.getCode()+" was created successfully at "+test.getDate()+"\n";
		return msg;
	}
	
	public String updateOrder(String code, String idCustomer, String nitRestaurant, ArrayList<String> products, ArrayList<Integer> quantitys) {
		String msg = "\nThe order was successfully update\n";
		int position = searchObject(code, orders);
		Order test = orders.get(position);
		test.setIdCustomer(idCustomer);
		test.setNitRestaurant(nitRestaurant);
		test.setProducts(products);
		test.setQuantitys(quantitys);
		return msg;
	}
	
	public int positionToAddCustomer(Customer test) {
		int position = 0;
		String complete1 = test.getAllName();
		for(int i=0; i<customers.size(); i++) {
			String complete2 = customers.get(i).getAllName();
			if(complete1.compareToIgnoreCase(complete2) < 0) {
				position++;
			}
		}
		return position;
	}
	
	public String updateStatus(String code, int newStatus) throws InvalidOption {
		int position = searchObject(code, orders);
		int orderStatus = orders.get(position).getStatus();
		if(newStatus<orderStatus) {
			throw new InvalidOption();
		} else{
			orders.get(position).setStatus(newStatus);
			return "\nThe order status has been update succesfully";
		}
	}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
}
