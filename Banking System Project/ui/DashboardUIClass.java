package ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import src.DashBoard;

public class DashboardUIClass extends JFrame {
    private static final long serialVersionUID = 1L;
    public JLabel welcomeL, checkBalanceL, transactionDetailsL, depositeL, withdrawL, bTobL;
    public JButton welcomeB, checkBalanceB, transactionDetailsB, depositeB, withdrawB, bTobB, loginOutB;
    public static JLabel indicatorL;

    public static String htmlFont(String s) {
        return ("<html><span style='font-size:15px'>" + s + "</span></html>");
    }

    public void DashBoardUI(String unm) {
        super.setResizable(false);
        super.setTitle("DashBoard");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(Color.BLACK);
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        DashBoard ob = new DashBoard(this, unm);

        welcomeL = new JLabel(htmlFont("Welcome " + unm + " to Dashboard!"));
        welcomeL.setForeground(Color.WHITE);
        welcomeL.setBounds(20, 20, 360, 40);

        indicatorL = new JLabel("");
        indicatorL.setForeground(Color.RED);
        indicatorL.setBounds(20, 60, 390, 40);
        super.add(indicatorL);

        checkBalanceB = new JButton(htmlFont("Check Balance"));
        checkBalanceB.setBounds(20, 90, 440, 100);
        checkBalanceB.setForeground(Color.WHITE);
        checkBalanceB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        checkBalanceB.setBackground(new Color(0, 153, 51));
        super.add(checkBalanceB);

        checkBalanceB.addActionListener(ob);

        transactionDetailsB = new JButton(htmlFont("Transactions"));
        transactionDetailsB.setBounds(20, 200, 215, 100);
        transactionDetailsB.setForeground(Color.WHITE);
        transactionDetailsB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        transactionDetailsB.setBackground(new Color(0, 153, 51));
        super.add(transactionDetailsB);
        transactionDetailsB.addActionListener(ob);

        depositeB = new JButton(htmlFont("Deposite"));
        depositeB.setBounds(245, 200, 215, 100);
        depositeB.setForeground(Color.WHITE);
        depositeB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        depositeB.setBackground(new Color(0, 153, 51));
        super.add(depositeB);
        depositeB.addActionListener(ob);

        withdrawB = new JButton(htmlFont("Withdrawal"));
        withdrawB.setBounds(245, 310, 215, 100);
        withdrawB.setForeground(Color.WHITE);
        withdrawB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        withdrawB.setBackground(new Color(0, 153, 51));
        super.add(withdrawB);
        withdrawB.addActionListener(ob);

        bTobB = new JButton(htmlFont("B-To-B Transfer"));
        bTobB.setBounds(20, 310, 215, 100);
        bTobB.setForeground(Color.WHITE);
        bTobB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        bTobB.setBackground(new Color(0, 153, 51));
        super.add(bTobB);
        bTobB.addActionListener(ob);

        loginOutB = new JButton(htmlFont("Logout"));
        loginOutB.setBounds(340, 20, 120, 40);
        loginOutB.setForeground(Color.WHITE);
        loginOutB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginOutB.setBackground(new Color(255, 51, 51));
        super.add(loginOutB);
        loginOutB.addActionListener(ob);

        super.add(welcomeL);
        super.setLayout(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
