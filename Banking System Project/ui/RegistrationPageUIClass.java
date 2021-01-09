package ui;

import src.RegistrationPage;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;

public class RegistrationPageUIClass extends JFrame {
    private static final long serialVersionUID = 1L;

    public String name;
    public String surname;
    public String gender;
    public String username;
    public String newpassword;
    public String confirmpassword;
    public String age;
    public String contact;
    public String account;
    public String AccountNumber;
    public String initalAmount;
    public boolean flag = true;

    public JButton b1, cancelB;
    public JLabel welcomeL, nameL, surnameL, ageL, genderL, maleL, femaleL, otherL, contactL, usernameL, newPasswordL,
            acL, confirmPasswordL, message, initalAmountL;
    public JTextField nameTf, surnameTf, contactTf, usernameTf, newPasswordTf, confirmPasswordTf, initalAmountTf;
    public JRadioButton m, f, oth;
    public JSpinner agesp;
    public SpinnerModel val;
    public JComboBox<String> typeOfAccount;
    public long AN;

    public RegistrationPageUIClass() {
    }

    public RegistrationPageUIClass(String name, String surname, String gender, String username, String newpassword,
            String confirmpassword, String age, String contact, String account, String AccountNumber, String initA) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.username = username;
        this.newpassword = newpassword;
        this.confirmpassword = confirmpassword;
        this.age = age;
        this.contact = contact;
        this.account = account;
        this.AccountNumber = AccountNumber;
        this.initalAmount = initA;
    }

    public static String htmlFont(String s) {
        return ("<html><span style='font-size:12px'>" + s + "</span></html>");
    }

    public void RegistrationPageUI() {

        super.setResizable(false);
        super.setTitle("Registration");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(new Color(0, 51, 102));
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.white, 3));

        welcomeL = new JLabel(htmlFont("Welcome to the Registration Form"));
        welcomeL.setForeground(new Color(220, 204, 255));
        welcomeL.setBounds(100, 10, 300, 30);
        super.add(welcomeL);

        nameL = new JLabel(htmlFont("Name: "));
        nameTf = new JTextField();
        nameL.setBounds(50, 80, 120, 20);
        nameL.setForeground(Color.WHITE);
        nameTf.setBounds(160, 80, 120, 20);
        super.add(nameTf);

        surnameL = new JLabel(htmlFont("Surname: "));
        surnameTf = new JTextField();
        surnameL.setBounds(50, 110, 120, 20);
        surnameL.setForeground(Color.WHITE);
        surnameTf.setBounds(160, 110, 120, 20);
        super.add(surnameTf);

        ageL = new JLabel(htmlFont("Age: "));
        ageL.setBounds(320, 110, 120, 20);
        ageL.setForeground(Color.WHITE);
        val = new SpinnerNumberModel(18, 10, 80, 1);
        agesp = new JSpinner(val);
        agesp.setBounds(355, 110, 60, 25);
        super.add(agesp);

        super.add(ageL);

        genderL = new JLabel(htmlFont("Gender: "));
        genderL.setBounds(50, 150, 120, 30);
        genderL.setForeground(Color.WHITE);
        m = new JRadioButton(htmlFont("Male"));
        f = new JRadioButton(htmlFont("Female"));
        oth = new JRadioButton(htmlFont("Not to say"));
        ButtonGroup bg = new ButtonGroup();

        m.setBounds(160, 150, 70, 30);
        m.setBackground(new Color(0, 51, 102));
        m.setForeground(Color.WHITE);
        f.setBounds(230, 150, 80, 30);
        f.setBackground(new Color(0, 51, 102));
        f.setForeground(Color.WHITE);
        oth.setBounds(310, 150, 120, 30);
        oth.setBackground(new Color(0, 51, 102));
        oth.setForeground(Color.WHITE);
        bg.add(m);
        bg.add(f);
        bg.add(oth);
        super.add(m);
        super.add(f);
        super.add(oth);
        super.add(genderL);
        contactL = new JLabel(htmlFont("Contact: "));
        contactTf = new JTextField();
        contactL.setBounds(50, 200, 120, 20);
        contactL.setForeground(Color.WHITE);
        contactTf.setBounds(160, 200, 120, 20);
        super.add(contactTf);

        usernameL = new JLabel(htmlFont("Username: "));
        usernameTf = new JTextField();
        usernameL.setBounds(50, 230, 120, 20);
        usernameL.setForeground(Color.WHITE);
        usernameTf.setBounds(160, 230, 120, 20);
        super.add(usernameTf);

        newPasswordL = new JLabel(htmlFont("New Password: "));
        newPasswordTf = new JTextField();
        newPasswordL.setBounds(50, 270, 120, 35);
        newPasswordL.setForeground(Color.WHITE);
        newPasswordTf.setBounds(160, 282, 120, 20);
        super.add(newPasswordTf);

        confirmPasswordL = new JLabel(htmlFont("Confirm Password: "));
        confirmPasswordTf = new JTextField();
        confirmPasswordL.setBounds(50, 320, 120, 35);
        confirmPasswordL.setForeground(Color.WHITE);
        confirmPasswordTf.setBounds(160, 332, 120, 20);
        super.add(confirmPasswordTf);

        initalAmountL = new JLabel(htmlFont("Initial Amount: "));
        initalAmountTf = new JTextField();
        initalAmountL.setBounds(320, 290, 120, 35);
        initalAmountL.setForeground(Color.WHITE);
        initalAmountTf.setBounds(320, 332, 120, 20);
        super.add(initalAmountTf);

        acL = new JLabel(htmlFont("Account Type:"));
        acL.setBounds(320, 195, 120, 20);
        acL.setForeground(Color.WHITE);
        super.add(acL);

        String ac[] = { "Current Account", "Salary Account", "Savings Account", "Fix deposit", "Other" };
        typeOfAccount = new JComboBox<String>(ac);
        typeOfAccount.setBounds(320, 235, 120, 20);
        super.add(typeOfAccount);

        b1 = new JButton("Continue");
        b1.setBounds(340, 400, 90, 30);
        b1.setForeground(Color.WHITE);
        b1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        b1.setBackground(new Color(0, 153, 51));
        super.add(b1);

        RegistrationPage ob = new RegistrationPage(this);
        b1.addActionListener(ob);

        cancelB = new JButton("Cancel");
        cancelB.setBounds(220, 400, 90, 30);
        cancelB.setForeground(Color.WHITE);
        cancelB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cancelB.setBackground(new Color(255, 77, 77));
        super.add(cancelB);
        cancelB.addActionListener(ob);

        message = new JLabel(htmlFont(""));
        message.setBounds(35, 400, 180, 35);
        message.setForeground(Color.CYAN);
        super.add(message);

        super.add(nameL);
        super.add(surnameL);
        super.add(contactL);
        super.add(usernameL);
        super.add(newPasswordL);
        super.add(confirmPasswordL);
        super.add(initalAmountL);

        super.setLayout(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
