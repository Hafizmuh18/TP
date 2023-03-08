package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;
    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp);
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    // Getter methods
    public String getNama() {
        return nama;
    }
    public String getNoHp() {
        return noHp;
    }
    public String getId() {
        return id;
    }
    public int getBonusCounter() {
        return bonusCounter;
    }
    // Setter methods
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setBonusCounter(int bonusCounter) {
        this.bonusCounter = bonusCounter;
    }
    public boolean getDiskon(){
        this.bonusCounter++;
        if(this.bonusCounter==3){
            setBonusCounter(0);
            return true;
        }
        return false;
    }
    
}
