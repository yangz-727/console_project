package domain;

import Controller.Main;

public class Convenience implements Pos {
	
	int money;
	
	public Convenience() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void signup() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("----> 회원가입");
			String id = findId();
			System.out.print("Password : ");
			String password = Main.scan.next();
			System.out.print("name : ");
			String name = Main.scan.next();
			System.out.print("email : ");
			String email = Main.scan.next();
			System.out.print("money : ");
			int money = Main.scan.nextInt();
			User temp = new User(id, password, name, email, money);
			Main.userList.add(temp);
			System.out.println("----> 회원가입 완료");
		}
	}
	
	public static String findId() {
		System.out.print("ID : ");
		String id = Main.scan.next();
		
		for(int i=0;i<Main.userList.size();i++) {
			if(Main.userList.get(i).equals(id)) {
				System.out.println("동일한 아이디가 존재합니다");
				return null;
			}
		}
		
		return id;
	}

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getStock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDeadline() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getDiscount() {
		// TODO Auto-generated method stub

	}

	@Override
	public Product findStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		System.out.println("----> 회원가입");
		String id = findId();
		System.out.print("Password : ");
		String password = Main.scan.next();
		System.out.print("name : ");
		String name = Main.scan.next();
		System.out.print("email : ");
		String email = Main.scan.next();
		System.out.print("money : ");
		int money = Main.scan.nextInt();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}
	
	public static void run() {
		
	}

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		
	}

}
