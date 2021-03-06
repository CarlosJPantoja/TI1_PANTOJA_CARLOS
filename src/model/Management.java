package model;

import exceptions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public String addCustomer(String idType, String id, String name, String lastName, Long phone, String address) {
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
	
	public String updateCustomer(String idType, String id, String name, String lastName, Long phone, String address) {
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
		} else if(orderStatus==4) {
			return "\nThe order status is delivered, it cannot be modified";
		} else if(orderStatus==newStatus) {
			return "\nThe chosen status is already assigned to the order";
		} else{
			orders.get(position).setStatus(newStatus);
			return "\nThe order status has been update succesfully";
		}
	}
	
	public String listRestaurants() {
		String msg = "";
		ArrayList<Restaurant> newRestaurants = new ArrayList<Restaurant>();
		newRestaurants.add(restaurants.get(0));
		for(int k=1; k<restaurants.size(); k++) {
			Restaurant test = restaurants.get(k);
			int position = positionToListRestaurant(test, newRestaurants);
			newRestaurants.add(position, test);
		}
		for(int i=0; i<newRestaurants.size(); i++) {
			msg += "\n"+newRestaurants.get(i).getName().trim();
		}
		return msg;
	}
	
	public int positionToListRestaurant(Restaurant test, ArrayList<Restaurant> newRestaurants) {
		int position = 0;
		String complete1 = test.getName().trim();
		for(int i=0; i<newRestaurants.size(); i++) {
			String complete2 = newRestaurants.get(i).getName().trim();
			if(complete1.compareToIgnoreCase(complete2) > 0) {
				position++;
			}
		}
		return position;
	}
	
	public String listCustomers() {
		String msg = "";
		ArrayList<Customer> newCustomers = new ArrayList<Customer>();
		newCustomers.add(customers.get(0));
		for(int k=1; k<customers.size(); k++) {
			Customer test = customers.get(k);
			int position = positionToListCustomer(test, newCustomers);
			newCustomers.add(position, test);
		}
		for(int i=0; i<newCustomers.size(); i++) {
			msg += "\n"+newCustomers.get(i).getAllName();
		}
		return msg;
	}
	
	public int positionToListCustomer(Customer test, ArrayList<Customer> newCustomers) {
		int position = 0;
		Long complete1 = test.getPhone();
		for(int i=0; i<newCustomers.size(); i++) {
			Long complete2 = newCustomers.get(i).getPhone();
			if(complete1.compareTo(complete2) < 0) {
				position++;
			}
		}
		return position;
	}
	
	public String searchCustomer(String name) {
		String msg = "";
		long time = System.currentTimeMillis();
		boolean found = false;
		int mid = 0;
		for(int i=0, j=customers.size()-1; i<=j && !found;){
			mid = (i+j)/2;
			if(customers.get(mid).getAllName().equals(name)){
				found=true;
			}else if(customers.get(mid).getAllName().compareToIgnoreCase(name)<0){
				j=mid-1;
			}else if(customers.get(mid).getAllName().compareToIgnoreCase(name)>0){
				i=mid+1;
			}
		}
		time = System.currentTimeMillis()-time;
		if(found) {
			msg += "\nCustomer "+name+" identified with"+customers.get(mid).getIdType()+" "+customers.get(mid).getId()+" was found in "+time+" milliseconds\n";
		} else {
			msg += "\nCustomer "+name+" was not found, the list was traversed in "+time+" milliseconds\n";
		}
		return msg;
	}
	
	public String importCustomer(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String ln = br.readLine();
		if(ln!=null) {
			String[] line = ln.split(separator);
			while(ln!=null) {
				ln = br.readLine();
				if(ln!=null) {
					line = ln.split(separator);
					String name = line[0];
					String lastName = line[1];
					String idType = line[2];
					String id = line[3];
					Long phone = Long.parseLong(line[4]);
					String address = line[5];
					addCustomer(idType, id, name, lastName, phone, address);
				}
			}
			br.close();
			return "\nData was imported successfully\n";
		} else {
			br.close();
			return "\nFile is empty\n";
		}
	}
	
	public String importRestaurants(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String ln = br.readLine();
		if(ln!=null) {
			String[] line = ln.split(separator);
			while(ln!=null) {
				ln = br.readLine();
				if(ln!=null) {
					line = ln.split(separator);
					String name = line[0];
					String nit = line[1];
					String manager = line[2];
					addRestaurant(name, nit, manager);
				}
			}
			br.close();
			return "\nData was imported successfully\n";
		} else {
			br.close();
			return "\nFile is empty\n";
		}
	}
	
	public String importProducts(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String ln = br.readLine();
		if(ln!=null) {
			String[] line = ln.split(separator);
			while(ln!=null) {
				ln = br.readLine();
				if(ln!=null) {
					line = ln.split(separator);
					String code = line[0];
					String name = line[1];
					String description = line[2];
					double cost = Double.parseDouble(line[3]);
					String nitRestaurant = line[4];
					addProduct(code, name, description, cost, nitRestaurant);
				}
			}
			br.close();
			return "\nData was imported successfully\n";
		} else {
			br.close();
			return "\nFile is empty\n";
		}
	}
	
	public String importOrders(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String codeProduct = "";
		int quantity = 0;
		String ln = br.readLine();
		if(ln!=null) {
			String[] line = ln.split(separator);
			while(ln!=null) {
				ln = br.readLine();
				if(ln!=null) {
					line = ln.split(separator);
					int position = searchObject(line[10], orders);
					if(position==-1) {
						String nit = line[0];
						String id = line[4];
						String date = line[9];
						String code = line[10];
						codeProduct = line[12];
						quantity = Integer.parseInt(line[15]);
						String status = line[16];
						addOrder(nit, id, code, date, codeProduct, quantity, status);
						
					} else {
						codeProduct = line[12];
						quantity = Integer.parseInt(line[15]);
						orders.get(position).setOneProduct(codeProduct);
						orders.get(position).setOneQuantity(quantity);
					}
				}
			}
			br.close();
			return "\nData was imported successfully\n";
		} else {
			br.close();
			return "\nFile is empty\n";
		}
	}
	
	public String exportOrders(String file, String separator) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String msg = "";
		ArrayList<Order> newOrders = new ArrayList<Order>();
		newOrders.add(orders.get(0));
		for(int k=1; k<orders.size(); k++) {
			Order test = orders.get(k);
			int position = positionToExportOrders(test, newOrders);
			newOrders.add(position, test);
		}
		bw.write("nit;name_restaurant;manager_restaurant;type_id_customer;id_customer;first_name;last_name;phone;address;date_order;code_order;name_product;code_product;description;cost;quantity;status".replaceAll(";", separator));
		for(int i=0; i<newOrders.size(); i++) {
			newOrders.get(i).getIdCustomer();
			newOrders.get(i).getNitRestaurant();
			for(int j=0; j<newOrders.get(i).getProducts().size(); j++) {
				
			}
		}
		
		bw.close();
		return msg;
	}
	
	public void addOrder(String nit, String id, String code, String date, String product, int quantity, String status) {
		Order test = new Order(code, date, id, nit, product, quantity, status);
		orders.add(test);
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

	private int positionToExportOrders(Order test, ArrayList<Order> newOrders) {
		int position = 0;
		Long complete1 = Long.parseLong(test.getNitRestaurant());
		for(int i=0; i<newOrders.size(); i++) {
			Long complete2 = Long.parseLong(newOrders.get(i).getNitRestaurant());;
			if(complete1.compareTo(complete2) > 0) {
				position++;
			}
		}
		return position;
	}
}
