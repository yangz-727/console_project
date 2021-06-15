package domain;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;

import dto.ListCollection;

public class FileUtill {

	//파일에 입력
	public static void writeFile(String dir,String fileName, int type ) throws Exception {
		
		File filePath = new File(dir);
		// 1. 파일 경로 유무 확인
		if(filePath.exists()) { // 해당 경로 폴더가 없으면
			filePath.mkdir(); // 폴더 생성 메소드
		}
		
		// 2. 파일 객체화
		File file = new File(dir,fileName);
							//경로, 파일명
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
		
		if(type==1) {
			for(Product temp : ListCollection.productList) {
				String outString = temp.
			}
		}
		
		// 3. 파일 쓰기
			//for(int i=0;i<userList.size();i++) {}
		for(User temp : userList) {
			
			// 1. 회원정보를 하나의 문자열에 담기 [필드 구분 , 회원구\n]
			String outString = temp.getId() +","+temp.getPassword()+","+temp.getName()+"\n";
			// 2. 회원 파일쓰기
			byte[] byteArr = outString.getBytes();
			outputStream.write(byteArr);//파일쓰기
		}
		// 4. 스트림 초기화
		outputStream.close();
	}
	
	// 파일을 읽어와서 리스트에 저장하는 메소드
	public static ArrayList<User> readReader(Reader reader) throws Exception {
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		ArrayList<User> userlist = new ArrayList<User>();
		
		String line;
		// 읽어오기
		while((line = bufferedReader.readLine())!=null) {
					//.readLine() : 한줄씩 읽어오기 [\n] 기준
					//.read() : 한 문자씩 읽어오기
			//읽어온 줄 분해
			
			String[] str = line.split(","); //읽어온 라인을 , 기준으로 분해
			//user 객체에 담기
			User temp = new User(str[0], str[1], str[2]);
			
			//객체를 리스트 담기
			userlist.add(temp);
		}
		
		//리스트 반환
		bufferedReader.close(); //버퍼 닫기
		return userlist;
		
	}
	
	//파일 읽어오는 메소드
	public static ArrayList<User> readFile(File file) throws Exception {
		FileReader fileReader = new FileReader(file);
		ArrayList<User> userlist = new ArrayList<>();
		
		userlist = readReader(fileReader);
		fileReader.close();
		return userlist;
		
	}
	
}
