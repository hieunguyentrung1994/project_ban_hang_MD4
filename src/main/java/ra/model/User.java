package ra.model;

public class User {
    private long id;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String address;
    private String avantar;
    private Role idrole;
    private boolean status;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvantar() {
        return avantar;
    }

    public void setAvantar(String avantar) {
        this.avantar = avantar;
    }

    public Role getIdrole() {
        return idrole;
    }

    public void setIdrole(Role idrole) {
        this.idrole = idrole;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User(long id, String userName, String password, String email, String phoneNumber, String fullName, String address, String avantar, Role idrole, boolean status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.address = address;
        this.avantar = avantar;
        this.idrole = idrole;
        this.status = status;
    }
}
