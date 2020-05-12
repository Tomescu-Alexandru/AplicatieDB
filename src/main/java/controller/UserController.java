package controller;

import dao.UserDao;
import dbConnection.DBConnection;
import model.User;

import java.sql.Connection;
import java.util.List;

public class UserController {

    private UserDao userDao;

    public UserController(){
        Connection connection = DBConnection.getConnection();
        userDao = new UserDao(connection);
    }

    public boolean addUser(User user)
    {
        return userDao.insert(user);
    }

    public User getUsersByName(String userName){
        return userDao.findByName(userName);
    }

    public User getUsersByEmail(String email){
        return userDao.findByEmail(email);
    }


}
