package top.mnsx.xhk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * uid 用户编号
 * username 用户名
 * password 密码
 * code 密钥
 * balance 余额
 * phone 手机号
 * email 邮箱
 * gender 性别
 * avatar 头像路径
 * deleted 是否被注销
 */
@Data
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {
    private Long uid;
    private String username;
    private String password;
    private String code;
    private Double balance;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer deleted;
    private String role;

    public User(String username, String password, String code, String phone, String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
        this.username = username;
        this.password = password;
        this.code = code;
        this.phone = phone;
        this.setCreatedUser(createdUser);
        this.setCreatedTime(createdTime);
        this.setModifiedUser(modifiedUser);
        this.setModifiedTime(modifiedTime);
    }
}
