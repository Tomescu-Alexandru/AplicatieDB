package main;

import gui.StartPage;
import lombok.Getter;
import lombok.Setter;
import model.User;

public class Main {

    public static User currentUser;
    public static void main(String[] args) {
        StartPage startPage = new StartPage();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }
}
