package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    //* Attribute-attribute di dalam class member */
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;
    public Member(String nama, String noHp) {
        //* Constructor untuk class member yang mengambil 2 parameter yaitu nama dan nohp */
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp); //* Mengenerate id dengan menggunakan method dari TP 1 */
    }

    //* Getter methods */
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
    //* Setter methods */
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
    //* Method untuk memastikan apakah member mendapatkan diskon atau tidak */
    public boolean getDiskon(){
        this.bonusCounter++;
        if(this.bonusCounter==3){
            setBonusCounter(0);
            return true;
        }
        return false;
    }
    
}
