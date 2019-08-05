package Model;

public class User {
	private String username;
	private String name;
	private String email;
	private Long phone;
	private String address;
	private String authToken;
	
	public User() {
		// TODO Auto-generated constructor stub
	}	
	
	public User(String username, String name, String email, Long phone, String address, String authToken) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.authToken = authToken;
	}
	
	public User(String username, String name, String email, Long phone, String address) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	@Override
	public String toString() {
		return "{username:" + username + ",\n name:" + name + ",\n email:" + email + ",\n phone:" + phone + ",\n address:"
				+ address + ",\n authToken:" + authToken + "}";
	}
	
	
	
	
}
