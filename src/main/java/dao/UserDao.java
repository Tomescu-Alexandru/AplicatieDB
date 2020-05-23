package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDao {

    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement selectQueryUserName;
    PreparedStatement selectQueryEmail;
    PreparedStatement updateUserNameQuery;
    PreparedStatement updateEmailQuery;
    PreparedStatement updatePasswordQuery;

    public UserDao(Connection connection) {
        this.connection=connection;

        try {
            insertQuery = connection.prepareStatement("INSERT INTO users VALUES(null,?,?,?)");
            selectQueryUserName = connection.prepareStatement("SELECT * FROM users where username=?");
            selectQueryEmail = connection.prepareStatement("SELECT * FROM users where email=?");
            updateUserNameQuery = connection.prepareStatement("UPDATE users SET username=? WHERE username=?");
            updateEmailQuery = connection.prepareStatement("UPDATE users SET email=? WHERE email=?");
            updatePasswordQuery = connection.prepareStatement("UPDATE users SET password=? WHERE username=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByName(String userName){
        try {
            selectQueryUserName.setString(1,userName);
            ResultSet result = selectQueryUserName.executeQuery();

            while(result.next()){
                User user = new User(
                        result.getString("userName"),
                        result.getString("email"),
                        result.getString("password")
                );
                return user;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  new User();
    }

    public User findByEmail(String email){
        try {
            selectQueryEmail.setString(1,email);
            ResultSet result = selectQueryEmail.executeQuery();

            while(result.next()){
                User user = new User(
                        result.getString("userName"),
                        result.getString("email"),
                        result.getString("password")
                );

                return user;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User();
    }

    public boolean insert(User user){

        try {
            insertQuery.setString(1,user.getUserName());
            insertQuery.setString(2,user.getEmail());
            insertQuery.setString(3, user.getPassword());

            return insertQuery.executeUpdate()!=0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateUserName(String newUsername, String username) {
        try {
            updateUserNameQuery.setString(1,newUsername);
            updateUserNameQuery.setString(2,username);

            return updateUserNameQuery.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateEmail(String newEmail, String email){
        try {
            updateEmailQuery.setString(1,newEmail);
            updateEmailQuery.setString(2,email);

            return updateEmailQuery.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePassword(String newPassword, String username){
        try {
            updatePasswordQuery.setString(1,newPassword);
            updatePasswordQuery.setString(2,username);

            return updatePasswordQuery.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
