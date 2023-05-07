package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import java.util.ArrayList;
import java.util.Arrays;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        //* Method ketika member berhasil login, sehingga dapat menjalankan 3 perintah */
        if(choice == 1){
            //* Jika member memilih untuk membuat nota baru, maka akan meminta user untuk memasukan ketentuan nota yang diinginkan */
            System.out.println("Masukan paket laundry:");
            Nota.showPaket();
            String paket = in.nextLine();
            System.out.println("Masukan berat cucian anda [Kg]:");
            String strBerat = in.nextLine();
            int berat = Integer.parseInt(strBerat);
            if(berat < 2){
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
            String tanggalMasuk = fmt.format(cal.getTime());
            Nota newNota = new Nota(loginMember, berat, paket, tanggalMasuk);
            System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0\n[Ketik x untuk tidak mau]: ");
            if(!in.nextLine().equalsIgnoreCase("x")){
                newNota.addService(new SetrikaService());
            }
            System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg\n[Ketik x untuk tidak mau]: ");
            if(!in.nextLine().equalsIgnoreCase("x")){
                newNota.addService(new AntarService());
            }
            loginMember.addNota(newNota);
            NotaManager.addNota(newNota);
            System.out.println("Nota berhasil dibuat!");
        }else if(choice == 2){
            //* Jika user ingin melihat nota mereka, maka tiap nota yang ada di listNota mereka akan di cetak */
            Nota[] listNota = loginMember.getNotaList();
            int panjangNota = listNota.length;
            int count = 0;
            for(Nota nota : listNota){
                count++;
                System.out.println(nota);
                if(!(count == panjangNota)){
                    System.out.println("");
                }
            }
        }else if(choice == 3){
            //* Jika user ingin logout, maka logout sama dengan true */
            logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        //* Menambahkan member ke dalam array memberList */
        Member[] tempArray = Arrays.copyOf(memberList, memberList.length+1);
        memberList = Arrays.copyOf(tempArray, tempArray.length);
        memberList[memberList.length-1]=member;
    }
}