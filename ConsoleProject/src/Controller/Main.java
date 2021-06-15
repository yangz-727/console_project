package Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Convenience;
import domain.Discount;
import domain.FileUtill;
import domain.Product;
import domain.User;
import dto.ListCollection;

public class Main {

	
	public static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		run();
	}
	
	
	public static void run() {
		try {
			FileUtill.readFile(1,new File("c:/java/","product.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("====================== 편의점 재고관리 프로그램 ======================");
		Convenience convenience = new Convenience();
		convenience.menu();
	}
}
