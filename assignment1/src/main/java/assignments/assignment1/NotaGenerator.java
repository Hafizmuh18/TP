package assignments.assignment1;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner inp = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        boolean working = true;
        String inputNama = "";
        String inpNomor = "";
        String id = "";
        while(working){
            /*Loop akan terus dijalanin  selama working == true
             * working akan != true jika pilihan yang dimasukan 0
             */
            printMenu();
            print("Pilihan : ");
            String pilihan = inp.nextLine();
            println("================================");
            if(pilihan.equals("1")){
                println("Masukan nama anda :");
                inputNama = inp.nextLine();
                println("Masukan nomor handphone Anda :");
                boolean nomorBenar = true;
                while(nomorBenar){
                    /*Memastikan bahwa input nomor bukan angka
                     * maupun bukan bilangan negatif
                     */
                    try{
                        inpNomor = inp.nextLine();
                        Long.parseLong(inpNomor);
                        if(Long.parseLong(inpNomor) < 0){
                            Integer.parseInt("a");
                        }
                        nomorBenar = false;
                    }
                    catch(Exception e){
                        println("Nomor hp hanya menerima digit");
                    }
                }
                id = generateId(inputNama, inpNomor);
                println("ID Anda : " + id);
            }
            else if(pilihan.equals("2")){
                int berat = 0;
                String tanggalTerima = "";
                String paket = "";
                String strBerat = "a";
                boolean benarTanggal = true;
                println("Masukan nama anda :");
                inputNama = inp.nextLine();
                println("Masukan nomor handphone Anda :");
                boolean nomorBenar = true;
                while(nomorBenar){
                    try{
                     /*Memastikan bahwa input nomor bukan angka
                     * maupun bukan bilangan negatif
                     */
                        inpNomor = inp.nextLine();
                        Long.parseLong(inpNomor);
                        if(Long.parseLong(inpNomor) < 0){
                            Integer.parseInt("a");
                        }
                        nomorBenar = false;
                    }
                    catch(Exception e){
                        println("Nomor hp hanya menerima digit");
                    }
                }
                id = generateId(inputNama, inpNomor);
                while(benarTanggal){
                    /*Memastikan input tanggal berniali benar */
                    println("Masukan tanggal terima : ");
                    tanggalTerima = inp.nextLine();
                    try{
                        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate.parse(tanggalTerima,formatTanggal);
                        benarTanggal = false;
                    }catch(Exception e){
                        println("Tanggal tidak bisa diterima, pastikan tanggal atau format benar");
                    }
                }
                boolean pilihPaket = true;
                while(pilihPaket){
                    /*Memastikan input paket laundry benar */
                    println("Masukan paket laundry :");
                    paket = inp.nextLine();
                    if(paket.equalsIgnoreCase("express")){
                        pilihPaket = false;
                    }else if(paket.equalsIgnoreCase("fast")){
                        pilihPaket = false;
                    }else if(paket.equalsIgnoreCase("reguler")){
                        pilihPaket = false;
                    }else if(paket.equalsIgnoreCase("?")){
                        showPaket();
                    }else{
                        println("Paket "+paket+" tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
                    }
                }
                println("Masukan berat cucian anda [Kg]:");
                boolean nomorBerat = true;
                while(nomorBerat){
                    /*Memastikan bahwa berat itu bilangan bulat positif, jadi tidak
                     * berupa huruf dan bilangan negatif
                     */
                    try{
                        strBerat = inp.nextLine();
                        berat = Integer.parseInt(strBerat);
                        if(berat<1){
                            strBerat = "a";
                            berat = Integer.parseInt(strBerat);
                        }
                        nomorBerat = false;
                    }
                    catch(Exception e){
                        println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    }
                }
                boolean a = false;
                String nota = generateNota(id, paket, berat, tanggalTerima, a);
                println("Nota Laudry");
                println(nota);
            }else if(pilihan.equals("0")){
                println("Terima kasih telah menggunakan NotaGenerator!");
                working = false;
            }else{
                println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        inp.close();
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    public static String generateId(String inpNama, String nomorHP){
        /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
        String[] listNama = inpNama.split(" ");
        String nama = listNama[0].toUpperCase();
        int checkSum = 0;
        String[] listChar = nama.split(""); //Membuat array dari nama
        String[] listNumber = (nomorHP).split(""); //Membuat array dari no hp
        for(String letter : listChar){
            /*Melakukan loop untuk setiap character di array listChar*/
            boolean huruf = true;
            if(huruf){
                char character = letter.charAt(0);
                int asciiChar = character;
                if(asciiChar >= 65 && asciiChar <= 90){
                    /*Jika char dari nama adalah hururf */
                    checkSum += asciiChar - 64;
                }else if(48 >= asciiChar && 57 <= asciiChar){
                    /*Jika char dari nama adalah angka */
                    checkSum += asciiChar-48;
                }else{
                    /*Jika char dari nama selain huruf dan angka */
                    checkSum += 7;
                }
            }
        }
        for(String aNumber : listNumber){
            int intNumber = Integer.parseInt(aNumber);
            checkSum += intNumber;
        }
        checkSum+=7;
        String twoDigit = "";
        String strCheckSum = Integer.toString(checkSum);
        if(strCheckSum.length() <2){ 
            /*Jika sum dari semua nama dan no hpnya hanya 1 maka ditambakan 0 didepannya, ex: sumnnya 5 maka digitnya 05 */
            twoDigit = "0"+strCheckSum;
        }else if(strCheckSum.length() >2){
            twoDigit = strCheckSum.substring(strCheckSum.length() - 2);
        }else{
            twoDigit = strCheckSum;
        }
        String id = nama+"-"+nomorHP+"-"+twoDigit;
        return id;
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */
        int harga = 0;
        int hari = 0;
        String nota = "";
        if(paket.equalsIgnoreCase("express")){
            harga = 12000;
            hari = 1;
        }else if(paket.equalsIgnoreCase("fast")){
            harga = 10000;
            hari = 2;
        }else if(paket.equalsIgnoreCase("reguler")){
            harga = 7000;
            hari = 3;
        }
        if(berat<2){
            berat = 2;
            println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }
        int totHarga = harga*berat;
        String tanggalSelesai = getTanggalSelesai(tanggalTerima, hari);
        nota = ("ID    : "+id+"\nPaket : "+paket+"\nHarga :"+"\n"+berat+" kg x "+harga+" = "+totHarga+"\nTanggal Terima  : "+tanggalTerima+"\nTanggal Selesai : "+tanggalSelesai);
        
        return nota;
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima, boolean diskon){
        /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */
        int harga = 0;
        int hari = 0;
        String nota = "";
        if(paket.equalsIgnoreCase("express")){
            harga = 12000;
            hari = 1;
        }else if(paket.equalsIgnoreCase("fast")){
            harga = 10000;
            hari = 2;
        }else if(paket.equalsIgnoreCase("reguler")){
            harga = 7000;
            hari = 3;
        }
        if(berat<2){
            berat = 2;
            println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }
        int totHarga = harga*berat;
        String tanggalSelesai = getTanggalSelesai(tanggalTerima, hari);
        if(diskon){
            int diskonHarga = totHarga - (totHarga*50/100);
            nota = ("ID    : "+id+"\nPaket : "+paket+"\nHarga :"+"\n"+berat+" kg x "+harga+" = "+totHarga+" = "+diskonHarga+" (Discount member 50%!!!)"+"\nTanggal Terima  : "+tanggalTerima+"\nTanggal Selesai : "+tanggalSelesai);
        }else{
            nota = ("ID    : "+id+"\nPaket : "+paket+"\nHarga :"+"\n"+berat+" kg x "+harga+" = "+totHarga+"\nTanggal Terima  : "+tanggalTerima+"\nTanggal Selesai : "+tanggalSelesai);
        }
        return nota;
    }

    public static String getTanggalSelesai(String tanggalDiTerima, int hari){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalTerima = LocalDate.parse(tanggalDiTerima,formatTanggal);
        LocalDate unformatTanggalSelesai = tanggalTerima.plusDays(hari);
        String tanggalSelesai = unformatTanggalSelesai.format(formatTanggal);
        return tanggalSelesai;
    }

    public static void println(String printnan){
        System.out.println(printnan);
    }

    public static void print(String printnan){
        System.out.print(printnan);
    }
}