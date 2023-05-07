package assignments.assignment3.nota;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[0];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;
    private int hariSelesai;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //* Contractor dari kelas Nota */
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota++;
        if(paket.equalsIgnoreCase("express")){
            this.sisaHariPengerjaan = 1;
            this.baseHarga = 12000;
        }else if(paket.equalsIgnoreCase("fast")){
            this.sisaHariPengerjaan = 2;
            this.baseHarga = 10000;
        }else if(paket.equalsIgnoreCase("reguler")){
            this.sisaHariPengerjaan = 3;
            this.baseHarga = 7000;
        }
        this.hariSelesai = sisaHariPengerjaan;
        addService(new CuciService()); //* Menambahkan service cuci yang sudah pasti ada di tiap nota */
    }

    public void addService(LaundryService service){
        //* Menambahkan service ke dalam array services */
        LaundryService[] tempArray = Arrays.copyOf(services, services.length+1);
        services = Arrays.copyOf(tempArray, tempArray.length);
        services[services.length-1]=service;
    }

    public String kerjakan(){
        //* Mengerjakan tiap service yang ada di array services secara berururan muali dari cuci -> Setrika -> Antar, jika sudah semua isDone diubah ke true */
        //* Tambahan : Sebenarnya bisa di loop sekali aja karena udah pasti urut, ini untuk jaa-jaga aja in case arraynya ke acak + astetic(karena efektivitas kode belum dinilai wkwkkwwkw) */
        String kerjaan = "";
        for(LaundryService service:services){
            if(service instanceof CuciService){
                if(!service.isDone()){
                    kerjaan = "Nota "+ this.id + " : " + service.doWork();
                    for(LaundryService checkDoneService : services){
                        if(!checkDoneService.isDone()){
                            return kerjaan;
                        }
                    }
                    isDone = true;
                    return kerjaan;
                }
            }
        }
        for(LaundryService service:services){
            if(service instanceof SetrikaService){
                if(!service.isDone()){
                    kerjaan = "Nota "+ this.id + " : " + service.doWork();
                    for(LaundryService checkDoneService : services){
                        if(!checkDoneService.isDone()){
                            return kerjaan;
                        }
                    }
                    isDone = true;
                    return kerjaan;
                }
            }
        }
        for(LaundryService service:services){
            if(service instanceof AntarService){
                if(!service.isDone()){
                    kerjaan = "Nota "+ this.id + " : " + service.doWork();
                    for(LaundryService checkDoneService : services){
                        if(!checkDoneService.isDone()){
                            return kerjaan;
                        }
                    }
                    isDone = true;
                    return kerjaan;
                }
            }
        }
        isDone = true;
        String status = getNotaStatus();
        return status;
    }
    public void toNextDay() {
        //* Mengurangkan sisa hari pengerjaan tiap kali dia next day jika nota belum selesai */
        if(!isDone){
            this.sisaHariPengerjaan -= 1;
        }
    }

    public long calculateHarga(){
        //* Menghitung harga akhir dengan mengalikan base harga dengan berat, lalu ditambahkan dengan harga tiap service yang diambil, lalu dikurangin jika ada kompensasi keterlambatan */
        long hargaFinal = this.baseHarga*berat;
        if(this.sisaHariPengerjaan<0){
            hargaFinal += 2000*this.sisaHariPengerjaan;
        }
        for(LaundryService service : services){
            hargaFinal += service.getHarga(berat);
        }
        if(hargaFinal<0){
            hargaFinal = 0;
        }
        return hargaFinal;
    }

    public String getNotaStatus(){
        //* Mengembalikan status nota apakah sudah selesai apa belum */
        if(isDone){
            return "Nota "+ id +" : Sudah selesai.";
        }
        return "Nota "+ id +" : Belum selesai.";
    }

    public static String getTanggalSelesai(String tanggalDiTerima, int hari){
        //* Mencari tanggal selesai degnan menambahakan hari ke tanggal masuk */
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalTerima = LocalDate.parse(tanggalDiTerima,formatTanggal);
        LocalDate unformatTanggalSelesai = tanggalTerima.plusDays(hari);
        String tanggalSelesai = unformatTanggalSelesai.format(formatTanggal);
        return tanggalSelesai;
    }

    @Override
    public String toString(){
        //* Keterangan nota yang akan dicetak ketika nota di print */
        String nota = "[ID Nota = "+ this.id +"]\nID    : "+ member.getId() +"\nPaket : "+ paket +"\nHarga :\n"+ berat +" kg x "+ baseHarga +" = "+ berat*baseHarga +"\ntanggal terima  : "+ tanggalMasuk +"\ntanggal selesai : "+ getTanggalSelesai(tanggalMasuk, hariSelesai) +"\n--- SERVICE LIST ---";
        for(LaundryService service : services){
            nota = nota + "\n-" + service.getServiceName() + " @ Rp." + service.getHarga(berat);
        }
        nota = nota + "\nHarga Akhir: " + calculateHarga();
        if(this.sisaHariPengerjaan<0){
            nota = nota + " Ada kompensasi keterlambatan "+ this.sisaHariPengerjaan*(-1) + " * 2000 hari";
        }
        return nota;
    }

    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return this.berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getId(){
        return this.id;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
