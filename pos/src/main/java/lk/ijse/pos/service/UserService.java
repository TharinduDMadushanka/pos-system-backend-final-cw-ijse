package lk.ijse.pos.service;

import lk.ijse.pos.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUser(String email);

    List<User> getAllUsers();
}
