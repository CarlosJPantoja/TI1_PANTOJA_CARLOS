package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	private String code;
	private Date date;
	private String idCustomer;
	private String nitRestaurant;
	private ArrayList<String> products;
	private ArrayList<Integer> quantitys;
	
	public Order(String code, String idCustomer, String nitRestaurant, ArrayList<String> products, ArrayList<Integer> quantitys) {
		this.code = generateCode(code);
		this.date = new Date();
		this.idCustomer = idCustomer;
		this.nitRestaurant = nitRestaurant;
		this.products = products;
		this.quantitys = quantitys;
	}
	
	public String generateCode(String code) {
		int ncode = Integer.parseInt(code);;
		ncode = ncode+1;
		code = ncode+"";
		return code;
	}
	
	public String toString() {
		return code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNitRestaurant() {
		return nitRestaurant;
	}

	public void setNitRestaurant(String nitRestaurant) {
		this.nitRestaurant = nitRestaurant;
	}

	public ArrayList<String> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}

	public ArrayList<Integer> getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(ArrayList<Integer> quantitys) {
		this.quantitys = quantitys;
	}
}
