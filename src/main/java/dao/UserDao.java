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

    public UserDao(Connection connection)
    {
        this.connection=connection;

        try {
            insertQuery = connection.prepareStatement("INSERT INTO users VALUES(null,?,?,?)");
            selectQueryUserName = connection.prepareStatement("SELECT * FROM users where username=?");
            selectQueryEmail = connection.prepareStatement("SELECT * FROM users where email=?");

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
}
