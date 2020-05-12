package gui;

import controller.UserController;
import model.User;

public class Validations {
    static UserController userController = new UserController();

    public static boolean validCredentials(String usernameEmail, String password){
        User user = userController.getUsersByName(usernameEmail);
        if(!user.equals(new User()))
            if (user.getPassword().equals(password))
                return true;

        user = userController.getUsersByEmail(usernameEmail);

        if(!user.equals(new User()))
        {
            if(user.getPassword().equals(password))
                return true;
        }

        return false;

    }

    public static boolean validUsername(String userName) {
        return userController.getUsersByName(userName).equals(new User()) && !userName.equals("");
    }

    public static boolean validPassword(String password){
        String regex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
         return password.matches(regex);
    }

    public static boolean validEmail(String email){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(regex);
    }





}
