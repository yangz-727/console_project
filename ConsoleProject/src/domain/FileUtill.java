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

	// ���Ͽ� �Է�
	public static void writeFile(int type, String dir, String fileName) throws Exception {

		File filePath = new File(dir);
		// 1. ���� ��� ���� Ȯ��
		if (filePath.exists()) { // �ش� ��� ������ ������
			filePath.mkdir(); // ���� ���� �޼ҵ�
		}

		// 2. ���� ��üȭ
		File file = new File(dir, fileName);
		// ���, ���ϸ�
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));

		if (type == 1) {
			// ��ǰ���� ����
			for (Product temp : ListCollection.productList) {
				String outString = temp.getProductName() + "," + temp.getPrice() + "," + temp.getStock() + ","
						+ temp.getEndDate() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		} else if (type == 2) {
			// �����ǰ ���� ����
			for (Product temp : ListCollection.wasteList) {
				String outString = temp.getProductName() + "," + temp.getPrice() + "," + temp.getStock() + ","
						+ temp.getEndDate() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		} else if (type == 3) {
			// �������� ����
			for (Discount temp : ListCollection.discountList) {
				String outString = temp.getProduct_no() + "," + temp.getRate() + "," + temp.getEndDate() + "\n";
				byte[] byteArr = outString.getBytes();
				outputStream.write(byteArr);
			}
		} else if (type == 4) {
			// ���� ���� ���� (�����ڵ�, �����н�����, �����ܾ�)

		} else if (type == 5) {
			// ���� ����
		}

		// 4. ��Ʈ�� �ʱ�ȭ
		outputStream.close();
	}

	// ������ �о�ͼ� ����Ʈ�� �����ϴ� �޼ҵ�
	public static Object readReader(int type, Reader reader) throws Exception {

		BufferedReader bufferedReader = new BufferedReader(reader);
		Object list = null;

		if (type == 1 || type == 2) {
			list = new ArrayList<Product>();
		} else if (type == 3) {
			list = new ArrayList<Discount>();
		} else if (type == 4) {

		} else if (type == 5) {

		}
		String line;
		while ((line = bufferedReader.readLine()) != null) {

			String[] str = line.split(","); // �о�� ������ , �������� ����
			if (type == 1) {
				Product temp = new Product(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3]);
				((ArrayList<Product>) list).add(temp);
			} else if (type == 2) {
				Product temp = new Product(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3]);
				((ArrayList<Product>) list).add(temp);
			} else if (type == 3) {
				Discount temp = new Discount(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
						new SimpleDateFormat("yyyyMMdd").parse(str[2]));
				((ArrayList<Discount>) list).add(temp);
			} else if (type == 4) {

			} else if (type == 5) {

			}
		}

		// ����Ʈ ��ȯ
		bufferedReader.close(); // ���� �ݱ�
		return list;

	}

	// ���� �о���� �޼ҵ�
	public static void readFile(int type, File file) throws Exception {
		FileReader fileReader = new FileReader(file);

		if (type == 1) {
			// ��ǰ���� �а�, ����Ʈ�� ����
			ArrayList<Product> productlist = new ArrayList<>();
			ListCollection.productList = (ArrayList<Product>) readReader(type, fileReader);
		} else if (type == 2) {
			// �����ǰ���� �а�, ����Ʈ�� ����
			ArrayList<Product> wastelist = new ArrayList<>();
			ListCollection.wasteList = (ArrayList<Product>) readReader(type, fileReader);
		} else if (type == 3) {
			// �������� �а�, ����Ʈ�� ����
			ArrayList<Discount> discountList = new ArrayList<>();
			ListCollection.discountList = (ArrayList<Discount>) readReader(type, fileReader);
		} else if (type == 4) {
			// ��������

		} else if (type == 5) {
			// ���� ���� ����
		}
		fileReader.close();
	}

}
