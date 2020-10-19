package model;

public class Product{
	
	private String code;
	private String name;
	private String descripcion;
	private double cost;
	private String nitRestaurant;
	
	public Product(String code, String name, String descripcion, double cost, String nitRestaurant) {
		this.code = code;
		this.name = name;
		this.descripcion = descripcion;
		this.cost = cost;
		this.nitRestaurant = nitRestaurant;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getNitRestaurant() {
		return nitRestaurant;
	}

	public void setNitRestaurant(String nitRestaurant) {
		this.nitRestaurant = nitRestaurant;
	}
}