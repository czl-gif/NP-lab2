package service;

import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtil;
import pojo.User;
import dao.UserDAO;

import java.util.List;

public class UserServiceImpl implements UserService{


    @Override
    public boolean addUser(User user) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        int i = userDAO.addUser(user);
        if (i > 0) {
            sqlSession.commit();
        }
        sqlSession.close();
        return i > 0 ? true : false;
    }

    @Override
    public boolean removeUser(User user){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        int i = userDAO.removeUser(user.getUsername());
        if (i > 0) {
            sqlSession.commit();
        }
        sqlSession.close();
        return i > 0 ? true : false;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = null;
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        userList = userDAO.getUserList();

        sqlSession.close();
        return userList;
    }
    @Override
    public int verifyLogin(User user){


        List<User> userList = null;
        String username = user.getUsername();
        String password = user.getPassword();

        userList = getUserList();

        boolean hasUser = false;
        boolean rightpas = false;
        for(User u:userList){
            if(u.getUsername().equals(username)){
                hasUser = true;
                if (u.getPassword().equals(password)){
                    rightpas = true;
                }
            }
        }
        if(! hasUser) return -1;//无该用户
        else if (!rightpas) return 0;//有该用户，但是密码输入错误
        else
            return 1;//有该用户，且密码输入正确
    }
    @Override
    public boolean changePassword(User user){
        boolean isok = false;
        isok = removeUser(user) && addUser(user);
        return isok;
    }

    @Override
    public boolean cancellation(User user) {
        List<User> userList = getUserList();
        boolean isok = false;
        for(User u:userList){
            if(u.getPassword().equals(user.getPassword())){
                isok = true;break;
            }
        }
        if (isok){
            return removeUser(user);
        }
        return false;
    }
}
