package domain.accoun;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class AccountDTO {

    private long id;
    private String name;
    private String password;
    private String email;

    private String signInCode;
    private long time;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", signInCode='" + signInCode + '\'' +
                ", time=" + time +
                '}';
    }
}
