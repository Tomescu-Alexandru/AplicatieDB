package gui;

import controller.UserController;
import main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class MyAccountPage extends JFrame {

    UserController userController = new UserController();

    JPanel topPanel = new JPanel(new GridLayout(5,2));
    JPanel panel = new JPanel(new BorderLayout());
    JLabel usernameLabel, emailLabel, changeUsernameLabel, changeEmailLabel;
    JTextField changeUsernameText, changeEmailText;
    JButton changeEmailButton, changeUsernameButton, changePasswordButton;
    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);


    MyAccountPage(){
        initDefaults();
        initUserNameAndEmail();
        initChangeUsername();
        initChangeEmail();
        initChangePassword();
        initPanel();

        setVisible(true);
    }

    private void initPanel(){
        panel.add(new JLabel("Curent user:"), BorderLayout.NORTH);
        panel.add(topPanel,BorderLayout.CENTER);
        add(panel);
    }

    private void initUserNameAndEmail(){
        usernameLabel = new JLabel("Username: " + Main.getCurrentUser().getUserName());
        usernameLabel.setBorder(border);
        emailLabel = new JLabel("Email: " +Main.getCurrentUser().getEmail());
        topPanel.add(usernameLabel);
        topPanel.add(emailLabel);
    }

    private void initChangeUsername()
    {
        changeUsernameLabel = new JLabel("New Username");
        changeUsernameLabel.setBorder(border);
        topPanel.add(changeUsernameLabel);

        changeUsernameText = new JTextField();
        topPanel.add(changeUsernameText);

        changeUsernameButton = new JButton("Change Username");
        changeUsernameButton.setBorder(border);
        topPanel.add(changeUsernameButton);
        topPanel.add(new JLabel());

        changeUsernameButton.addActionListener(e-> {
            if(Validations.validUsername(changeUsernameText.getText()))
            {
                userController.updateUsername(changeUsernameText.getText(), Main.getCurrentUser().getUserName());
                JOptionPane.showMessageDialog(null,"Username Changed");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid Username");
                changeUsernameText.setText("");
                changeUsernameText.requestFocus();
            }
        });
    }

    private void initChangeEmail()
    {
        changeEmailLabel = new JLabel("New Email");
        changeEmailLabel.setBorder(border);
        topPanel.add(changeEmailLabel);

        changeEmailText = new JTextField();
        topPanel.add(changeEmailText);

        changeEmailButton = new JButton("Change Email");
        changeEmailButton.setBorder(border);
        topPanel.add(changeEmailButton);

        changeEmailButton.addActionListener(e->{
            if(Validations.validEmail(changeEmailText.getText()))
            {
                userController.updateEmail(changeEmailText.getText(),Main.getCurrentUser().getEmail());
                    JOptionPane.showMessageDialog(null,"Email Changed");

            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid Email");
                changeEmailText.setText("");
                changeEmailText.requestFocus();
            }
        });


    }

    private void initChangePassword(){
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBorder(border);
        panel.add(changePasswordButton, BorderLayout.SOUTH);

        changePasswordButton.addActionListener(e->{
             new ChangePasswordPage();
             dispose();
        });
    }

    private void initDefaults() {
        setTitle("My Account");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
