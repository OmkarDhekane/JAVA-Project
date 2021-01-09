package ui;

import javax.swing.*;

import src.DashBoard;
import java.awt.event.ActionListener;
import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.Color;

public class WithdrawalUIClass extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private String Holdername, AccountNum, AmountGiven, nm;
    private JLabel welcomeL, AccountNumberL, HolderNameL, DateL, WithdrawAmountL, messageL;
    private JButton continueB, cancelB;
    private JTextField AccountNumberTf, HolderNameTf, WithdrawAmountTf;
    private String unm;
    private Boolean flag = true;
    private int AmountFromMap;
    private Date date;

    public void WithdrawalUI(String unm) {

        // super.setVisible(false);
        super.setResizable(false);
        super.setTitle("DashBoard/Withdrawal Page");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(Color.BLACK);
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        this.unm = unm;
        date = new Date();
        DateL = new JLabel(RegistrationPageUIClass.htmlFont(date.toString()));
        DateL.setForeground(new Color(220, 204, 255));
        DateL.setBounds(30, 60, 300, 30);
        super.add(DateL);

        welcomeL = new JLabel(RegistrationPageUIClass.htmlFont("Withdraw on just ONE Click !!"));
        welcomeL.setForeground(Color.ORANGE);
        welcomeL.setBounds(30, 20, 390, 40);
        super.add(welcomeL);

        messageL = new JLabel(RegistrationPageUIClass.htmlFont(""));
        messageL.setForeground(Color.CYAN);
        messageL.setBounds(110, 310, 300, 20);
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

        WithdrawAmountL = new JLabel(RegistrationPageUIClass.htmlFont("Amount to be Withdrawn(INR):"));
        WithdrawAmountTf = new JTextField();
        WithdrawAmountL.setBounds(50, 250, 220, 40);
        WithdrawAmountL.setForeground(Color.WHITE);
        WithdrawAmountTf.setBounds(270, 250, 150, 20);
        super.add(WithdrawAmountTf);
        super.add(WithdrawAmountL);

        continueB = new JButton("Withdrawal");
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
        AmountGiven = WithdrawAmountTf.getText();

        if (e.getSource() == cancelB) {
            this.setVisible(false);
            new DashboardUIClass().DashBoardUI(unm);
        } else if (e.getSource() == continueB) {
            try {
                DashBoard.LoadUserInfoByAC(unm, DashBoard.info); // load the info of user with account number
            } catch (MyException e2) {

                e2.printStackTrace();
            }
            nm = DashBoard.info.get("name");
            AmountFromMap = Integer.parseInt(DashBoard.info.get("balance").toString());

            if (HolderNameTf.getText().isBlank()) {
                messageL.setText("Holder's name can't be blank");
                flag = false;
            } else if (AccountNumberTf.getText().isBlank()) {
                messageL.setText("Enter account number!");
                flag = false;
            } else if (Integer.parseInt(AmountGiven) >= AmountFromMap) {
                messageL.setText("Can't withdraw! balance<=1000");
                flag = false;
            } else if (Integer.parseInt(AmountGiven) < 0) {
                messageL.setText("Amount can't be Negetive!");
                flag = false;
            } else {
                flag = true;
                if ((flag == true) && (DashBoard.info.get("accountNumber").equals(AccountNum))) {
                    if ((nm.equals(Holdername)) || ((nm + " " + DashBoard.info.get("surname")).equals(Holdername))) {
                        DashBoard.modifyAndWriteAmount(DashBoard.info.get("username"),
                                (AmountFromMap - Integer.parseInt(AmountGiven)), DashBoard.info);

                        DashBoard.LogFileWriter(date.toString(), DashBoard.info.get("username"), "Withdrawal",
                                "-" + AmountGiven, AccountNum, "Omkar's Bank", "Successful");
                        messageL.setText("Database updated!");

                        this.setVisible(false);
                        DashboardUIClass n = new DashboardUIClass();
                        n.DashBoardUI(unm);
                        DashboardUIClass.indicatorL.setText("Last Activity: Amount Withdrawal!");
                    } else {
                        messageL.setText("Incorrect inputs!");
                    }
                } else {
                    if (!DashBoard.info.get("accountNumber").equals(AccountNum)) {
                        messageL.setText("illegal action caught! can't withdraw");
                        DashBoard.LogFileWriter(date.toString(), unm, "Withdrawal", "0",
                                DashBoard.info.get("accountNumber"), "Omkar's Bank", "Failed");

                    } else {
                        messageL.setText("Something Went Wrong!");
                    }
                    DashBoard.LoadUserInfo(unm, DashBoard.info);// reloading current users info
                }
            }
        }
    }
}
