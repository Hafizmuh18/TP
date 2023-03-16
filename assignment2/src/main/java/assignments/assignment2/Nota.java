package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    //* Attribute-attribute di dalam class nota*/  
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private String id;
    private static int jmlNota;
    private int idNota;
    private int sisaHariPengerjaan;
    private boolean isReady;
    private boolean diskon;
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        //* Constructter untuk class nota yang menerima 4 parameter*/
        /* yang dijadikan instance untuk object-object Nota */
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.id = member.getId();
        this.diskon = member.getDiskon();
        String nota = NotaGenerator.generateNota(id, paket, berat, tanggalMasuk,this.diskon);
        //* Mencari hari pengerjaan berdasarkan paket yang dipilih */
        if(paket.equalsIgnoreCase("express")){
            this.sisaHariPengerjaan = 1;
        }else if(paket.equalsIgnoreCase("fast")){
            this.sisaHariPengerjaan = 2;
        }else if(paket.equalsIgnoreCase("reguler")){
            this.sisaHariPengerjaan = 3;
        }
        setidNota();
        addJmlNota();
        System.out.println("[ID Nota = "+getidNota()+"]");
        System.out.println(nota);
        System.out.println("Status      	: "+getAvailable());
    }

    //* Method-method getter dan setter dan juga 1 method untuk menaikan jumlah nota setiap object nota dibuat */
    public void addJmlNota(){
        jmlNota++;
    }
    public int getJmlNota(){
        return jmlNota;
    }
    public void setidNota(){
        this.idNota = jmlNota;
    }
    public int getidNota(){
        return this.idNota;
    }
    public Member getMember(){
        return this.member;
    }
    public String getId(){
        return this.id;
    }
    public boolean getIsReady(){
        return isReady;
    }

    //* Method-method untuk membantu menjalankan program */
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
    

}
