package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
	private String productName;
	private int price;
	private int stock;
	private String endDate;
	
	//기본 생성자
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String productName, int price, int stock, String date) {
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.endDate = date;
	}
	
	//getter/setter
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
