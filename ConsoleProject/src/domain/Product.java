package domain;

import java.util.Date;

public class Product {
	private int no;
	private String productName;
	private int price;
	private int stock;
	private Date endDate;
	
	//기본 생성자
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(int no, String productName, int price, int stock, Date date) {
		this.no = no;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.endDate = date;
	}
	
	//getter/setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
