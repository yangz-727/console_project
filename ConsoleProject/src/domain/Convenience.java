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

	// 메인 메뉴
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("1. 로그인");
			System.out.println("2. 종료");
			System.out.println("=========================");
			System.out.print("선택 : ");
			int select = 0;
			try {
				select = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 숫자만 입력할 수 있습니다");
			}

			if (select == 1) {
				boolean login = login();
				while (login) {
					Main.scan = new Scanner(System.in);
					System.out.println("=========================");
					System.out.println("0. 로그아웃");
					System.out.println("1. 판매관리");
					System.out.println("2. 재고관리");
					System.out.println("3. 할인관리");
					System.out.println("4. 정산및종료");
					System.out.println("=========================");
					System.out.print("선택 : ");
					int select2 = 4;
					try {
						select2 = Main.scan.nextInt();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("----> 숫자만 입력할 수 있습니다");
					}

					if (select2 == 0) {
						System.out.println("----> 로그아웃");
						login = false;
					} else if (select2 == 1) {
						System.out.println("----> 판매관리");
						sellMenu();
					} else if (select2 == 2) {
						System.out.println("----> 재고관리");
						productMenu();
					} else if (select2 == 3) {
						System.out.println("----> 할인관리");
						discountMenu();
					}else if(select2 == 4) {
						exit();
					}else {
						System.out.println("----> 다시 입력해주세요");
					}

				}
			} else if (select == 2) {
				exit();
				break;
			} else {
				System.out.println("----> 다시 입력해주세요");
			}

		}

	}

	// 판매메뉴
	public void sellMenu() {
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("0. 돌아가기");
			System.out.println("1. 제품결제");
			System.out.println("2. 매출확인");
			System.out.println("3. 매출그래프");
			System.out.println("4. 정산및종료");
			System.out.println("=========================");
			System.out.print("선택 : ");
			int select = 8;
			try {
				select = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 숫자만 입력할 수 있습니다");
			}
			if (select == 1) {
				System.out.println("----> 제품판매");
				pay();
			} else if (select == 2) {
				System.out.println("----> 매출확인");
				getProfit();
			} else if (select == 3) {
				System.out.println("----> 매출그래프");
				getGraph();
			} else if (select == 4) {
				exit();
			} else if (select == 0) {
				break;
			} else {
				System.out.println("----> 다시 입력해주세요");
			}
		}

	}

	// 결제
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

					System.out.print("제품번호 : ");
					int no = Main.scan.nextInt();
					System.out.print("구매개수 : ");
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
								receipt += " "+temp.getProductName() + "\t\t" + temp.getPrice() + "원\t\t" + count + "개\t\t"
										+ price + "원\t\t" + fd + "% 할인적용\n";
							} else {
								price = temp.getPrice() * count;
								result += price;
								receipt += " "+temp.getProductName() + "\t\t" + temp.getPrice() + "원\t\t" + count + "개\t\t"
										+ price + "원\n";
							}
							ListCollection.productList.get(no).setStock(temp.getStock()-count);
							FileUtill.writeFile(1, "c:/java/", "product.txt");
						} else {
							System.out.println("----> 재고가 부족합니다");
							continue;
						}
					} else {
						System.out.println("----> 제품이 없습니다");
						continue;
					}

					System.out.println("=========================");
					System.out.println("0. 결제하기");
					System.out.println("1. 결제상품추가");
					System.out.println("=========================");
					System.out.print("선택 : ");
					sw = Main.scan.nextInt();

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("----> 숫자만 입력할 수 있습니다 / 파일 업데이트 실패");
				}
			} else {
				this.money+=result;
				receipt += "합계 : "+result+"원";
				System.out.println("----> 결제완료");
				System.out.println("==================================== 영수증 ====================================");
				System.out.println(" 제품명\t\t제품가격\t\t구매개수\t\t총금액\t\t할인");
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
			System.out.print("[ "+year+"년 "+month+"월 "+day+"일 ]");
			int count =percent/5;
			for(int i=0;i<count;i++) {
				System.out.print("■");
			}
			System.out.println();
		}
	}
	
	public void storeProfit() {
		Sales temp = new Sales(this.money-ListCollection.admin.getMoney());
		ListCollection.salesList.add(temp);
		try {
			getProfit();
			System.out.println("[ 포스기 잔액 ] "+this.money);
			FileUtill.writeFile(5, "c:/java/", "sales.txt");
			System.out.println("----> 매출정보 저장");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> 매출정보 실패");
		}
	}

	// 매출확인(현재잔액(this.money) - 기존잔액(admin))
	public void getProfit() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		String date = sdf.format(cal.getTime());
		System.out.println("[ " + date + " : 매출 ] " + (this.money-ListCollection.admin.getMoney()) + "원");
	}

	// 재고관리메뉴
	public void productMenu() {
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("0. 돌아가기");
			System.out.println("1. 재고등록");
			System.out.println("2. 재고확인");
			System.out.println("3. 재고삭제");
			System.out.println("4. 폐기확인");
			System.out.println("5. 폐기처리");
			System.out.println("6. 정산및종료");
			System.out.println("=========================");
			System.out.print("선택 : ");
			int select = 7;
			try {
				select = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 숫자만 입력할 수 있습니다");
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
				System.out.println("----> 다시 입력해주세요");
			}
		}
	}

	// 할인관리메뉴
	public void discountMenu() {
		while (true) {
			Main.scan = new Scanner(System.in);
			System.out.println("=========================");
			System.out.println("0. 돌아가기");
			System.out.println("1. 할인등록");
			System.out.println("2. 할인목록");
			System.out.println("3. 할인취소");
			System.out.println("4. 정산및종료");
			System.out.println("=========================");
			System.out.print("선택 : ");
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
					System.out.println("----> 다시 입력해주세요");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 숫자만 입력할 수 있습니다");
			}
		}
	}

	// 로그인
	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		System.out.println("----> 로그인");
		System.out.print("아이디 : ");
		String id = Main.scan.next();
		System.out.print("패스워드 : ");
		String password = Main.scan.next();

		Admin temp = ListCollection.admin;

		if (temp.getId().equals(id) && temp.getPassword().equals(password)) {
			this.money = temp.getMoney();
			System.out.println("----> 로그인 성공");
			return true;
		}
		System.out.println("----> 로그인 실패");
		return false;
	}

	// 재고등록
	@Override
	public void addProduct() {
		// TODO Auto-generated method stub
		Main.scan = new Scanner(System.in);
		System.out.println("----> 재고등록");
		System.out.print("제품명 : ");
		String name = Main.scan.next();
		int price = 0;
		int stock = 0;
		try {
			System.out.print("가격 : ");
			price = Main.scan.nextInt();
			System.out.print("재고 : ");
			stock = Main.scan.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----> 숫자만 입력할 수 있습니다");
			return;
		}

		System.out.print("유통기한(YYYYMMDD) : ");
		String date = Main.scan.next();
		Date today = new Date();
		try {
			if (today.before(new SimpleDateFormat("yyyyMMdd").parse(date))) {
				Product temp = new Product(name, price, stock, date);
				ListCollection.productList.add(temp);
				FileUtill.writeFile(1, "c:/java/", "product.txt");
				System.out.println("----> 제품등록 완료");
			} else {
				System.out.println("----> 유통기한이 지난 상품은 등록할 수 없습니다");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> 제품등록 실패");
			return;
		}

	}

	// 폐기등록
	@Override
	public void addWaste() {
		Date today = new Date();
		for (int i = 0; i < ListCollection.productList.size(); i++) {
			Product temp = ListCollection.productList.get(i);
			try {
				// 날짜가 지난 상품 폐기상품목록에 추가 후 재고목록에서 삭제
				if (today.after(new SimpleDateFormat("yyyyMMdd").parse(temp.getEndDate()))) {
					ListCollection.wasteList.add(temp);
					ListCollection.productList.remove(i);
					FileUtill.writeFile(2, "c:/java/", "waste.txt");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("----> 폐기상품 등록 실패");
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("----> 폐기상품 파일 업데이트 실패");
			}
		}
	}

	// 할인등록
	@Override
	public void addDiscount() {
		Main.scan = new Scanner(System.in);
		getProduct();
		System.out.println("----> 할인등록");
		int no = 0;
		int rate = 0;
		int date = 0;

		try {
			System.out.print("제품번호: ");
			no = Main.scan.nextInt();
			System.out.print("할인율: ");
			rate = Main.scan.nextInt();
			System.out.print("할인기간: ");
			date = Main.scan.nextInt();
			Discount temp = new Discount(no, rate, date);
			ListCollection.discountList.add(temp);
			FileUtill.writeFile(3, "c:/java/", "discount.txt");
			System.out.println("----> 할인등록 완료");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----> 할인등록 실패");
		}

	}

	// 등록된 할인 취소
	@Override
	public void deleteDiscount() {
		if (!ListCollection.discountList.isEmpty()) {
			Main.scan = new Scanner(System.in);
			getDiscount();
			System.out.println("---> 할인취소 ");
			System.out.print("취소할 제품번호 : ");
			int no = 0;

			try {
				no = Main.scan.nextInt();
				for (int i = 0; i < ListCollection.discountList.size(); i++) {
					if (ListCollection.discountList.get(i).getProduct_no() == no) {
						ListCollection.discountList.remove(i);
						FileUtill.writeFile(3, "c:/java/", "discount.txt");
						System.out.println("---> 할인취소 완료");
						break;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 제품번호를 정확하게 입력해주세요");
			}
		} else {
			System.out.println("할인목록이 없습니다");
		}

	}

	@Override
	public void deleteWaste() {
		// TODO Auto-generated method stub
		if (!ListCollection.wasteList.isEmpty()) {
			Main.scan = new Scanner(System.in);
			getWaste();
			System.out.println("----> 페기제품처분");
			System.out.print("처분할 폐기제품번호 : ");
			int no = Main.scan.nextInt();
			if (ListCollection.wasteList.get(no) != null) {
				ListCollection.wasteList.remove(no);
				try {
					FileUtill.writeFile(2, "c:/java/", "waste.txt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("----> 폐기상품 파일 업로드 실패");
				}
				System.out.println("----> 폐기처분 완료");
			} else {
				System.out.println("----> 제품번호를 잘못 입력하셨습니다");
			}
		} else {
			System.out.println("----> 폐기상품이 없습니다");
		}
	}

	@Override
	public void deleteProduct() {
		// TODO Auto-generated method stub
		if (!ListCollection.productList.isEmpty()) {
			Main.scan = new Scanner(System.in);
			getProduct();
			System.out.println("----> 재고삭제");
			System.out.print("처분할 제품번호 : ");
			int no = 0;
			try {
				no = Main.scan.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("----> 숫자만 입력할 수 있습니다");
				return;
			}

			if (ListCollection.productList.get(no) != null) {
				ListCollection.productList.remove(no);
				try {
					FileUtill.writeFile(1, "c:/java/", "product.txt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("----> 파일 업데이트 실패");
					return;
				}
				System.out.println("----> 재고삭제 완료");
			} else {
				System.out.println("----> 제품번호를 잘못 입력하셨습니다");
			}

		} else {
			System.out.println("----> 재고가 없습니다");
		}
	}

	// 제품목록 출력
	@Override
	public void getProduct() {
		// TODO Auto-generated method stub

		if (!ListCollection.productList.isEmpty()) {
			System.out.println("----> 재고확인");
			System.out.println("============================================================================");
			System.out.println("제품번호\t\t제품명\t\t재고\t\t가격\t\t유통기한");
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
			System.out.println("----> 재고가 없습니다");
		}

	}

	// 폐기목록 출력
	@Override
	public void getWaste() {
		// TODO Auto-generated method stub
		addWaste();
		if (!ListCollection.wasteList.isEmpty()) {
			System.out.println("----> 폐기상품확인");
			System.out.println("============================================================================");
			System.out.println("제품번호\t\t제품명\t\t재고\t\t가격\t\t유통기한");
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
			System.out.println("----> 폐기상품이 없습니다.");
		}
	}

	// 할인목록 출력
	@Override
	public void getDiscount() {
		if (!ListCollection.discountList.isEmpty()) {
			System.out.println("--->할인목록확인");
			System.out.println("============================================================================");
			System.out.println("제품번호\t\t제품명\t\t할인율\t\t할인만료기간");
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

	//할인찾기
	public int findDiscount(int no) {

		for (Discount temp : ListCollection.discountList) {
			if (temp.getProduct_no() == no) {
				return temp.getRate();
			}
		}
		return 0;
	}

	//제품찾기
	public Product findProduct(int no) {
		for (int i = 0; i < ListCollection.productList.size(); i++) {
			if (i == no) {
				return ListCollection.productList.get(i);
			}
		}
		return null;
	}

	// 파일 -> List로 로딩
	public void getFiles() {
		try {
			FileUtill.readFile(1, new File("c:/java/", "product.txt"));
			FileUtill.readFile(2, new File("c:/java/", "waste.txt"));
			FileUtill.readFile(3, new File("c:/java/", "discount.txt"));
			FileUtill.readFile(4, new File("c:/java/", "admin.txt"));
			FileUtill.readFile(5, new File("c:/java/", "sales.txt"));
			System.out.println("----> 파일 로딩 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> 파일 로딩 실패");
		}
	}

	// 프로그램 종료
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		storeProfit();
		ListCollection.admin.setMoney(this.money);
		try {
			FileUtill.writeFile(4, "c:/java/", "admin.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----> 관리자 정보 업데이트 실패");
		}
		System.out.println("----> 시스템 종료");
		System.exit(0);
	}
}
