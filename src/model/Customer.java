package model;

public class Customer {
	
	private String idType;
	private String id;
	private String name;
	private String lastName;
	private Long phone;
	private String address;
	
	public Customer(String idType, String id, String name, String lastName, Long phone, String address) {
		this.idType = idType;
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}
	
	public String toString() {
		return id;
	}
	
	public String getAllName() {
		String msg = lastName.trim()+" "+name.trim();
		return msg;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
