package domain;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Controller.Main;
import dto.ListCollection;
import domain.FileUtill;
import java.util.Date;
import java.util.Scanner;

public class Convenience implements Pos {

	int money;
	Scanner scan;

	public Convenience() {
		// TODO Auto-generated constructor stub
	}

	// 메뉴
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		while (true) {
			scan = new Scanner(System.in);
			System.out.println("1회원가입 2로그인 3종료");
			System.out.print("선택 : ");
			try {
				int select = scan.nextInt();
				if (select == 1)
					signup();
				else if (select == 2) {
					String id = login();
					while (id != null) {
						System.out.println("0로그아웃 1판매 2재고관리 3할인관리");
						System.out.print("선택 : ");
						int select2 = scan.nextInt();
						if (select2 == 1) {
							System.out.println("----> 판매");
						} else if (select == 2) {
							productMenu();
						} else if (select == 3) {

						} else if (select == 0) {
							break;
						}
					}
				} else if (select == 3) {
					exit();
					return;
				} else {
					System.out.println("----> 없는 메뉴입니다");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 다시 입력해주세요");
			}

		}

	}

	// 재고관리메뉴
	public void productMenu() {
		while (true) {
			System.out.println("0돌아가기 1재고등록 2재고확인 3재고삭제 4폐기확인 5폐기처분");
			System.out.print("선택 : ");
			int select = Main.scan.nextInt();
			if (select == 1) {
				addProduct();
			} else if (select == 2) {
				getProduct();
			} else if (select == 3) {
				deleteProduct();
			} else if (select == 4) {
				getWaste();
			} else if (select == 5) {
				deleteWaste();
			} else if (select == 0) {
				break;
			}
		}
	}

	@Override
	public void signup() {
		// TODO Auto-generated method stub
		System.out.println("----> 회원가입");
		String id = findId();
		if (id != null) {
			System.out.print("Password : ");
			String password = Main.scan.next();
			System.out.print("name : ");
			String name = Main.scan.next();
			System.out.print("email : ");
			String email = Main.scan.next();
			System.out.print("money : ");
			int money = Main.scan.nextInt();
			User temp = new User(id, password, name, email, money);
			ListCollection.userList.add(temp);
			System.out.println("----> 회원가입 완료");
		}
	}

	@Override
	public String login() {
		// TODO Auto-generated method stub
		System.out.println("----> 로그인");
		System.out.print("ID : ");
		String id = Main.scan.next();
		System.out.print("Password : ");
		String password = Main.scan.next();

		for (User temp : ListCollection.userList) {
			if (temp.getId().equals(id) && temp.getPassword().equals(password)) {
				System.out.println("----> 로그인 성공");
				this.money = temp.getMoney();
				return id;
			}
		}
		System.out.println("----> 동일한 회원정보가 없습니다");
		return null;
	}

	public String findId() {
		System.out.print("ID : ");
		String id = Main.scan.next();

		for (int i = 0; i < ListCollection.userList.size(); i++) {
			if (ListCollection.userList.get(i).equals(id)) {
				System.out.println("동일한 아이디가 존재합니다");
				return null;
			}
		}
		return id;
	}

	@Override
	public void addProduct() {
		// TODO Auto-generated method stub
		System.out.println("----> 재고등록");
		System.out.print("제품명 : ");
		String name = Main.scan.next();
		System.out.print("가격 : ");
		int price = Main.scan.nextInt();
		System.out.print("재고 : ");
		int stock = Main.scan.nextInt();
		System.out.print("유통기한(YYYYMMDD) : ");
		String date = Main.scan.next();
		Product temp = new Product(name, price, stock, date);
		ListCollection.productList.add(temp);
		try {
			FileUtill.writeFile(1,"c:/java/", "product.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----> 제품등록 완료");
	}

	@Override
	public void addWaste() {
		Date today = new Date();
		for (int i = 0; i < ListCollection.productList.size(); i++) {
			try {
				Product temp = ListCollection.productList.get(i);
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(temp.getEndDate());
				if (today.after(date)) {
					ListCollection.wasteList.add(temp);
					ListCollection.productList.remove(i);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}

	@Override
	public void addDiscount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDiscount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWaste() {
		// TODO Auto-generated method stub
		if(!ListCollection.wasteList.isEmpty()) {
			scan = new Scanner(System.in);
			getWaste();
			System.out.println("----> 페기제품처분");
			System.out.print("처분할 폐기제품번호 : ");
			int no = scan.nextInt();
			if (ListCollection.wasteList.get(no) != null) {
				ListCollection.wasteList.remove(no);
				System.out.println("----> 폐기처분 완료");
			} else {
				System.out.println("----> 제품번호를 잘못 입력하셨습니다");
			}
		}else{
			System.out.println("----> 폐기상품이 없습니다");
		}
	}

	@Override
	public void deleteProduct() {
		// TODO Auto-generated method stub
		if (!ListCollection.productList.isEmpty()) {
			scan = new Scanner(System.in);
			getProduct();
			System.out.println("----> 재고삭제");
			System.out.print("처분할 제품번호 : ");
			int no = scan.nextInt();
			if (ListCollection.productList.get(no) != null) {
				ListCollection.productList.remove(no);
				System.out.println("----> 재고삭제 완료");
			} else {
				System.out.println("----> 제품번호를 잘못 입력하셨습니다");
			}
		} else {
			System.out.println("----> 재고가 없습니다");
		}
	}

	@Override
	public void pay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getProduct() {
		// TODO Auto-generated method stub
		
		addWaste();
		
		if (!ListCollection.productList.isEmpty()) {
			System.out.println("----> 재고확인");
			System.out.println("============================================================================");
			System.out.println("제품번호\t\t제품명\t\t재고\t\t가격\t\t유통기한");
			System.out.println("============================================================================");
			for (int i=0;i<ListCollection.productList.size();i++) {
				Product temp = ListCollection.productList.get(i);
				System.out.println("[" + i + "]\t\t" + temp.getProductName() + "\t\t" + temp.getStock()
						+ "\t\t" + temp.getPrice() + "\t\t" + temp.getEndDate());
			}
			System.out.println("============================================================================");
		} else {
			System.out.println("----> 재고가 없습니다");
		}

	}

	@Override
	public void getWaste() {
		// TODO Auto-generated method stub
		addWaste();
		if (!ListCollection.wasteList.isEmpty()) {
			System.out.println("----> 폐기상품확인");
			System.out.println("============================================================================");
			System.out.println("제품번호\t\t제품명\t\t재고\t\t가격\t\t유통기한");
			System.out.println("============================================================================");
			for (int i=0;i<ListCollection.wasteList.size();i++) {
				Product temp = ListCollection.wasteList.get(i);
				System.out.println("[" + i + "]\t\t" + temp.getProductName() + "\t\t" + temp.getStock()
						+ "\t\t" + temp.getPrice() + "\t\t" + temp.getEndDate());
			}
			System.out.println("============================================================================");
		} else {
			System.out.println("----> 폐기상품이 없습니다.");
		}
	}

	public void getDiscount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}
	
	public void getProductFile() {
		try {
			FileUtill.readFile(1,new File("c:/java/","product.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
