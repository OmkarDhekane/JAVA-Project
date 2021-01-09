package src;

import ui.LoginPageUIClass;

public class MAIN extends Thread {

    public void run() {
        new LoginPageUIClass().LoginPageUI();
    }

    public static void main(String[] args) {
        new MAIN().start();
    }
}
