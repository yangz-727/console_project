package domain;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dto.ListCollection;

public class FileUtill {

	// 파일에 입력
	public static void writeFile(int type, String dir, String fileName) throws Exception {

		File filePath = new File(dir);
		// 1. 파일 경로 유무 확인
		if (filePath.exists()) { // 해당 경로 폴더가 없으면
			filePath.mkdir(); // 폴더 생성 메소드
		}

		// 2. 파일 객체화
		File file = new File(dir, fileName);
		// 경로, 파일명
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));

		if (type == 1) {
			// 제품정보 저장
			for (Product temp : ListCollection.productList) {
				String outString = temp.getProductName() + "," + temp.getPrice() + "," + temp.getStock() + ","
						+ temp.getEndDate() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		} else if (type == 2) {
			// 폐기제품 정보 저장
			for (Product temp : ListCollection.wasteList) {
				String outString = temp.getProductName() + "," + temp.getPrice() + "," + temp.getStock() + ","
						+ temp.getEndDate() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		} else if (type == 3) {
			// 할인정보 저장
			for (Discount temp : ListCollection.discountList) {
				String outString = temp.getProduct_no() + "," + temp.getRate() + "," + temp.getEndDate() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		} else if (type == 4) {
			// 매장 정보 저장
			String outString = ListCollection.admin.getId()+ "," + ListCollection.admin.getPassword() + "," + ListCollection.admin.getMoney() + "\n";
			byte[] byteArr = outString.getBytes();
			outputStream.write(byteArr);
			
		} else if (type == 5) {
			// 매출 정보
			for (Sales temp : ListCollection.salesList) {
				String outString = temp.getDate() + "," + temp.getMoney() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		}

		// 4. 스트림 초기화
		outputStream.close();
	}

	// 파일을 읽어와서 리스트에 저장하는 메소드
	public static Object readReader(int type, Reader reader) throws Exception {

		BufferedReader bufferedReader = new BufferedReader(reader);
		Object list = null;

		if (type == 1 || type == 2) {
			list = new ArrayList<Product>();
		} else if (type == 3) {
			list = new ArrayList<Discount>();
		} else if (type == 4) {
			list = new Admin();
		} else if (type == 5) {
			list = new ArrayList<Sales>();
		}
		String line;
		while ((line = bufferedReader.readLine()) != null) {

			String[] str = line.split(","); // 읽어온 라인을 , 기준으로 분해
			if (type == 1 || type == 2) {
				Product temp = new Product(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3]);
				((ArrayList<Product>) list).add(temp);
			} else if (type == 3) {
				Discount temp = new Discount(Integer.parseInt(str[0]), Integer.parseInt(str[1]),str[2]);
				((ArrayList<Discount>) list).add(temp);
			} else if (type == 4) {
				Admin temp = new Admin(str[0],str[1],Integer.parseInt(str[2]));
				list = temp;
			} else if (type == 5) {
				Sales temp = new Sales(str[0], Integer.parseInt(str[1]));
				((ArrayList<Sales>) list).add(temp);
			}
		}

		// 리스트 반환
		bufferedReader.close(); // 버퍼 닫기
		return list;

	}

	// 파일 읽어오는 메소드
	public static void readFile(int type, File file) throws Exception {
		FileReader fileReader = new FileReader(file);

		if (type == 1) {
			// 제품정보 읽고, 리스트에 저장
			ListCollection.productList = (ArrayList<Product>) readReader(type, fileReader);
		} else if (type == 2) {
			// 폐기제품정보 읽고, 리스트에 저장
			ListCollection.wasteList = (ArrayList<Product>) readReader(type, fileReader);
		} else if (type == 3) {
			// 할인정보 읽고, 리스트에 저장
			ListCollection.discountList = (ArrayList<Discount>) readReader(type, fileReader);
		} else if (type == 4) {
			// 점포정보
			ListCollection.admin = (Admin) readReader(type, fileReader);
		} else if (type == 5) {
			// 점포 매출 정보
			ListCollection.salesList = (ArrayList<Sales>) readReader(type, fileReader);
		}
		fileReader.close();
	}

}
