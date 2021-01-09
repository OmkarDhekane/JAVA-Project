package ui;

import javax.swing.*;
import src.DashBoard;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositeUIClass extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private String Holdername, AccountNum, AmountGiven;
    protected JLabel welcomeL, AccountNumberL, HolderNameL, DateL, DepositeAmountL, messageL;
    protected JButton DepositeB, continueB, cancelB;
    protected JTextField AccountNumberTf, HolderNameTf, DepositeAmountTf;
    public static JLabel clockL;

    private String unm, nm;
    private Boolean flag = true;

    private static void clock() {

        Thread clockT = new Thread() {
            public void run() {
                try {
                    for (;;) {
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int mouth = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);

                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR);
                        clockL.setText("Time: " + hour + ":" + minute + ":" + second + "  Date: " + day + "/"
                                + (mouth + 1) + "/" + year);
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        clockT.start();
    }

    public void DepositeUI(String unm) {

        // super.setVisible(false);
        super.setResizable(false);
        super.setTitle("DashBoard/Deposite Page");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(Color.BLACK);
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        this.unm = unm;
        // date = new Date();

        clockL = new JLabel(RegistrationPageUIClass.htmlFont(""));
        clockL.setForeground(new Color(220, 204, 255));
        clockL.setBounds(30, 60, 300, 30);
        super.add(clockL);
        clock();

        welcomeL = new JLabel(RegistrationPageUIClass.htmlFont("Deposite your money on ONE Click !!"));
        welcomeL.setForeground(Color.ORANGE);
        welcomeL.setBounds(30, 20, 390, 40);
        super.add(welcomeL);

        messageL = new JLabel(RegistrationPageUIClass.htmlFont(""));
        messageL.setForeground(Color.CYAN);
        messageL.setBounds(110, 310, 200, 20);
        super.add(messageL);

        AccountNumberL = new JLabel(RegistrationPageUIClass.htmlFont("Account Number: "));
        AccountNumberTf = new JTextField();
        AccountNumberL.setBounds(50, 150, 180, 20);
        AccountNumberL.setForeground(Color.WHITE);
        AccountNumberTf.setBounds(270, 150, 150, 20);
        super.add(AccountNumberTf);
        super.add(AccountNumberL);

        HolderNameL = new JLabel(RegistrationPageUIClass.htmlFont("A/C HolderName(as registered):"));
        HolderNameTf = new JTextField();
        HolderNameL.setBounds(50, 200, 200, 35);
        HolderNameL.setForeground(Color.WHITE);
        HolderNameTf.setBounds(270, 200, 150, 20);
        super.add(HolderNameTf);
        super.add(HolderNameL);

        DepositeAmountL = new JLabel(RegistrationPageUIClass.htmlFont("Amount to be Deposited(INR):"));
        DepositeAmountTf = new JTextField();
        DepositeAmountL.setBounds(50, 250, 250, 20);
        DepositeAmountL.setForeground(Color.WHITE);
        DepositeAmountTf.setBounds(270, 250, 150, 20);
        super.add(DepositeAmountTf);
        super.add(DepositeAmountL);

        continueB = new JButton("Deposite");
        continueB.setBounds(340, 400, 90, 30);
        continueB.setForeground(Color.WHITE);
        continueB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        continueB.setBackground(new Color(0, 153, 51));
        super.add(continueB);
        continueB.addActionListener(this);

        cancelB = new JButton("Cancel");
        cancelB.setBounds(220, 400, 90, 30);
        cancelB.setForeground(Color.WHITE);
        cancelB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cancelB.setBackground(new Color(255, 77, 77));
        super.add(cancelB);
        cancelB.addActionListener(this);

        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        super.setLayout(null);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Holdername = HolderNameTf.getText();
        AccountNum = AccountNumberTf.getText();
        AmountGiven = DepositeAmountTf.getText();
        if (e.getSource() == cancelB) {
            this.setVisible(false);
            new DashboardUIClass().DashBoardUI(unm);

        } else if (e.getSource() == continueB) {
            try {
                DashBoard.LoadUserInfoByAC(AccountNum, DashBoard.info); // load the info of user with account numner
            } catch (MyException e2) {

                e2.printStackTrace();
            }
            nm = DashBoard.info.get("name");
            int AmountFromMap = Integer.parseInt(DashBoard.info.get("balance").toString());

            if (HolderNameTf.getText().isBlank()) {
                messageL.setText("Holder's name can't be blank");
                flag = false;
            } else if (AccountNumberTf.getText().isBlank()) {
                messageL.setText("Enter account number!");
                flag = false;
            } else {
                flag = true;
                if (flag == true) {

                    if ((DashBoard.info.get("accountNumber").equals(AccountNum)) && ((nm.equals(Holdername))
                            || ((nm + " " + DashBoard.info.get("surname")).equals(Holdername)))) {

                        DashBoard.modifyAndWriteAmount(DashBoard.info.get("username"),
                                (AmountFromMap + Integer.parseInt(AmountGiven)), DashBoard.info);

                        if (DashBoard.info.get("username").toString().equals(unm))// depositing money to myself
                        {

                            DashBoard.LogFileWriter((clockL.getText()), DashBoard.info.get("username"), "Deposited",
                                    "+" + AmountGiven, AccountNum, "Omkar's Bank", "Successful");// messaging
                                                                                                 // to me
                                                                                                 // only
                        } else if (!DashBoard.info.get("username").toString().equals(unm)) { // depositin money on
                                                                                             // another
                            // account

                            DashBoard.LogFileWriter((clockL.getText()), DashBoard.info.get("username"), "Deposited",
                                    "+" + AmountGiven, "from" + AccountNum, "Omkar's Bank", "Successful");// messaging
                            // to me
                            // only
                            DashBoard.LogFileWriter((clockL.getText()), unm, "Deposited", "+" + AmountGiven,
                                    "from" + AccountNum, "Omkar's Bank", "Successful");
                            // messaging to other account
                        }
                        messageL.setText("Database updated!");
                        DashBoard.LoadUserInfo(unm, DashBoard.info);// reloading current users info

                        this.setVisible(false);
                        DashboardUIClass n = new DashboardUIClass();
                        n.DashBoardUI(unm);
                        DashboardUIClass.indicatorL.setText("Last Activity: Amount Deposited!");

                    } else {
                        messageL.setText("Incorrect inputs!");
                    }
                } else {

                    DashBoard.LogFileWriter((clockL.getText()).toString(), unm, "Deposite", "0",
                            DashBoard.info.get("accountNumber"), "Omkar's Bank", "Failed");
                    DashBoard.LoadUserInfo(unm, DashBoard.info);// reloading current users info in DashBOard.info
                    messageL.setText("Something Went Wrong!");
                }
            }
        }
    }
}
