package dto;

import java.util.ArrayList;

import domain.Admin;
import domain.Discount;
import domain.Product;
import domain.Sales;




public class ListCollection {
	
	//����������
	public static Admin admin = new Admin();
	
	//��ǰ����Ʈ
	public static ArrayList<Product> productList = new ArrayList<>();
	
	//��⸮��Ʈ
	public static ArrayList<Product> wasteList = new ArrayList<Product>();
	
	//���θ���Ʈ
	public static ArrayList<Discount> discountList = new ArrayList<Discount>();
	
	//���⸮��Ʈ
	public static ArrayList<Sales> salesList = new ArrayList<Sales>();
	
}
