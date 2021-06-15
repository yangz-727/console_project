package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.Main;
import dto.ListCollection;

public class Product {
	private int no;
	private String productName;
	private int price;
	private int stock;
	private String endDate;
	
	//기본 생성자
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String productName, int price, int stock, String date) {
		this.no = ListCollection.productList.size()+1;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		try {
			Date temp = new SimpleDateFormat("yyyyMMdd").parse(date);
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}
