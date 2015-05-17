package mk.ukim.finki.wp.security;

public class UserTransfer {

	private final String username;
	private final String role;

	public UserTransfer(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

}
