package domain;

import java.util.Calendar;
import java.util.Date;

public class Discount {
	private int product_no;
	private int rate;
	private String endDate;

	public Discount() {
		// TODO Auto-generated constructor stub
	}

	public Discount(int product_no, int rate, int date) {
		this.product_no = product_no;
		this.rate = rate;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, date);
		String year = cal.get(Calendar.YEAR)+"";
		String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
		String day = String.format("%02d", cal.get(Calendar.DATE));
		// 할인 기간 입력
		this.endDate = year+month+day;
	}
	
	public Discount(int product_no, int  rate, String date) {
		this.product_no = product_no;
		this.rate = rate;
		this.endDate = date;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
