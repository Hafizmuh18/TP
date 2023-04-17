package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.lang.model.util.ElementScanner14;

import assignments.assignment1.NotaGenerator;

import static assignments.assignment1.NotaGenerator.*;
import java.util.Random;


public class MainMenu {
    //* Attribute-attribute untuk class MainMenu */
    private static final Scanner inp = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static ArrayList<String> listNamaMember = new ArrayList<String>();
    private static int jumlahMember = 0;
    private static HashMap<String, Member> dictIdMember = new HashMap<String,Member>();
    private static HashMap<String, Nota> dictIdNota = new HashMap<String, Nota>();
    

    public static void main(String[] args) {
        //* Method main dalam mejalankan program */
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = inp.nextLine();
            System.out.println("================================");
            if(command.equals("1"))
                handleGenerateUser();
            else if(command.equals("2"))
                handleGenerateNota();
            else if(command.equals("3"))
                handleListNota();
            else if(command.equals("4"))
                handleListUser();
            else if(command.equals("5"))
                handleAmbilCucian();
            else if(command.equals("6"))
                handleNextDay();
            else if(command.equals("0"))
                isRunning =  false;
            else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        //* Method untuk men generate object member berdasarkan input nama dan nomor hp */
        String noHp = "";
        System.out.println("Masukan nama Anda:");
        String nama = inp.nextLine();
        System.out.println("Masukan nomor handphone Anda:");
        while(true){
            try{
                //* Mmemastikan bahwa input yang diasukan di noHp berupa digit semua */
                noHp = inp.nextLine();
                String[] arrayNoHp = noHp.split("");
                for(String character:arrayNoHp){
                    Integer.parseInt(character);
                }
                break;
            }catch (Exception e){
                System.out.println("Field nomor hp hanya menerima digit.");
            }
        }
        for(Member member:memberList){
            //* Loop untuk memastikan member baru yang akan masuk belum ada di listMember */
            String checkId = NotaGenerator.generateId(nama, noHp);
            if(member.getId().equals(checkId)){
                System.out.println("Member dengan nama "+nama+" dan nomor hp "+member.getNoHp()+" sudah ada!");
                return; //* Stop method jika member yang ingin masuk sudah ada di list */
            }
        }
        Member userMember = new Member(nama, noHp);
        memberList.add(userMember);
        listNamaMember.add(nama);
        String id = userMember.getId();
        dictIdMember.put(id,userMember);
        System.out.println("Berhasil membuat member dengan ID "+userMember.getId()+"!");
        jumlahMember++;

    }

    private static void handleGenerateNota() {
        //* Membuat object nota dengan syarat ada id member, dan object nota tersebut dimasukan ke listNota */
        String paket = "";
        System.out.println("Masukan ID member:");
        String id = inp.nextLine();
        if(dictIdMember.containsKey(id)){
            //* Memastikan bahwa member sudah terdaftar jika ingin mengenerate nota */
            Member member = dictIdMember.get(id);
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
                    NotaGenerator.showPaket();
                }else{
                    println("Paket "+paket+" tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
                }
            }
            System.out.println("Masukan berat cucian Anda:");
            int berat = inp.nextInt();
            inp.nextLine();
            String tanggalTerima = fmt.format(cal.getTime());
            Nota nota = new Nota(member, paket, berat, tanggalTerima);
            notaList.add(nota);
            dictIdNota.put(id,nota);
        }else{
            //* ID Member belum terdaftar */
            System.out.println("Member dengan ID "+id+" tidak ditemukan!");
        }
    }

    private static void handleListNota() {
        //* Melakukan loop untuk setiap nota di listNota */
        int jumlahNota = notaList.size();
        System.out.println("Terdaftar "+jumlahNota+" nota dalam sistem.");
        for(Nota nota:notaList){
            System.out.println("- ["+nota.getidNota()+"] Status      	: "+nota.getAvailable());
        }
    }

    private static void handleListUser() {
        //* Melakukan loop untuk setiap member di listNota */
        System.out.println("Terdaftar "+jumlahMember+" member dalam sistem.");
        for(Member member:memberList){
            System.out.println("- "+member.getId()+" : "+member.getNama());
        }
    }

    private static void handleAmbilCucian() {
        //* Mengambil cucian berdasarkan ID dari Nota */
        System.out.println("Masukan ID nota yang akan diambil: ");
        String strIdNota = "";
        int idNota = 0;
        while(true){
            try{
                //* Memastikan ID Nota itu angka dan lebih dari 0 */
                strIdNota = inp.nextLine();
                String[] arrayId = strIdNota.split("");
                for(String character:arrayId){
                    Integer.parseInt(character);
                }
                idNota = Integer.parseInt(strIdNota);
                break;
            }catch (Exception e){
                System.out.println("ID nota berbentuk angka!");
            }
        }
        for(Nota nota:notaList){
            //* Memastikan bahwa nota ada di listNota */
            int checkNota = nota.getidNota();
            if(idNota == checkNota){
                boolean isReady = nota.getIsReady();
                if(isReady){
                    //* Memastikan bahwa nota dengan ID tersebut sudah dapat diambil */
                    notaList.remove(nota);
                    dictIdNota.remove(nota.getId());
                    System.out.println("Nota dengan ID "+strIdNota+" berhasil diambil!");
                }else if(!isReady){
                    //* Jika Nota dengan ID Nota belum selesai */
                    System.out.println("Nota dengan ID "+strIdNota+" gagal diambil!");
                }return;
            }
        }
        //* Jika ID nota tidak ditemukan di dalam listNota */
        System.out.println("Nota dengan ID "+strIdNota+" tidak ditemukan!");
    }

    private static void handleNextDay() {
        //* Menaikan hari menjadi hari berikutnya */
        cal.add(Calendar.DAY_OF_MONTH,1);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for(Nota nota:notaList){
            if(nota.finishChecker()){
                //* Melakukan pengecekan untuk setiap nota di list, apakah nota tersebut sudah siap diambil */
                System.out.println("Laudry dengan nota ID "+nota.getidNota()+" sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
