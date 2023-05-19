package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    private JPanel panel = new JPanel(new GridBagLayout());
    private final GridBagConstraints grid = new GridBagConstraints();

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        grid.insets = new Insets(10, 10, 10, 10);

        paketLabel = new JLabel("Paket Laundry:");
        String[] isiComboBox = {
            "Express",
            "Fast",
            "Reguler",
        };
        paketComboBox = new JComboBox<String>(isiComboBox);
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaket();
            }
        });
        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratTextField = new JTextField(10);
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / Kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4 Kg pertama, kemudian 500 / Kg)");

        createNotaButton = new JButton("Buat Nota");
        createNotaButton.addActionListener(e -> createNota());
        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());

        grid.gridx = 0;
        grid.gridy = 0;
        grid.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(paketLabel, grid);
        grid.gridx = 1;
        panel.add(paketComboBox, grid);
        grid.gridx = 2;
        panel.add(showPaketButton, grid);
        grid.gridx = 0;
        grid.gridy = 1;
        panel.add(beratLabel, grid);
        grid.gridx = 1;
        panel.add(beratTextField, grid);
        grid.gridy = 2;
        grid.gridx = 0;
        panel.add(setrikaCheckBox, grid);
        grid.gridy = 3;
        panel.add(antarCheckBox, grid);
        grid.gridy = 4;
        grid.ipadx = 3;
        panel.add(createNotaButton, grid);
        grid.gridy = 5;
        panel.add(backButton, grid);

        add(panel);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        int berat = 0;
        String paket = (String) paketComboBox.getSelectedItem();
        String strBerat = beratTextField.getText();
        if(strBerat == ""){
            JOptionPane.showMessageDialog(panel, "Berat cucian harus berisi angka!", "Error", 0);
            return;
        }
        try{
            for(String digit : strBerat.split("")){
                Integer.parseInt(digit);
            }
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(panel, "Berat cucian harus berisi angka!", "Error", 0);
            return;
        }
        berat = Integer.parseInt(strBerat);
        if(berat < 1){
            JOptionPane.showMessageDialog(panel, "Berat cucian harus berisi angka!", "Error", 0);
            return;
        }
        if(berat < 2){
            berat = 2;
            JOptionPane.showMessageDialog(panel, "Cucian kurang dari 2 kg, maka cucian akan dianggap 2 kg", "Info", 1);
        }
        Member member = memberSystemGUI.getLoggedInMember();
        String tanggalMasuk = fmt.format(cal.getTime());
        Nota nota = new Nota(member, berat, paket, tanggalMasuk);
        if(setrikaCheckBox.isSelected()){
            nota.addService(new SetrikaService());
        }
        if(antarCheckBox.isSelected()){
            nota.addService(new AntarService());
        }
        NotaManager.addNota(nota);
        member.addNota(nota);
        JOptionPane.showMessageDialog(panel, "Nota berhasil dibuat", "Succees", 1);
        paketComboBox.setSelectedItem("Express");
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        MainFrame.getInstance().navigateTo("MEMBER");
    }
}
