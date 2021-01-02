package service;

import pojo.User;

import java.util.List;

public interface UserService {
    public boolean addUser(User user);
    public List<User> getUserList();
    public int verifyLogin(User user);
}
