package Model;

import java.util.Date;

public class CreditCard {
	private int id;
	private String name;
	private String number;
	private String expirationDate;
	private int securityCode;
	private String username;
	
	public CreditCard() {
		// TODO Auto-generated constructor stub
	}
	
	public CreditCard(int id, String name, String number, String expirationDate, int securityCode, String username) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
}
