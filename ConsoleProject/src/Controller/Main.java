package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import domain.Convenience;
import domain.Discount;
import domain.Product;
import domain.User;

public class Main {

	
	public static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		run();
	}
	
	
	public static void run() {
		System.out.println("====================== ������ ������ ���α׷� ======================");
		Convenience convenience = new Convenience();
		convenience.menu();
	}
}
