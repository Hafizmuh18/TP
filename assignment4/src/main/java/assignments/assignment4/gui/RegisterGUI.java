package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;
    private GridBagConstraints grid = new GridBagConstraints();

    public RegisterGUI(LoginManager loginManager) {
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
        nameLabel = new JLabel("Masukan nama Anda:");
        nameTextField = new JTextField(20);
        phoneLabel = new JLabel("Masukan nomor handphone Anda:");
        phoneTextField = new JTextField(20);
        passwordLabel = new JLabel("Masukan password Anda:");
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");
        backButton = new JButton("Kembali");

        grid.insets = new Insets(5, 5, 5, 5);
        grid.anchor = GridBagConstraints.FIRST_LINE_START;

        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> handleBack());

        grid.gridx = 0;
        grid.gridy = 0;
        mainPanel.add(nameLabel, grid);

        grid.gridy = 1;
        mainPanel.add(nameTextField, grid);

        grid.gridy = 2;
        mainPanel.add(phoneLabel, grid);

        grid.gridy = 3;
        mainPanel.add(phoneTextField, grid);

        grid.gridy = 4;
        mainPanel.add(passwordLabel, grid);

        grid.gridy = 5;
        mainPanel.add(passwordField, grid);

        grid.gridy = 6;
        mainPanel.add(registerButton, grid);

        grid.gridy = 7;
        mainPanel.add(backButton, grid);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo("HOME");
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        String password =String.valueOf(passwordField.getPassword());
        if(nama.equals("") || noHp.equals("") || password.equals("") || nama.split(" ").length == 0 || noHp.split(" ").length == 0 || password.split(" ").length == 0 || noHp.split(" ")[0].equals("") || password.split(" ")[0].equals("") || nama.split(" ")[0].equals("")){
            JOptionPane.showMessageDialog(mainPanel, "Semua field diatas wajib diisi!", "Empety Field", 0);
            return;
        }

        for(String digit : noHp.split("")){
            try{
                Integer.parseInt(digit);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(mainPanel, "Nomor handphone harus berisi angka!", "Invalid Phone Number", 0);
                return;
            }
        }
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        Member member = loginManager.register(nama, noHp, password);
        if(member == null){
            JOptionPane.showMessageDialog(mainPanel, "Username dengan nama "+nama+" dan nomor hp "+noHp+" sudah ada!", "Registration Vailed", 0);
            return;
        }
        JOptionPane.showMessageDialog(mainPanel, "Berhasil membuat user dengan ID "+member.getId()+"!", "Registration Successful", 1);
        
    }
}
