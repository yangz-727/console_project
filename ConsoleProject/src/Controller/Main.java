package Controller;

import java.util.Scanner;
import domain.Convenience;

public class Main {

	
	public static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		run();
	}
	
	
	public static void run() {
		System.out.println("====================== ������ ������ ���α׷� ======================");
		Convenience convenience = new Convenience();
		convenience.getFiles();
		convenience.menu();
	}
	
}
