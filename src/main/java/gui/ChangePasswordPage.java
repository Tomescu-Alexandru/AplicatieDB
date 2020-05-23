package gui;

import controller.UserController;
import main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChangePasswordPage extends JFrame {

    JLabel passwordLabel,passwordConfirmationLabel;
    JPasswordField passwordText, passwordConfirmationText;
    JButton changePasswordButtons, cancelButton;
    JPanel panel = new JPanel(new GridLayout(3,2));
    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);

    ChangePasswordPage()
    {
        initDefaults();
        initPassword();
        initPasswordConfirmation();
        initChangeButton();
        initCancelButton();
        add(panel);
        setVisible(true);

    }

    private void initPassword()
    {
        passwordLabel = new JLabel("Password");
        passwordLabel.setBorder(border);
        panel.add(passwordLabel);

        passwordText= new JPasswordField();
        panel.add(passwordText);
    }

    private void initPasswordConfirmation()
    {
        passwordConfirmationLabel = new JLabel("Password Confirmation");
        passwordConfirmationLabel.setBorder(border);
        panel.add(passwordConfirmationLabel);

        passwordConfirmationText= new JPasswordField();
        panel.add(passwordConfirmationText);
    }

    private void initChangeButton(){
        changePasswordButtons = new JButton("Change Password");
        changePasswordButtons.setBorder(border);

        panel.add(changePasswordButtons);
        changePasswordButtons.addActionListener(e->{
            if(Validations.validPassword(String.valueOf(passwordText.getPassword()))){
            if(!String.valueOf(passwordText.getPassword()).equals(String.valueOf(passwordConfirmationText.getPassword())))
            {
                JOptionPane.showMessageDialog(null,"Passwords don't match ");
                passwordConfirmationText.setText("");
                passwordConfirmationText.requestFocus();
            }
            else {
                UserController userController = new UserController();
                System.out.println(userController.updatePassword(String.valueOf(passwordText.getPassword()), Main.getCurrentUser().getUserName()));
                JOptionPane.showMessageDialog(null,"Password Changed");
                dispose();
            }
            }
            else {
                JOptionPane.showMessageDialog(null,"Password must have 6 characters and must contain at least 1 uppercase letter, one lowercase letter, and one number ");
                passwordText.setText("");
                passwordText.requestFocus();
            }
        });
    }

    void initCancelButton(){
        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        cancelButton.addActionListener(e->{
            new MyAccountPage();
            dispose();
        });

    }

    private void initDefaults() {
        setTitle("Change Password");
        setSize(300,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
