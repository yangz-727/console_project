package domain;

public class User {

	private String id;
	private String password;
	private String name;
	private String email;
	private int money;
	
	//»ý¼ºÀÚ
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String id, String password, String name, String email, int money) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.money = money;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
	
	
	
}
