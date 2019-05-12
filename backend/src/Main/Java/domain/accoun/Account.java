package domain.accoun;

import interceptor.MyInterceptorBinding;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@MyInterceptorBinding
@Entity
@NamedQueries({
        @NamedQuery(name = "login", query = "select u from Account u where u.name = :name  AND u.password = :password")
}
)
public class Account {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique=true, nullable=false, length=128)
    private String name;

    @NotNull
    @Column(nullable=false, length=128)
    private String password;

    @Email(message = "Email should be valid")
    @NotNull
    @Column(nullable=false, length=128)
    private String email;

    @Column(length=128)
    private String signInCode;

    @Column(length=128)
    private long time;

    public Account() {
    }

    public Account(AccountDTO accountDTO) {

        //message hash
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(accountDTO.getPassword().getBytes());
        String encryptedString = new String(messageDigest.digest());

        this.id = accountDTO.getId();
        this.name = accountDTO.getName();
        this.password = encryptedString;
        this.email = accountDTO.getEmail();
    }


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
}
