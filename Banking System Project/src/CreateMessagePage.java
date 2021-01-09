package src;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.RegistrationPageUIClass;

public class CreateMessagePage extends JFrame {
    private static final long serialVersionUID = 1L;

    // JLabel message;
    public CreateMessagePage(String Title, int x, int y, int width, int height, Color background) {
        // super.setResizable(false);
        super.setTitle(Title.toString());// 1000, 150
        super.setBounds(x, y, width, height);
        super.getContentPane().setBackground(background);
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.white, 3));
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        super.setLayout(null);
        super.setVisible(true);
    }

    public void MyMessage(String Message, String Content, int x, int y, Color Foreground) {

        JLabel message = new JLabel("<html><span style='font-size:13px'>" + Message + " " + Content + "</span></html>");
        message.setBounds(x, y, 300, 40);
        message.setForeground(Foreground);
        super.add(message);
    }

    public JButton myButton(String ActionName, int x, int y, int width, int height, Color backgColor,
            Color foregColor) {
        JButton button = new JButton(RegistrationPageUIClass.htmlFont(ActionName));
        button.setBounds(x, y, width, height);
        button.setBackground(backgColor);
        button.setForeground(foregColor);
        super.add(button);
        return button;
    }

    public void MyNavbar(String title, int x, int y, Color c) {
        JPanel panel = new JPanel();
        this.MyMessage(title, "", x, y, Color.WHITE);
        panel.setBounds(0, 0, 500, 100);
        panel.setBackground(c);
        super.add(panel);
    }

}
