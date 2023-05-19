package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;
    private GridBagConstraints grid = new GridBagConstraints();

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        titleLabel = new JLabel("Selamat Datang di CuciCuci System");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");
        dateLabel = new JLabel("Hari Ini : " + NotaManager.fmt.format(NotaManager.cal.getTime()));

        loginButton.addActionListener(e -> handleToLogin());

        registerButton.addActionListener(e -> handleToRegister());

        toNextDayButton.addActionListener(e -> handleNextDay());

        grid.insets = new Insets(10, 10, 10, 10);

        grid.gridx = 0;
        grid.gridy = 0;
        grid.ipady = 10;
        mainPanel.add(titleLabel, grid);
        
        grid.gridx = 0;
        grid.gridy = 1;
        grid.ipady = 20;
        grid.ipadx = 119;
        mainPanel.add(loginButton, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        grid.ipady = 20;
        grid.ipadx = 100;
        mainPanel.add(registerButton, grid);

        grid.gridx = 0;
        grid.gridy = 3;
        grid.ipady = 20;
        mainPanel.add(toNextDayButton, grid);

        
        grid.gridx = 0;
        grid.gridy = 4;
        grid.ipady = 20;
        grid.ipadx = 30;
        grid.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(dateLabel, grid);
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo("REGISTER");
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo("LOGIN");
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();
        MainFrame.getInstance().navigateTo("");
        JOptionPane.showMessageDialog(mainPanel, "Tidur ahh zzzz.....", "Skip this boring day...", 1);
        dateLabel.setText("Hari Ini : " + NotaManager.fmt.format(NotaManager.cal.getTime()));
    }
}
