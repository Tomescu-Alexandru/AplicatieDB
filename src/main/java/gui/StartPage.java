package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartPage extends JFrame {

    private JButton loginPageButton;
    private JButton registerPageButton;

    EmptyBorder border = new EmptyBorder(0,10,0,0);

    public StartPage(){
        initDefaults();

        loginPageButton = new JButton("Login Page");
        registerPageButton = new JButton("Register Page");

        loginPageButton.addActionListener(event -> openLoginPage());
        registerPageButton.addActionListener(event -> openRegisterPage());

        initPanel();
        setVisible(true);

    }

    private void openLoginPage(){
        new LoginPage();
        dispose();
    }

    private void openRegisterPage(){
        new RegisterPage();
        dispose();
    }


    private void initPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(loginPageButton);
        panel.add(registerPageButton);
        add(panel);
    }

    private void initDefaults() {
        setTitle("Start Page");
        setSize(400,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

