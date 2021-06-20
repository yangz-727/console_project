package domain;

import java.util.Calendar;
import java.util.Date;

public class Sales {
	private String date;
	private int money;
	
	public Sales() {
		// TODO Auto-generated constructor stub
	}
	public Sales(int money) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String year = cal.get(Calendar.YEAR)+"";
		String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
		String day = String.format("%02d", cal.get(Calendar.DATE));
		this.date = year+month+day;
		this.money = money;
	}
	public Sales(String date, int money) {
		this.date = date;
		this.money = money;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
