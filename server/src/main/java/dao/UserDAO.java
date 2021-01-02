package dao;

import pojo.User;

import java.util.List;

public interface UserDAO {
    public int addUser(User user);
    public int removeUser(String username);
    public List<User> getUserList();
}
