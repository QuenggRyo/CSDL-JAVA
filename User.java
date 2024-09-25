package vnua.fita.bean;

public class User {
	private String email;
	private String fullname;
	
	public User(String email, String fullname) {
		super();
		this.email = email;
		this.fullname = fullname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
