package top.mnsx.foreground.service;

import top.mnsx.foreground.entity.User;

import java.util.List;

public interface UserService {
    List<User> list();

    User getUserById(Long id);

    User login(User user);

    Boolean register(User user);

    Boolean getUserByName(String username);

    Boolean updateBalance(User user);

    Boolean updateUser(User user);

    Boolean updatePasswordUser(User user);
}
