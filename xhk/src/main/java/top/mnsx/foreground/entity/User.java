package top.mnsx.foreground.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private String photoUrl;
    private String username;
    private String password;
    private String phoneNum;
    private Integer balance;
    private String sex;
    private Date birth;
    private String email;

    public User(Long id, String username, String password, String phoneNum, Integer balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.balance = balance;
    }
}
