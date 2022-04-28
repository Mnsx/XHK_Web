package top.mnsx.foreground.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.mnsx.foreground.entity.User;

@Data
public class UserResponse {
    private User user;
    private String vCode;
}
