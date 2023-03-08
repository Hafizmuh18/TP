package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private String id;
    private static int jmlNota;
    private int notaNumber;
    private int sisaHariPengerjaan;
    private boolean isReady;
    private boolean diskon;
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.id = member.getId();
        this.diskon = member.getDiskon();
        String nota = NotaGenerator.generateNota(id, paket, berat, tanggalMasuk,this.diskon);
        if(paket.equalsIgnoreCase("express")){
            this.sisaHariPengerjaan = 1;
        }else if(paket.equalsIgnoreCase("fast")){
            this.sisaHariPengerjaan = 2;
        }else if(paket.equalsIgnoreCase("reguler")){
            this.sisaHariPengerjaan = 3;
        }
        setNotaNumber();
        addJmlNota();
        System.out.println("[ID Nota = "+getNotaNumber()+"]");
        System.out.println(nota);
        System.out.println("Status      	: "+getAvailable());
    }

    public void addJmlNota(){
        jmlNota++;
    }
    public int getJmlNota(){
        return jmlNota;
    }
    public void setNotaNumber(){
        this.notaNumber = jmlNota;
    }
    public int getNotaNumber(){
        return this.notaNumber;
    }
    public Member getMember(){
        return this.member;
    }
    public String getId(){
        return this.id;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public boolean finishChecker(){
        if(!isReady){
            this.sisaHariPengerjaan--;
            if(this.sisaHariPengerjaan == 0){
                isReady = true;
                return isReady;
            }
            return false;
        }
        return true;
    }
    public String getAvailable(){
        String available = "";
        if(isReady){
            available = "Sudah dapat diambil!";
        }else if(!isReady){
            available = "Belum bisa diambil :(";
        }
        return available;
    }
    public boolean getIsReady(){
        return isReady;
    }

}
