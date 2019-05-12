package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class UserLogin {
    private String username;
    private String password;
    private String email;
    private String signInCode;

    public UserLogin() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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

    public String getSignInCode() {
        return signInCode;
    }
    public void setSignInCode(String signInCode) {
        this.signInCode = signInCode;
    }
}
