package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	public static int REQUESTED = 1;
	public static int INPROCESS = 2;
	public static int SENT = 3;
	public static int DELIVERED = 4;
	
	private String code;
	private Date date;
	private String idCustomer;
	private String nitRestaurant;
	private ArrayList<String> products;
	private ArrayList<Integer> quantitys;
	private int status;
	
	public Order(String code, String idCustomer, String nitRestaurant, ArrayList<String> products, ArrayList<Integer> quantitys) {
		this.code = generateCode(code);
		this.date = new Date();
		this.idCustomer = idCustomer;
		this.nitRestaurant = nitRestaurant;
		this.products = products;
		this.quantitys = quantitys;
		status = REQUESTED;
	}
	
	public Order(String code, String date, String idCustomer, String nitRestaurant, String product, int quantity, String status) {
		this.code = code;
		String[] str = date.split("/");
		long mls = ((Integer.parseInt(str[2])-1970)*365*24*3600*1000)+(Integer.parseInt(str[1])*30*24*3600*1000)+(Integer.parseInt(str[0])*24*3600*1000);
		this.date = new Date(mls);
		this.idCustomer = idCustomer;
		this.nitRestaurant = nitRestaurant;
		products = new ArrayList<String>();
		products.add(product);
		quantitys = new ArrayList<Integer>();
		quantitys.add(quantity);
		this.status = assingStatus(status);
	}
	
	private int assingStatus(String status) {
		if("requested".equalsIgnoreCase(status)) {
			return 1;
		} else if("in process".equalsIgnoreCase(status)) {
			return 2;
		} else if("sent".equalsIgnoreCase(status)) {
			return 3;
		} else if("delivered".equalsIgnoreCase(status)) {
			return 4;
		} 
		return 0;
	}

	public String generateCode(String code) {
		int ncode = Integer.parseInt(code);
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setOneProduct(String codeProduct) {
		products.add(codeProduct);
	}

	public void setOneQuantity(int quantity) {
		quantitys.add(quantity);
	}
}
