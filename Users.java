package application;

public class Users {
    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Customer extends Users {
    public Customer(String username, String password) {
        super(username, password);
    }
}

class Seller extends Users {
	boolean approved;
    public Seller(String username, String password) {
        super(username, password);
        approved = false;
    }
}

class Technician extends Users {
	boolean approved;
    public Technician(String username, String password) {
        super(username, password);
        approved = false;
    }
}
class Admin extends Users{
	public Admin(String username, String password) {
		super(username, password);
	}
}
