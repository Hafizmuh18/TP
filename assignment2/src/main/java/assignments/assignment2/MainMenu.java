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

public class MainMenu {
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
        // TODO: handle generate user
        String noHp = "";
        System.out.println("Masukan nama Anda:");
        String nama = inp.nextLine();
        System.out.println("Masukan nomor handphone Anda:");
        while(true){
            try{
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
            String checkId = NotaGenerator.generateId(nama, noHp);
            if(member.getId().equals(checkId)){
                System.out.println("Member dengan nama "+member.getNama()+" dan nomor hp "+member.getNoHp()+" sudah ada!");
                return;
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
        // TODO: handle ambil cucian
        String paket = "";
        System.out.println("Masukan ID member:");
        String id = inp.nextLine();
        if(dictIdMember.containsKey(id)){
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
            System.out.println("Member dengan ID "+id+" tidak ditemukan!");
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        int jumlahNota = notaList.size();
        System.out.println("Terdaftar "+jumlahNota+" nota dalam sistem.");
        for(Nota nota:notaList){
            System.out.println("- ["+nota.getNotaNumber()+"] Status      	: "+nota.getAvailable());
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.println("Terdaftar "+jumlahMember+" member dalam sistem.");
        for(Member member:memberList){
            System.out.println("- "+member.getId()+" : "+member.getNama());
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID nota yang akan diambil: ");
        String strIdNota = "";
        int idNota = 0;
        while(true){
            try{
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
            int checkNota = nota.getNotaNumber();
            if(idNota == checkNota){
                boolean isReady = nota.getIsReady();
                if(isReady){
                    notaList.remove(nota);
                    dictIdNota.remove(nota.getId());
                    System.out.println("Nota dengan ID "+idNota+" berhasil diambil!");
                }else if(!isReady){
                    System.out.println("Nota dengan ID "+idNota+" gagal diambil!");
                }return;
            }
        }
        System.out.println("Nota dengan ID "+idNota+" tidak ditemukan!");
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DAY_OF_MONTH,1);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for(Nota nota:notaList){
            if(nota.finishChecker()){
                System.out.println("Laudry dengan nota ID "+nota.getNotaNumber()+" sudah dapat diambil!");
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
