package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import ui.DashboardUIClass;
import ui.LoginPageUIClass;
import ui.RegistrationPageUIClass;

public class LoginPage implements ActionListener {
    LoginPageUIClass ob;
    public static ArrayList<String> listOfUsername = new ArrayList<String>();
    public static ArrayList<String> listOfPassword = new ArrayList<String>();

    public LoginPage(LoginPageUIClass ob) {
        this.ob = ob;
    }

    public void LoadUPToArrayLists() {
        String st;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./db/Store_UP/Password.txt")));
            BufferedReader br1 = new BufferedReader(new FileReader(new File("./db/Store_UP/Username.txt")));
            while ((st = br.readLine()) != null) {
                if (!listOfPassword.contains(st)) {
                    listOfPassword.add(st);
                }
            }
            br.close();
            while ((st = br1.readLine()) != null) {
                if (!listOfUsername.contains(st)) {
                    listOfUsername.add(st);
                }
            }
            br1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LoadUPToArrayLists();
        if (e.getSource() == ob.newUserB) {
            ob.setVisible(false);
            new RegistrationPageUIClass().RegistrationPageUI(); // call to main registration

        } else if (e.getSource() == ob.loginB) {
            ob.usernameGiven = ob.tf1.getText();
            ob.passwordGiven = new String(ob.pf1.getPassword());// IMP

            if (ob.usernameGiven.isEmpty() && ob.passwordGiven.isEmpty()) {
                ob.messageL.setText("Please enter Credentials!");
                ob.flag = false;
            } else if (!listOfUsername.contains(ob.usernameGiven) || !listOfPassword.contains(ob.passwordGiven)) {
                ob.messageL.setText("Incorrect Credentials!");
                ob.flag = false;
            } else {
                ob.flag = true;
                if (listOfUsername.contains(ob.usernameGiven) && listOfPassword.contains(ob.passwordGiven)
                        && ob.flag == true) {
                    ob.messageL.setText("login Successful!");
                    ob.setVisible(false);
                    new DashboardUIClass().DashBoardUI(ob.usernameGiven);
                }
            }
        }
    }

}
