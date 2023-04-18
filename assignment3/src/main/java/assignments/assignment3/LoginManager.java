package assignments.assignment3;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        return null;
    }

    public String generateId(String inpNama, String nomorHp){
        String[] listNama = inpNama.split(" ");
        String nama = listNama[0].toUpperCase();
        int checkSum = 0;
        String[] listChar = nama.split(""); 
        String[] listNumber = (nomorHp).split(""); 
        for(String letter : listChar){
            boolean huruf = true;
            if(huruf){
                char character = letter.charAt(0);
                int asciiChar = character;
                if(asciiChar >= 65 && asciiChar <= 90){
                    checkSum += asciiChar - 64;
                }else if(Character.isDigit(character)){
                    checkSum += character;
                }else{
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
            twoDigit = "0"+strCheckSum;
        }else if(strCheckSum.length() >2){
            twoDigit = strCheckSum.substring(strCheckSum.length() - 2);
        }else{
            twoDigit = strCheckSum;
        }
        String id = nama+"-"+nomorHp+"-"+twoDigit;
        return id;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {
        // TODO
        String id = this.generateId(nama, noHp);
        Member newMember = new Member(nama, id, password);
        if(!memberSystem.isMemberExist(id)){
            memberSystem.addMember(newMember);
            return newMember;
        }
        return null;
    }
}