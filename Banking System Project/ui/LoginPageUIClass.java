package ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import src.LoginPage;
import javax.swing.ImageIcon;
import java.awt.*;

public class LoginPageUIClass extends JFrame {

    private static final long serialVersionUID = 1L;

    public JButton loginB, newUserB, forgotPasswordB;
    public JTextField tf1;
    public JLabel userL, passwordL, imgL, messageL;// protected
    private ImageIcon im;
    public JPasswordField pf1;
    public boolean flag = true;
    public String usernameGiven, passwordGiven;

    public void LoginPageUI() {
        super.setResizable(false);
        super.setTitle("Login Page");
        super.setBounds(500, 150, 500, 500);

        // image
        im = new ImageIcon("./img/loginIcon.png");
        imgL = new JLabel();
        imgL.setBounds(115, 40, 250, 240);
        imgL.setIcon(im);
        super.add(imgL);

        // username label
        userL = new JLabel(DashboardUIClass.htmlFont("Username"));
        userL.setBounds(70, 305, 100, 30);
        super.add(userL);

        // password label
        passwordL = new JLabel(DashboardUIClass.htmlFont("Password"));
        passwordL.setBounds(70, 340, 100, 30);
        super.add(passwordL);

        messageL = new JLabel("");
        messageL.setBounds(320, 270, 155, 50);
        messageL.setForeground(Color.RED);
        super.add(messageL);

        // username textbox
        tf1 = new JTextField();
        tf1.setBounds(180, 310, 120, 20);
        tf1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        super.add(tf1);

        // password textbox
        pf1 = new JPasswordField();
        pf1.setBounds(180, 345, 120, 20);
        pf1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        super.add(pf1);

        // Login button
        loginB = new JButton(DashboardUIClass.htmlFont("Login "));
        loginB.setForeground(Color.WHITE);
        loginB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        loginB.setBackground(Color.BLUE);
        loginB.setBounds(320, 310, 95, 55);
        super.add(loginB);
        LoginPage obj = new LoginPage(this);
        loginB.addActionListener(obj);

        // new user button
        newUserB = new JButton("Create account");
        newUserB.setBackground(Color.RED);
        newUserB.setForeground(Color.WHITE);
        newUserB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        newUserB.setBounds(297, 410, 120, 30);
        super.add(newUserB);
        newUserB.addActionListener(obj);

        super.setLayout(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}
