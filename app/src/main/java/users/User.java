package users;

public class User {

    private String userEmail;
    private String userName;
    private String userId;

public User(){

}

    public User(String userEmail, String userName, String userId) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName(String userName) {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
