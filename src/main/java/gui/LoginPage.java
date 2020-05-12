package gui;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class LoginPage extends JFrame {

    private JButton loginButton, cancelButton;
    private JLabel userLabel, passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);

    LoginPage() {

        initDefaults();


        initButtonsText();

        loginButton.addActionListener(event -> loginMethod());

        passwordField.addActionListener(event -> loginMethod());


        initPanel();

        setVisible(true);

    }

    private void loginMethod() {
        if (Validations.validCredentials(usernameField.getText(), new String(passwordField.getPassword()))) {
//
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Credentiale incorecte");
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }

    private void initDefaults() {
        setTitle("Login Page");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initButtonsText() {
        userLabel = new JLabel("UserName/ Email:");
        userLabel.setBorder(border);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBorder(border);


        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
    }

    private void initPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);
        add(panel);
    }
}



