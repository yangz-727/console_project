package domain;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Controller.Main;
import dto.ListCollection;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Convenience implements Pos {
	int code=1;
	int money;

	public Convenience() {
		// TODO Auto-generated constructor stub
	}

	// ���� �޴�
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("1. �α���");
			System.out.println("2. ����");
			System.out.println("=========================");
			System.out.print("���� : ");
			int select = 0;
			try {
				select = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
			}

			if (select == 1) {
				boolean login = login();
				while (login) {
					Main.scan = new Scanner(System.in);
					System.out.println("=========================");
					System.out.println("0. �α׾ƿ�");
					System.out.println("1. �ǸŰ���");
					System.out.println("2. ������");
					System.out.println("3. ���ΰ���");
					System.out.println("4. ���������");
					System.out.println("=========================");
					System.out.print("���� : ");
					int select2 = 4;
					try {
						select2 = Main.scan.nextInt();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
					}

					if (select2 == 0) {
						System.out.println("----> �α׾ƿ�");
						login = false;
					} else if (select2 == 1) {
						System.out.println("----> �ǸŰ���");
						sellMenu();
					} else if (select2 == 2) {
						System.out.println("----> ������");
						productMenu();
					} else if (select2 == 3) {
						System.out.println("----> ���ΰ���");
						discountMenu();
					}else if(select2 == 4) {
						exit();
					}else {
						System.out.println("----> �ٽ� �Է����ּ���");
					}

				}
			} else if (select == 2) {
				exit();
				break;
			} else {
				System.out.println("----> �ٽ� �Է����ּ���");
			}

		}

	}

	// �ǸŸ޴�
	public void sellMenu() {
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("0. ���ư���");
			System.out.println("1. ��ǰ����");
			System.out.println("2. ����Ȯ��");
			System.out.println("3. ����׷���");
			System.out.println("4. ���������");
			System.out.println("=========================");
			System.out.print("���� : ");
			int select = 8;
			try {
				select = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
			}
			if (select == 1) {
				System.out.println("----> ��ǰ�Ǹ�");
				pay();
			} else if (select == 2) {
				System.out.println("----> ����Ȯ��");
				getProfit();
			} else if (select == 3) {
				System.out.println("----> ����׷���");
				getGraph();
			} else if (select == 4) {
				exit();
			} else if (select == 0) {
				break;
			} else {
				System.out.println("----> �ٽ� �Է����ּ���");
			}
		}

	}

	// ����
	@Override
	public void pay() {
		// TODO Auto-generated method stub
		getProduct();
		int result = 0;
		String receipt = "";
		int sw = 1;
		while (true) {
			Main.scan = new Scanner(System.in);
			if (sw == 1) {
				try {

					System.out.print("��ǰ��ȣ : ");
					int no = Main.scan.nextInt();
					System.out.print("���Ű��� : ");
					int count = Main.scan.nextInt();
					Product temp = findProduct(no);

					if (temp != null) {
						if (temp.getStock() >= count) {
							int price = 0;
							int fd = findDiscount(no);
							if (fd != 0) {
								double discount = (fd * 1.0) / 100.0;
								price = (temp.getPrice() * count) - ((int) ((temp.getPrice() * count) * discount));
								result += price;
								receipt += " "+temp.getProductName() + "\t\t" + temp.getPrice() + "��\t\t" + count + "��\t\t"
										+ price + "��\t\t" + fd + "% ��������\n";
							} else {
								price = temp.getPrice() * count;
								result += price;
								receipt += " "+temp.getProductName() + "\t\t" + temp.getPrice() + "��\t\t" + count + "��\t\t"
										+ price + "��\n";
							}
							ListCollection.productList.get(no).setStock(temp.getStock()-count);
							FileUtill.writeFile(1, "c:/java/", "product.txt");
						} else {
							System.out.println("----> ��� �����մϴ�");
							continue;
						}
					} else {
						System.out.println("----> ��ǰ�� �����ϴ�");
						continue;
					}

					System.out.println("=========================");
					System.out.println("0. �����ϱ�");
					System.out.println("1. ������ǰ�߰�");
					System.out.println("=========================");
					System.out.print("���� : ");
					sw = Main.scan.nextInt();

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ� / ���� ������Ʈ ����");
				}
			} else {
				this.money+=result;
				receipt += "�հ� : "+result+"��";
				System.out.println("----> �����Ϸ�");
				System.out.println("==================================== ������ ====================================");
				System.out.println(" ��ǰ��\t\t��ǰ����\t\t���Ű���\t\t�ѱݾ�\t\t����");
				System.out.println("==============================================================================");
				System.out.println(receipt);
				System.out.println("==============================================================================");
				break;
			}

		}
	}
	
	public void getGraph() {
		double total=0.0;
		for(Sales temp : ListCollection.salesList) {
			if(temp.getMoney()!=0) total+=(double)temp.getMoney();
		}
		for(Sales temp : ListCollection.salesList) {
			int percent = (int) ((temp.getMoney()/total)*100);
			String year = temp.getDate().substring(0, 4);
			String month = temp.getDate().substring(4, 6);
			String day = temp.getDate().substring(6);
			System.out.print("[ "+year+"�� "+month+"�� "+day+"�� ]");
			int count =percent/5;
			for(int i=0;i<count;i++) {
				System.out.print("��");
			}
			System.out.println();
		}
	}
	
	public void storeProfit() {
		Sales temp = new Sales(this.money-ListCollection.admin.getMoney());
		ListCollection.salesList.add(temp);
		try {
			getProfit();
			System.out.println("[ ������ �ܾ� ] "+this.money);
			FileUtill.writeFile(5, "c:/java/", "sales.txt");
			System.out.println("----> �������� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> �������� ����");
		}
	}

	// ����Ȯ��(�����ܾ�(this.money) - �����ܾ�(admin))
	public void getProfit() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm��");
		String date = sdf.format(cal.getTime());
		System.out.println("[ " + date + " : ���� ] " + (this.money-ListCollection.admin.getMoney()) + "��");
	}

	// �������޴�
	public void productMenu() {
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("0. ���ư���");
			System.out.println("1. �����");
			System.out.println("2. ���Ȯ��");
			System.out.println("3. ������");
			System.out.println("4. ���Ȯ��");
			System.out.println("5. ���ó��");
			System.out.println("6. ���������");
			System.out.println("=========================");
			System.out.print("���� : ");
			int select = 7;
			try {
				select = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
			}
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
			}else if(select == 6) {
				exit();
			}else {
				System.out.println("----> �ٽ� �Է����ּ���");
			}
		}
	}

	// ���ΰ����޴�
	public void discountMenu() {
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("0. ���ư���");
			System.out.println("1. ���ε��");
			System.out.println("2. ���θ��");
			System.out.println("3. �������");
			System.out.println("4. ���������");
			System.out.println("=========================");
			System.out.print("���� : ");
			int select = 4;
			try {
				select = Main.scan.nextInt();
				if (select == 1) {
					addDiscount();
				} else if (select == 2) {
					getDiscount();
				} else if (select == 3) {
					deleteDiscount();
				}else if(select==4) {
					exit();
				}else if (select == 0) {
					break;
				} else {
					System.out.println("----> �ٽ� �Է����ּ���");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
			}
		}
	}

	// �α���
	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		System.out.println("----> �α���");
		System.out.print("���̵� : ");
		String id = Main.scan.next();
		System.out.print("�н����� : ");
		String password = Main.scan.next();

		Admin temp = ListCollection.admin;

		if (temp.getId().equals(id) && temp.getPassword().equals(password)) {
			this.money = temp.getMoney();
			System.out.println("----> �α��� ����");
			return true;
		}
		System.out.println("----> �α��� ����");
		return false;
	}

	// �����
	@Override
	public void addProduct() {
		// TODO Auto-generated method stub
		Main.scan = new Scanner(System.in);
		System.out.println("----> �����");
		System.out.print("��ǰ�� : ");
		String name = Main.scan.next();
		int price = 0;
		int stock = 0;
		try {
			System.out.print("���� : ");
			price = Main.scan.nextInt();
			System.out.print("��� : ");
			stock = Main.scan.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
			return;
		}

		System.out.print("�������(YYYYMMDD) : ");
		String date = Main.scan.next();
		Date today = new Date();
		try {
			if (today.before(new SimpleDateFormat("yyyyMMdd").parse(date))) {
				Product temp = new Product(name, price, stock, date);
				ListCollection.productList.add(temp);
				FileUtill.writeFile(1, "c:/java/", "product.txt");
				System.out.println("----> ��ǰ��� �Ϸ�");
			} else {
				System.out.println("----> ��������� ���� ��ǰ�� ����� �� �����ϴ�");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> ��ǰ��� ����");
			return;
		}

	}

	// �����
	@Override
	public void addWaste() {
		Date today = new Date();
		for (int i = 0; i < ListCollection.productList.size(); i++) {
			Product temp = ListCollection.productList.get(i);
			try {
				// ��¥�� ���� ��ǰ ����ǰ��Ͽ� �߰� �� ����Ͽ��� ����
				if (today.after(new SimpleDateFormat("yyyyMMdd").parse(temp.getEndDate()))) {
					ListCollection.wasteList.add(temp);
					ListCollection.productList.remove(i);
					FileUtill.writeFile(2, "c:/java/", "waste.txt");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("----> ����ǰ ��� ����");
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("----> ����ǰ ���� ������Ʈ ����");
			}
		}
	}

	// ���ε��
	@Override
	public void addDiscount() {
		Main.scan = new Scanner(System.in);
		getProduct();
		System.out.println("----> ���ε��");
		int no = 0;
		int rate = 0;
		int date = 0;

		try {
			System.out.print("��ǰ��ȣ: ");
			no = Main.scan.nextInt();
			System.out.print("������: ");
			rate = Main.scan.nextInt();
			System.out.print("���αⰣ: ");
			date = Main.scan.nextInt();
			Discount temp = new Discount(no, rate, date);
			ListCollection.discountList.add(temp);
			FileUtill.writeFile(3, "c:/java/", "discount.txt");
			System.out.println("----> ���ε�� �Ϸ�");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----> ���ε�� ����");
		}

	}

	// ��ϵ� ���� ���
	@Override
	public void deleteDiscount() {
		if (!ListCollection.discountList.isEmpty()) {
			Main.scan = new Scanner(System.in);
			getDiscount();
			System.out.println("---> ������� ");
			System.out.print("����� ��ǰ��ȣ : ");
			int no = 0;

			try {
				no = Main.scan.nextInt();
				for (int i = 0; i < ListCollection.discountList.size(); i++) {
					if (ListCollection.discountList.get(i).getProduct_no() == no) {
						ListCollection.discountList.remove(i);
						FileUtill.writeFile(3, "c:/java/", "discount.txt");
						System.out.println("---> ������� �Ϸ�");
						break;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> ��ǰ��ȣ�� ��Ȯ�ϰ� �Է����ּ���");
			}
		} else {
			System.out.println("���θ���� �����ϴ�");
		}

	}

	@Override
	public void deleteWaste() {
		// TODO Auto-generated method stub
		if (!ListCollection.wasteList.isEmpty()) {
			Main.scan = new Scanner(System.in);
			getWaste();
			System.out.println("----> �����ǰó��");
			System.out.print("ó���� �����ǰ��ȣ : ");
			int no = Main.scan.nextInt();
			if (ListCollection.wasteList.get(no) != null) {
				ListCollection.wasteList.remove(no);
				try {
					FileUtill.writeFile(2, "c:/java/", "waste.txt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("----> ����ǰ ���� ���ε� ����");
				}
				System.out.println("----> ���ó�� �Ϸ�");
			} else {
				System.out.println("----> ��ǰ��ȣ�� �߸� �Է��ϼ̽��ϴ�");
			}
		} else {
			System.out.println("----> ����ǰ�� �����ϴ�");
		}
	}

	@Override
	public void deleteProduct() {
		// TODO Auto-generated method stub
		if (!ListCollection.productList.isEmpty()) {
			Main.scan = new Scanner(System.in);
			getProduct();
			System.out.println("----> ������");
			System.out.print("ó���� ��ǰ��ȣ : ");
			int no = 0;
			try {
				no = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> ���ڸ� �Է��� �� �ֽ��ϴ�");
				return;
			}

			if (ListCollection.productList.get(no) != null) {
				ListCollection.productList.remove(no);
				try {
					FileUtill.writeFile(1, "c:/java/", "product.txt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("----> ���� ������Ʈ ����");
					return;
				}
				System.out.println("----> ������ �Ϸ�");
			} else {
				System.out.println("----> ��ǰ��ȣ�� �߸� �Է��ϼ̽��ϴ�");
			}

		} else {
			System.out.println("----> ��� �����ϴ�");
		}
	}

	// ��ǰ��� ���
	@Override
	public void getProduct() {
		// TODO Auto-generated method stub

		if (!ListCollection.productList.isEmpty()) {
			System.out.println("----> ���Ȯ��");
			System.out.println("============================================================================");
			System.out.println("��ǰ��ȣ\t\t��ǰ��\t\t���\t\t����\t\t�������");
			System.out.println("============================================================================");
			for (int i = 0; i < ListCollection.productList.size(); i++) {
				Product temp = ListCollection.productList.get(i);
				String year = temp.getEndDate().substring(0, 4);
				String month = temp.getEndDate().substring(4, 6);
				String day = temp.getEndDate().substring(6);
				System.out.println("[" + i + "]\t\t" + temp.getProductName() + "\t\t" + temp.getStock() + "\t\t"
						+ temp.getPrice() + "\t\t" + (year + "-" + month + "-" + day));
			}
			System.out.println("============================================================================");
		} else {
			System.out.println("----> ��� �����ϴ�");
		}

	}

	// ����� ���
	@Override
	public void getWaste() {
		// TODO Auto-generated method stub
		addWaste();
		if (!ListCollection.wasteList.isEmpty()) {
			System.out.println("----> ����ǰȮ��");
			System.out.println("============================================================================");
			System.out.println("��ǰ��ȣ\t\t��ǰ��\t\t���\t\t����\t\t�������");
			System.out.println("============================================================================");
			for (int i = 0; i < ListCollection.wasteList.size(); i++) {
				Product temp = ListCollection.wasteList.get(i);
				String year = temp.getEndDate().substring(0, 4);
				String month = temp.getEndDate().substring(4, 6);
				String day = temp.getEndDate().substring(6);
				System.out.println("[" + i + "]\t\t" + temp.getProductName() + "\t\t" + temp.getStock() + "\t\t"
						+ temp.getPrice() + "\t\t" + (year + "-" + month + "-" + day));
			}
			System.out.println("============================================================================");
		} else {
			System.out.println("----> ����ǰ�� �����ϴ�.");
		}
	}

	// ���θ�� ���
	@Override
	public void getDiscount() {
		if (!ListCollection.discountList.isEmpty()) {
			System.out.println("--->���θ��Ȯ��");
			System.out.println("============================================================================");
			System.out.println("��ǰ��ȣ\t\t��ǰ��\t\t������\t\t���θ���Ⱓ");
			System.out.println("============================================================================");

			for (Discount discount : ListCollection.discountList) {
				for (int i = 0; i < ListCollection.productList.size(); i++) {
					if (discount.getProduct_no() == i) {
						String year = discount.getEndDate().substring(0, 4);
						String month = discount.getEndDate().substring(4, 6);
						String day = discount.getEndDate().substring(6);
						System.out.println("[" + i + "]\t\t" + ListCollection.productList.get(i).getProductName()
								+ "\t\t" + discount.getRate() + "%\t\t" + (year + "-" + month + "-" + day));
					}
				}
			}
			System.out.println("============================================================================");
		}

	}

	//����ã��
	public int findDiscount(int no) {

		for (Discount temp : ListCollection.discountList) {
			if (temp.getProduct_no() == no) {
				return temp.getRate();
			}
		}
		return 0;
	}

	//��ǰã��
	public Product findProduct(int no) {
		for (int i = 0; i < ListCollection.productList.size(); i++) {
			if (i == no) {
				return ListCollection.productList.get(i);
			}
		}
		return null;
	}

	// ���� -> List�� �ε�
	public void getFiles() {
		try {
			FileUtill.readFile(1, new File("c:/java/", "product.txt"));
			FileUtill.readFile(2, new File("c:/java/", "waste.txt"));
			FileUtill.readFile(3, new File("c:/java/", "discount.txt"));
			FileUtill.readFile(4, new File("c:/java/", "admin.txt"));
			FileUtill.readFile(5, new File("c:/java/", "sales.txt"));
			System.out.println("----> ���� �ε� �Ϸ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> ���� �ε� ����");
		}
	}

	// ���α׷� ����
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		storeProfit();
		ListCollection.admin.setMoney(this.money);
		try {
			FileUtill.writeFile(4, "c:/java/", "admin.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> ������ ���� ������Ʈ ����");
		}
		System.out.println("----> �ý��� ����");
		System.exit(0);
	}
}
