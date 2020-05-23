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

    public boolean updateUsername(String newUsername, String username){return userDao.updateUserName(newUsername,username);}

    public boolean updateEmail(String newEmail, String email){return  userDao.updateEmail(newEmail,email);}

    public boolean updatePassword(String newPassword, String username){return  userDao.updatePassword(newPassword,username);}

}
