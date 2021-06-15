package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import domain.Convenience;
import domain.Discount;
import domain.Product;
import domain.User;

public class Main {

	public static ArrayList<User> userList = new ArrayList<>();
	public static ArrayList<Product> productList = new ArrayList<>();
	public static ArrayList<Product> wasteList = new ArrayList<Product>();
	public static ArrayList<Discount> discountList = new ArrayList<Discount>();
	public static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		run();
	}
	
	
	public static void run() {
		System.out.println("====================== 편의점 재고관리 프로그램 ======================");
		Convenience convenience = new Convenience();
		convenience.menu();
	}
}
