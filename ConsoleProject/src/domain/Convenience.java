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

	// �޴�
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		while (true) {
			scan = new Scanner(System.in);
			System.out.println("1ȸ������ 2�α��� 3����");
			System.out.print("���� : ");
			try {
				int select = scan.nextInt();
				if (select == 1)
					signup();
				else if (select == 2) {
					String id = login();
					while (id != null) {
						System.out.println("0�α׾ƿ� 1�Ǹ� 2������ 3���ΰ���");
						System.out.print("���� : ");
						int select2 = scan.nextInt();
						if (select2 == 1) {
							System.out.println("----> �Ǹ�");
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
					System.out.println("----> ���� �޴��Դϴ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> �ٽ� �Է����ּ���");
			}

		}

	}

	// �������޴�
	public void productMenu() {
		while (true) {
			System.out.println("0���ư��� 1����� 2���Ȯ�� 3������ 4���Ȯ�� 5���ó��");
			System.out.print("���� : ");
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
		System.out.println("----> ȸ������");
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
			System.out.println("----> ȸ������ �Ϸ�");
		}
	}

	@Override
	public String login() {
		// TODO Auto-generated method stub
		System.out.println("----> �α���");
		System.out.print("ID : ");
		String id = Main.scan.next();
		System.out.print("Password : ");
		String password = Main.scan.next();

		for (User temp : ListCollection.userList) {
			if (temp.getId().equals(id) && temp.getPassword().equals(password)) {
				System.out.println("----> �α��� ����");
				this.money = temp.getMoney();
				return id;
			}
		}
		System.out.println("----> ������ ȸ�������� �����ϴ�");
		return null;
	}

	public String findId() {
		System.out.print("ID : ");
		String id = Main.scan.next();

		for (int i = 0; i < ListCollection.userList.size(); i++) {
			if (ListCollection.userList.get(i).equals(id)) {
				System.out.println("������ ���̵� �����մϴ�");
				return null;
			}
		}
		return id;
	}

	@Override
	public void addProduct() {
		// TODO Auto-generated method stub
		System.out.println("----> �����");
		System.out.print("��ǰ�� : ");
		String name = Main.scan.next();
		System.out.print("���� : ");
		int price = Main.scan.nextInt();
		System.out.print("��� : ");
		int stock = Main.scan.nextInt();
		System.out.print("�������(YYYYMMDD) : ");
		String date = Main.scan.next();
		Product temp = new Product(name, price, stock, date);
		ListCollection.productList.add(temp);
		try {
			FileUtill.writeFile(1,"c:/java/", "product.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----> ��ǰ��� �Ϸ�");
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
			System.out.println("----> �����ǰó��");
			System.out.print("ó���� �����ǰ��ȣ : ");
			int no = scan.nextInt();
			if (ListCollection.wasteList.get(no) != null) {
				ListCollection.wasteList.remove(no);
				System.out.println("----> ���ó�� �Ϸ�");
			} else {
				System.out.println("----> ��ǰ��ȣ�� �߸� �Է��ϼ̽��ϴ�");
			}
		}else{
			System.out.println("----> ����ǰ�� �����ϴ�");
		}
	}

	@Override
	public void deleteProduct() {
		// TODO Auto-generated method stub
		if (!ListCollection.productList.isEmpty()) {
			scan = new Scanner(System.in);
			getProduct();
			System.out.println("----> ������");
			System.out.print("ó���� ��ǰ��ȣ : ");
			int no = scan.nextInt();
			if (ListCollection.productList.get(no) != null) {
				ListCollection.productList.remove(no);
				System.out.println("----> ������ �Ϸ�");
			} else {
				System.out.println("----> ��ǰ��ȣ�� �߸� �Է��ϼ̽��ϴ�");
			}
		} else {
			System.out.println("----> ��� �����ϴ�");
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
			System.out.println("----> ���Ȯ��");
			System.out.println("============================================================================");
			System.out.println("��ǰ��ȣ\t\t��ǰ��\t\t���\t\t����\t\t�������");
			System.out.println("============================================================================");
			for (int i=0;i<ListCollection.productList.size();i++) {
				Product temp = ListCollection.productList.get(i);
				System.out.println("[" + i + "]\t\t" + temp.getProductName() + "\t\t" + temp.getStock()
						+ "\t\t" + temp.getPrice() + "\t\t" + temp.getEndDate());
			}
			System.out.println("============================================================================");
		} else {
			System.out.println("----> ��� �����ϴ�");
		}

	}

	@Override
	public void getWaste() {
		// TODO Auto-generated method stub
		addWaste();
		if (!ListCollection.wasteList.isEmpty()) {
			System.out.println("----> ����ǰȮ��");
			System.out.println("============================================================================");
			System.out.println("��ǰ��ȣ\t\t��ǰ��\t\t���\t\t����\t\t�������");
			System.out.println("============================================================================");
			for (int i=0;i<ListCollection.wasteList.size();i++) {
				Product temp = ListCollection.wasteList.get(i);
				System.out.println("[" + i + "]\t\t" + temp.getProductName() + "\t\t" + temp.getStock()
						+ "\t\t" + temp.getPrice() + "\t\t" + temp.getEndDate());
			}
			System.out.println("============================================================================");
		} else {
			System.out.println("----> ����ǰ�� �����ϴ�.");
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
