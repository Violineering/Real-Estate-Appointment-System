package AppointmentSystem;

public class Users {
	private String Username;
    private String Password;
    private String Email;	   
    private String PhoneNo;
    
    public Users(String Username, String Password, String Email, String PhoneNo) {	    	
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;	       
        this.PhoneNo = PhoneNo;
    }

    // Getter and Setter methods
    
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username.trim();
    }
    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email.trim();
    }
   	   
    
    public String getPhoneNo() {
        return PhoneNo;
    }
   
    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo.trim();
    }

    public String getFileString() {
        return this.Username + ";"+this.Password+";"+ this.Email+";"+ this.PhoneNo;
    }
}
