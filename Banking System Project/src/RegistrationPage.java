package src;

import ui.LoginPageUIClass;
import ui.RegistrationPageUIClass;

import java.awt.event.ActionListener; //must import
import java.awt.event.ActionEvent; //must import

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

import java.io.IOException;
import java.util.Random;

public class RegistrationPage implements ActionListener {

    private static final String path = "./db/";

    public Random random = new Random();
    RegistrationPageUIClass newUserInfo, ob;
    private static String rateOfInterest = "6.00";

    public RegistrationPage(RegistrationPageUIClass ob) {
        this.ob = ob;
    }

    public static void appendToFile(String pathName, Object content) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(pathName, true));
            out.write(content + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e1) {

        if (e1.getSource() == ob.cancelB) {
            ob.setVisible(false);
            new LoginPageUIClass().LoginPageUI();
        }
        if (e1.getSource() == ob.b1) {// continue button

            ob.name = ob.nameTf.getText();
            ob.surname = ob.surnameTf.getText();
            ob.username = ob.usernameTf.getText();
            ob.newpassword = ob.newPasswordTf.getText();
            ob.confirmpassword = ob.confirmPasswordTf.getText();
            ob.contact = ob.contactTf.getText();
            ob.age = ob.agesp.getValue().toString();
            if (ob.m.isSelected()) {
                ob.gender = "Male";
            } else if (ob.f.isSelected()) {
                ob.gender = "Female";
            } else if (ob.oth.isSelected()) {
                ob.gender = "Not to Say";
            } else {
                ob.gender = "Not to Say";
            }
            ob.account = ob.typeOfAccount.getSelectedItem().toString();
            ob.initalAmount = ob.initalAmountTf.getText();

            if (ob.newpassword.isBlank()) {
                ob.message.setText("Password can't be blank");
                ob.flag = false;
            } else if (ob.newpassword.length() < 6) {
                ob.message.setText("Password is too Weak!");
                ob.flag = false;
            } else if (Integer.parseInt(ob.initalAmount) < 1000) {
                ob.message.setText("Initial amount must be >= 1000");
                ob.flag = false;
            } else if (ob.newpassword.contains("#") || ob.newpassword.contains("%") || ob.newpassword.contains("@")
                    || ob.newpassword.contains("&") || ob.newpassword.contains("$") || ob.newpassword.contains("^")
                    || ob.newpassword.contains("!") || ob.newpassword.contains("?")) {
                ob.message.setText("@#!$%^&?_ not Allowed!");
                ob.flag = false;
            } else if (!ob.newpassword.equals(ob.confirmpassword)) {
                ob.message.setText("Password not matching!");
                ob.flag = false;
            } else {
                ob.message.setText("Password matched!");
                ob.flag = true;
                ob.AN = 10000000000L + (long) (random.nextDouble() * 9999999999L); // assigning account number

                newUserInfo = new RegistrationPageUIClass(ob.name, ob.surname, ob.gender, ob.username, ob.newpassword,
                        ob.confirmpassword, ob.age, ob.contact, ob.account, Long.toString(ob.AN), ob.initalAmount);

                if (ob.flag == true) {// Profile successfully completed adding to database

                    String s0 = new String(path.concat(ob.username));
                    File f = new File(s0);
                    f.mkdir();
                    File logFile = new File(s0.concat("/MyLogFile.txt"));
                    try {
                        if (logFile.createNewFile()) {
                            System.out.println("Log file Created");
                        } else {
                            System.out.println("File already Exists");
                        }

                    } catch (IOException e2) {
                        System.out.println("Error From Registration page");
                        e2.printStackTrace();
                    }
                    try {
                        FileWriter out = new FileWriter(s0.concat("/MyDatabase.txt"));

                        out.write(newUserInfo.name + "\n");
                        out.write(newUserInfo.surname + "\n");
                        out.write(newUserInfo.age + "\n");
                        out.write(newUserInfo.gender + "\n");
                        out.write(newUserInfo.contact + "\n");
                        out.write(newUserInfo.username + "\n");
                        out.write(newUserInfo.account + "\n");
                        out.write(newUserInfo.AccountNumber + "\n");
                        out.write(newUserInfo.initalAmount + "\n");
                        if (newUserInfo.account == "Savings Account") {
                            out.write(rateOfInterest);
                        } else {
                            out.write("null");
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String s1 = new String(path + "Store_UP/Username.txt");
                    String s2 = new String(path + "Store_UP/Password.txt");
                    String s3 = new String(path + "Store_UP/AccountNumbers.txt");
                    String s4 = new String(path + "Store_UP/Balance.txt");
                    appendToFile(s1, newUserInfo.username);
                    appendToFile(s2, newUserInfo.confirmpassword);
                    appendToFile(s3, newUserInfo.AccountNumber);
                    appendToFile(s4, newUserInfo.initalAmount);
                    ob.setVisible(false);
                    new LoginPageUIClass().LoginPageUI();
                } // if ends
            } // else ends
        } // if ends
        System.gc();
    }
}
