package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import domain.Convenience_test;
import domain.Discount;
import domain.Product_test;
import domain.User;

public class Main_test {

	public static ArrayList<User> userList = new ArrayList<>();
	public static ArrayList<Product_test> productList = new ArrayList<>();
	public static ArrayList<Product_test> wasteList = new ArrayList<Product_test>();
	public static ArrayList<Discount> discountList = new ArrayList<Discount>();
	
	public static void main(String[] args) {
		run();
	}
	
	
	public static void run() {
		System.out.println("====================== 편의점 재고관리 프로그램 ======================");
		Convenience_test convenience = new Convenience_test();
		convenience.menu();
	}
}
