package domain;

import java.util.Calendar;
import java.util.Date;

public class Discount {
	private int product_no;
	private int rate;
	private Date endDate;

	public Discount() {
		// TODO Auto-generated constructor stub
	}

	public Discount(int product_no, int rate, int date) {
		this.product_no = product_no;
		this.rate = rate;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, date);

		// 할인 기간 입력
		this.endDate = cal.getTime();
	}

	public Discount(int product_no, int rate, Date date) {
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
