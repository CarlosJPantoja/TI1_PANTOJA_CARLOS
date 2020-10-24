package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import exceptions.*;
import model.Management;

public class Menu {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private Management management;

	public Menu(){
		management = new Management();
	}

	public void showMenu() throws IOException{
		int option = -1;
		option = selectOption(textManagement(), 0, 6); 
		do {
			if(option==-1) {
				option = selectOption("\n"+textManagement(), 0, 6); 
			}
			switch(option) {
				case 1:
					option = addInformation();
				break;
				
				case 2:
					option = updateInformation();
				break;
				
				case 3:
					option = updateStatus();
				break;
				
				case 4:
					option = listOnScreen();
				break;
				
				case 5:
					option = searchCustomer();
				break;
				
				case 6:
					option = importData();
				break;
			}
		} while(option!=0);
	}
	
	public String textManagement(){
		String msg = "Management\n1. Add information\n2. Update information\n3. Update order status\n4. List on screen\n5. Search customer\n6. Import data (from .csv)\n0. End the program";
		return msg;
	}
	
	public int importData() throws IOException {
		int option = -1;
		do {
			option = selectOption(textImportData(), 0, 4);
			String msg = "";
			String separator = "";
			if(option!=0) {
				msg = typeString("\nType the file location to import");
				separator = typeString("\nType the separator of the file");
			}
			switch(option) {
				case 1:
					msg = management.importRestaurants(msg, separator);
					System.out.println(msg);
				break;

				case 2:
					msg = management.importProducts(msg, separator);
					System.out.println(msg);
				break;
				
				case 3:
					msg = management.importCustomer(msg, separator);
					System.out.println(msg);
				break;
				
				case 4:
					msg = management.importOrders(msg, separator);
					System.out.println(msg);
				break;
			}
		} while(option!=0);
		return -1;
	}
	
	public String textImportData(){
		String msg = "Select an option\n1. Import restaurants\n2. Import producs\n3. Import customers\n4. Import orders\n0. Back to menu";
		return msg;
	}
	
	public int addInformation() throws IOException {
		int option = -1;
		do {
			option = selectOption(textAddInformation(), 0, 4);
			switch(option) {
				case 1:
					addRestaurant();
				break;

				case 2:
					if(management.getRestaurants().size()>0) {
						addProduct();
					} else {
						System.out.print("\nNo restaurants has been added, without one, you cannot add products\n");
					}
				break;
				
				case 3:
					addCustomer();
				break;
				
				case 4:
					if(!(management.getRestaurants().size()>0)) {
						System.out.print("\nNo restaurants has been added, without one, you cannot add products\n");
					} else if(!(management.getCustomers().size()>0)){
						System.out.print("\nNo customers has been added, without one, you cannot add products\n");
					} else {
						addOrder();
					}
				break;
			}
		} while(option!=0);
		return -1;
	}
	
	public String textAddInformation() {
		String msg = "\nSelect an option\n1. Add restaurant\n2. Add product\n3. Add customer\n4. Add order\n0. Back to menu";
		return msg;
	}
	
	public int updateInformation() throws IOException {
		int option = -1;
		do {
			option = selectOption(textUpdateInformation(), 0, 4);
			switch(option) {
				case 1:
					if(management.getRestaurants().size()>0) {
						updateRestaurant();
					} else {
						System.out.print("\nNo restaurants has been added, without one, you cannot update information\n");
					}
				break;

				case 2:
					if(management.getProducts().size()>0) {
						updateProduct();
					} else {
						System.out.print("\nNo products has been added, without one, you cannot update information\n");
					}
				break;
				
				case 3:
					if(management.getCustomers().size()>0) {
						updateCustomer();
					} else {
						System.out.print("\nNo customers has been added, without one, you cannot update information\n");
					}
				break;
				
				case 4:
					if(management.getOrders().size()>0) {
						updateOrder();
					} else {
						System.out.print("\nNo orders has been added, without one, you cannot update information\n");
					}
				break;
			}
		} while(option!=0);
		return -1;
	}
	
	public String textUpdateInformation() {
		String msg = "\nSelect an option\n1. Update restaurant\n2. Update product\n3. Update customer\n4. Update order\n0. Back to menu";
		return msg;
	}
	
	public int listOnScreen() throws IOException {
		int option = -1;
		do {
			option = selectOption(textListOnScreen(), 0, 2);
			switch(option) {
				case 1:
					if(management.getRestaurants().size()>0) {
						String msg = management.listRestaurants();
						System.out.print(msg+"\n");
					} else {
						System.out.print("\nNo restaurants has been added, without one, you cannot list information\n");
					}
				break;

				case 2:
					if(management.getCustomers().size()>0) {
						String msg = management.listCustomers();
						System.out.print(msg+"\n");
					} else {
						System.out.print("\nNo customers has been added, without one, you cannot list information\n");
					}
				break;
			}
		} while(option!=0);
		return -1;
	}
	
	public String textListOnScreen() {
		String msg = "\nSelect an option\n1. List restaurants in ascending alphabetical order\n2. List customers in order of their descending phone number\n0. Back to menu";
		return msg;
	}
	
	public int updateStatus() throws IOException {
		String orderCode = existsThisObject(management.getOrders(), "Type the code of the order to update", "order, please try again");
		while(true) {
			try {
				int status = selectOption("\nSelect the new status\n1. Requested\n2. In process\n3. Sent\n4. Delivered", 1, 4);
				String msg = management.updateStatus(orderCode, status);
				System.out.print(msg);
				return -1;
			} catch(InvalidOption e){
				System.out.print("\nThe order status cannot be downgraded, please try again\n");
			}
		}
	}
	
	public int searchCustomer() throws IOException {
		String name = typeString("\nType the name of the customer to search").trim();
		String lastName = typeString("Type the last name of the customer to search").trim();
		String msg = management.searchCustomer(lastName+" "+name);
		System.out.print(msg);
		return -1;
	}
	
	public void addRestaurant() throws IOException {
		String name = typeString("\nType a name of the restaurant");
		String nit = typeString("Type a nit of the restaurant");
		String manager = typeString("Type a name of the restaurant manager");
		String msg = management.addRestaurant(name, nit, manager);
		System.out.print(msg);
	}
	
	public void updateRestaurant() throws IOException {
		String nit = existsThisObject(management.getRestaurants(), "Type a nit of the restaurant to update", "restaurant, please try again");
		String name = typeString("\nType the new name of the restaurant");
		String manager = typeString("Type the new name of the restaurant manager");
		String msg = management.updateRestaurant(name, nit, manager);
		System.out.print(msg);
	}
	
	public void addProduct() throws IOException {
		String code = typeString("\nType a code of the product");
		String name = typeString("Type a name of the product");
		String descripcion = typeString("Type a descripcion of the product");
		double cost = typeDouble("Type a cost of the product");
		String nitRestaurant = existsThisObject(management.getRestaurants(), "Type a nit of the restaurant", "restaurant, please try again");
		String msg = management.addProduct(code, name, descripcion, cost, nitRestaurant);
		System.out.print(msg);
	}
	
	public void updateProduct() throws IOException {
		String code = existsThisObject(management.getProducts(), "Type a code of the product to update", "product, please try again");
		String name = typeString("Type the new name of the product");
		String descripcion = typeString("Type the new descripcion of the product");
		double cost = typeDouble("Type the new cost of the product");
		String msg = management.updateProduct(code, name, descripcion, cost);
		System.out.print(msg);
	}
	
	public void addCustomer() throws IOException {
		String idType = typeString("\nType the id type of the customer");
		String id = typeString("Type the id of the customer");
		String name = typeString("Type the name of the customer");
		String lastName = typeString("Type the last name of the customer");
		Long phone = typeLong("Type the phone of the customer");
		String address = typeString("Type the address of the customer");
		String msg = management.addCustomer(idType, id, name, lastName, phone, address);
		System.out.print(msg);
	}
	
	public void updateCustomer() throws IOException {
		String id = existsThisObject(management.getCustomers(), "Type a id of the customer to update", "customer, please try again");
		String idType = typeString("\nType the new id type of the customer");
		String name = typeString("Type the new name of the customer");
		String lastName = typeString("Type the new last name of the customer");
		Long phone = typeLong("Type the new phone of the customer");
		String address = typeString("Type the new address of the customer");
		String msg = management.updateCustomer(idType, id, name, lastName, phone, address);
		System.out.print(msg);
	}
	
	public void addOrder() throws IOException {
		String id = existsThisObject(management.getCustomers(), "\nType the id of the customer", "customer, please try again");
		String nit = existsThisObject(management.getRestaurants(), "Type a nit of the restaurant", "restaurant, please try again");
		ArrayList<String> products = new ArrayList<String>();
		ArrayList<Integer> quantitys = new ArrayList<Integer>();
		int option = 0;
		do{
			String code = addCodeProduct(nit, "\nType a code of the product", "restaurant product, please try again");
			products.add(code);
			int quantity = typeInt("\nType a product quantity");
			quantitys.add(quantity);
			option = selectOption("\nDo you want to add another product?\n1. Add\n0. Finish", 0, 1);	
		} while(option!=0);
		String msg = management.addOrder(id, nit, products, quantitys);
		System.out.print(msg);
	}
	
	public void updateOrder() throws IOException {
		String orderCode = existsThisObject(management.getOrders(), "Type the code of the order to update", "order, please try again");
		String id = existsThisObject(management.getCustomers(), "\nType the id of the customer", "customer, please try again");
		String nit = existsThisObject(management.getRestaurants(), "Type a nit of the restaurant", "restaurant, please try again");
		ArrayList<String> products = new ArrayList<String>();
		ArrayList<Integer> quantitys = new ArrayList<Integer>();
		int option = 0;
		do{
			String code = addCodeProduct(nit, "\nType a code of the product", "restaurant product, please try again");
			products.add(code);
			int quantity = typeInt("\nType a product quantity");
			quantitys.add(quantity);
			option = selectOption("\nDo you want to add another product?\n1. Add\n0. Finish", 0, 1);	
		} while(option!=0);
		String msg = management.updateOrder(orderCode, id, nit, products, quantitys);
		System.out.print(msg);
	}
	
	public String addCodeProduct(String nit, String msgUser, String msgException) throws IOException {
		String code = "";
		int position = -1;
		while(position==-1) {
			try {
				code = typeString(msgUser);
				position = management.searchObject(code, management.getProducts());
				if(position==-1) {
					throw new NonExistent();
				}
				if(!management.getProducts().get(position).getNitRestaurant().equals(nit)) {
					throw new NonExistent();
				}
			} catch(NonExistent e) {
				System.out.println(e.getMessage()+msgException);
			}
		}
		return code;
	}
	
	public String existsThisObject(ArrayList<?> list, String msgUser, String msgException) throws IOException {
		String msg = "";
		int position = -1;
		while(position==-1) {
			try {
				msg = typeString(msgUser);
				position = management.searchObject(msg, list);
				if(position==-1) {
					throw new NonExistent();
				}
			} catch(NonExistent e) {
				System.out.println(e.getMessage()+msgException);
			}
		}
		
		return msg;
	}
	
	public int selectOption(String msg, int min, int max) throws IOException{
		while(true) {
			try {
				System.out.println(msg);
				if(msg == textManagement()) { 
					msg = "\n"+msg;
				}
				int number = Integer.parseInt(br.readLine());
				if(number<min || number>max) {
					throw new InvalidOption();
				}
				return number;
			} catch(NumberFormatException e){
				System.out.print("\nThe type of data entered is not supported, please try again\n");
			} catch(InvalidOption e) {
				System.out.print(e.getMessage());
			}
		}
	}
	
	public String typeString(String msg) throws IOException {
		System.out.print(msg+": ");
		String str = br.readLine();
		return str;
	}
	
	public double typeDouble(String msg) throws IOException {
		while(true) {
			try {
				System.out.print(msg+": ");
				double number = Double.parseDouble(br.readLine());
				return number;
			} catch(NumberFormatException e) {
				System.out.print("\nThe type of data entered is not supported, please try again\n\n");
			}
		}
	}
	
	public int typeInt(String msg) throws IOException {
		while(true) {
			try {
				System.out.print(msg+": ");
				int number = Integer.parseInt(br.readLine());
				return number;
			} catch(NumberFormatException e) {
				System.out.print("\nThe type of data entered is not supported, please try again\n\n");
			}
		}
	}
	
	public Long typeLong(String msg) throws IOException {
		while(true) {
			try {
				System.out.print(msg+": ");
				Long number = Long.parseLong(br.readLine());
				return number;
			} catch(NumberFormatException e) {
				System.out.print("\nThe type of data entered is not supported, please try again\n\n");
			}
		}
	}
}
