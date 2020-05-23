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

    public static boolean validTime(String time, String type){
        String regex = "^[0-9]{1,2}+:[0-9]{2}$";
        if(time.matches(regex)){
                String[] hoursAndMinutes = time.split(":");
                int hours = Integer.parseInt(hoursAndMinutes[0]);
                int minutes = Integer.parseInt(hoursAndMinutes[1]);

                if(hours >=0 && hours <24 && type.equals("Departure"))
                    return true;

                if(minutes>=0 && minutes<=60)
                    return true;

                return false;
        }

        return false;
    }

    public static boolean validLocation(String location)
    {
        String regex = ".+[a-zA-Z]";
        if(location.length()>=3 && location.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean validPrice(String price){
      try {
          Integer.parseInt(price);
          return true;
      }catch (NumberFormatException e){
          e.printStackTrace();
          return false;
      }
    }



}
