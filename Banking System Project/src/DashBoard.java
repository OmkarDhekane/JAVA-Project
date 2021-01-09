package src;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ui.DashboardUIClass;
import ui.DepositeUIClass;
import ui.MoneyTransferUIClass;
import ui.MyException;
import ui.RegistrationPageUIClass;
import ui.WithdrawalUIClass;

public class DashBoard implements ActionListener {

    public String unm;
    private DashboardUIClass obj;
    CreateMessagePage cb;
    private static int count = 10;

    public static Map<String, String> info = new HashMap<String, String>();

    public DashBoard(DashboardUIClass obj, String unm) {
        this.obj = obj;
        this.unm = unm;// IMP do not change
    }

    public DashBoard() {
    }

    // **************************************************************************************//
    public static void LoadUserInfo(String userN, Map<String, String> map) {
        String prop[] = { "name", "surname", "age", "gender", "contactNo", "username", "typeOfAccount", "accountNumber",
                "balance", "rateOfInterest" };
        String val;
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./db/" + userN + "/MyDatabase.txt")));

            while ((val = br.readLine()) != null) {
                map.put(prop[i], val);
                i++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // **************************************************************************************//
    public static void modifyAndWriteAmount(String userN, int newAmount, Map<String, String> map) {

        // DashBoard.LoadUserInfo(userN);
        map.replace("balance", String.valueOf(newAmount));

        try {
            FileWriter fw = new FileWriter("./db/" + userN + "/MyDatabase.txt");

            fw.write(map.get("name") + "\r\n");
            fw.write(map.get("surname") + "\r\n");
            fw.write(map.get("age") + "\r\n");
            fw.write(map.get("gender") + "\r\n");
            fw.write(map.get("contactNo") + "\r\n");
            fw.write(map.get("username") + "\r\n");
            fw.write(map.get("typeOfAccount") + "\r\n");
            fw.write(map.get("accountNumber") + "\r\n");
            fw.write(map.get("balance") + "\r\n");
            if (map.get("typeOfAccount").equals("Savings Account")) {
                fw.write(map.get("rateOfInterest") + "\r\n");
            } else {
                fw.write("null" + "\r\n");
            }
            fw.close();
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    // **************************************************************************************//
    public static void LoadUserInfoByAC(String ac, Map<String, String> map) throws MyException {
        File f = new File("./db");
        File[] pathA = f.listFiles();
        List<String> list;
        for (File fl : pathA) {
            if (fl.getName().toString().equals("Store_UP")) {
                throw new MyException("File not Found!\nStore_UP doesn't contain database files.");
            }
            try {
                list = Files.readAllLines(Paths.get("./db/" + fl.getName() + "/MyDatabase.txt"),
                        StandardCharsets.UTF_8);
                if (list.contains(ac)) {
                    DashBoard.LoadUserInfo(fl.getName(), map);
                    break;
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // **************************************************************************************//
    public static void LogFileWriter(String date, String name, String reason, String amount, String AC, String bankname,
            String status) {

        try {
            FileWriter fw;
            String log;
            fw = new FileWriter("./db/" + name + "/MyLogFile.txt", true);
            log = name + "|" + reason + "|" + amount + "|" + AC + "|" + date + "|" + bankname + "|" + status + "\n";
            for (int i = 0; i < log.length(); i++) {
                fw.append(log.charAt(i));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.gc();

    }

    // ----------------------------------------------------------//
    @Override
    public void actionPerformed(ActionEvent e) {
        LoadUserInfo(unm, info);

        if (e.getSource() == obj.checkBalanceB) {
            cb = new CreateMessagePage("DashBoard/Balance Information", 1000, 150, 340, 450, new Color(0, 85, 255));
            cb.MyMessage("Account number: ", info.get("accountNumber"), 35, 35, Color.WHITE);
            cb.MyMessage("Account Type: ", info.get("typeOfAccount"), 35, 70, Color.WHITE);
            cb.MyMessage("Account Balance: ", info.get("balance"), 35, 105, Color.WHITE);
            cb.MyMessage("Rate of Interest: ", info.get("rateOfInterest").concat("  per annum"), 35, 140, Color.WHITE);

        } else if (e.getSource() == obj.depositeB) {
            if (this.cb != null) {
                cb.setVisible(false);
            }
            obj.setVisible(false);
            new DepositeUIClass().DepositeUI(unm);
        } else if (e.getSource() == obj.withdrawB) {

            if (this.cb != null) {
                cb.setVisible(false);
            }
            obj.setVisible(false);
            new WithdrawalUIClass().WithdrawalUI(unm);

        } else if (e.getSource() == obj.transactionDetailsB) {

            if (this.cb != null) {
                cb.setVisible(false);
            }
            try {
                Desktop.getDesktop().open(new File("./db/" + unm + "/MyLogFile.txt"));
            } catch (IOException e1) {
                System.out.println("Error From DashBoard page");
                e1.printStackTrace();
            }

        } else if (e.getSource() == obj.bTobB) {
            if (this.cb != null) {
                cb.setVisible(false);
            }
            if (Integer.parseInt(info.get("balance")) <= 1000) {
                CreateMessagePage temp = new CreateMessagePage("Error Page", 560, 300, 365, 150,
                        new Color(255, 255, 128));
                temp.MyMessage("Low Balance! ", "your balance is less than 1000", 20, 30, Color.BLACK);
            } else {
                obj.setVisible(false);
                new MoneyTransferUIClass().MoneyTransferUI(unm);
            }
        } else if (e.getSource() == obj.loginOutB) {
            if (this.cb != null) {
                cb.setVisible(false);
            }
            CreateMessagePage ch = new CreateMessagePage("Logout", 560, 300, 365, 155, new Color(255, 255, 128));
            ch.MyMessage("Are you Sure?", "", 120, 15, Color.BLACK);
            ch.myButton("Yes", 50, 65, 100, 40, Color.RED, Color.white).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ch.setVisible(false);
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (count > -1) {
                                DashboardUIClass.indicatorL.setText(RegistrationPageUIClass
                                        .htmlFont("Thank You! System is now switching off in " + " " + count + " sec"));
                                count--;
                                if (count == 0) {
                                    System.out.println("\t\t\t\t\t\tThank you for visiting Omkar's Bank!");
                                    System.exit(0);

                                }
                                try {
                                    Thread.sleep(1000);

                                } catch (InterruptedException e) {

                                    System.out.println(e.getMessage());

                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    t1.start();
                }
            });
            ch.myButton("No", 200, 65, 100, 40, Color.BLUE, Color.white).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ch.setVisible(false);
                }
            });
        }
        System.gc();
    }
}
