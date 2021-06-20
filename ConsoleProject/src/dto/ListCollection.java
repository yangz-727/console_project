package dto;

import java.util.ArrayList;

import domain.Admin;
import domain.Discount;
import domain.Product;
import domain.Sales;




public class ListCollection {
	
	//관리자정보
	public static Admin admin = new Admin();
	
	//제품리스트
	public static ArrayList<Product> productList = new ArrayList<>();
	
	//폐기리스트
	public static ArrayList<Product> wasteList = new ArrayList<Product>();
	
	//할인리스트
	public static ArrayList<Discount> discountList = new ArrayList<Discount>();
	
	//매출리스트
	public static ArrayList<Sales> salesList = new ArrayList<Sales>();
	
}
