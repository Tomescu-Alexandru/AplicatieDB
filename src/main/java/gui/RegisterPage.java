package gui;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterPage extends JFrame{

    JPanel panel;

    JLabel userNameLabel, passwordLabel, emailLabel,passwordConfirmationLabel;
    JButton registerButton, resetButton;
    JTextField userNameText,emailText ;
    JPasswordField  passwordText, passwordConfirmationText;

    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);


    public RegisterPage(){
        initDefaults();

        panel = new JPanel(new GridLayout(5,2));
        initUserName();
        initEmail();
        initPassword();
        initPasswordConfirmation();
        initRegisterButton();
        initResetButton();

        add(panel);
        setVisible(true);

    }


    private void initUserName()
    {
        userNameLabel = new JLabel("Username");
        userNameLabel.setBorder(border);
        panel.add(userNameLabel);

        userNameText= new JTextField();
        panel.add(userNameText);
    }

    private void initPassword()
    {
        passwordLabel = new JLabel("Password");
        passwordLabel.setBorder(border);
        panel.add(passwordLabel);

        passwordText= new JPasswordField();
        panel.add(passwordText);
    }

    private void initEmail(){
        emailLabel = new JLabel("Email");
        emailLabel.setBorder(border);
        panel.add(emailLabel);

        emailText= new JTextField();
        panel.add(emailText);
    }

    private void initPasswordConfirmation()
    {
        passwordConfirmationLabel = new JLabel("Password Confirmation");
        passwordConfirmationLabel.setBorder(border);
        panel.add(passwordConfirmationLabel);

        passwordConfirmationText= new JPasswordField();
        panel.add(passwordConfirmationText);
    }

    private void initRegisterButton(){

        registerButton = new JButton("Register");

        registerButton.addActionListener(event ->{
            if(validateForm())
            {
                User user = new User(userNameText.getText(), emailText.getText(),String.valueOf(passwordText.getPassword()));
                UserController userController = new UserController();

                if(userController.addUser(user)){
                    new LoginPage();
                    dispose();;
                }

            }
        });
        panel.add(registerButton);

    }


    private void initResetButton() {
        resetButton = new JButton("Reset");

        resetButton.addActionListener(event ->{
            userNameText.setText("");
            passwordText.setText("");
            emailText.setText("");
            passwordConfirmationText.setText("");
        });

        panel.add(resetButton);
    }

    private void initDefaults() {
        setTitle("Register");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean validateForm(){
        if(!Validations.validUsername(userNameText.getText()))
        {
            JOptionPane.showMessageDialog(null,"Invalid UserName");
            userNameText.setText("");
            userNameText.requestFocus();
            return false;
        }
        else if(!Validations.validEmail(emailText.getText()))
        {
            JOptionPane.showMessageDialog(null,"Invalid Email, example: a@y.c");
            emailText.setText("");
            emailText.requestFocus();
            return false;
        }
        else if(!Validations.validPassword(String.valueOf(passwordText.getPassword())))
        {
            JOptionPane.showMessageDialog(null,"Password must have 6 characters and must contain at least 1 uppercase letter, one lowercase letter, and one number ");
            passwordText.setText("");
            passwordText.requestFocus();
            return false;
        }
        else if(!String.valueOf(passwordText.getPassword()).equals(String.valueOf(passwordConfirmationText.getPassword())))
        {
            JOptionPane.showMessageDialog(null,"Passwords don't match ");
            passwordConfirmationText.setText("");
            passwordConfirmationText.requestFocus();
            return false;
        }

        return true;
    }
}

