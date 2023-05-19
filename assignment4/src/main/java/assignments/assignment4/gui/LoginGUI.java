package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;
    private GridBagConstraints grid = new GridBagConstraints();


    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
        // TODO
        idLabel = new JLabel("Masukan ID Anda:");
        idTextField = new JTextField(20);
        passwordLabel = new JLabel("Masukan password Anda:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        backButton = new JButton("Kembali");

        grid.insets = new Insets(5, 5, 5, 5);
        grid.anchor = GridBagConstraints.FIRST_LINE_START;

        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());

        grid.gridx = 0;
        grid.gridy = 0;
        mainPanel.add(idLabel, grid);

        grid.gridy = 1;
        mainPanel.add(idTextField, grid);

        grid.gridy = 2;
        mainPanel.add(passwordLabel, grid);

        grid.gridy = 3;
        mainPanel.add(passwordField, grid);

        grid.gridy = 4;
        mainPanel.add(loginButton, grid);

        grid.gridy = 5;
        mainPanel.add(backButton, grid);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo("HOME");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String id = idTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        idTextField.setText("");
        passwordField.setText("");
        SystemCLI systemCLI = loginManager.getSystem(id);
        if(systemCLI == null){
            JOptionPane.showMessageDialog(mainPanel, "ID tidak terdaftar!", "Login Vailed", 0);
            return;
        }
        Boolean passRight = MainFrame.getInstance().login(id, password);
        if(passRight == false){
            JOptionPane.showMessageDialog(mainPanel, "Password salah!", "Login Vailed", 0);
            return;
        }
        

    }
}
