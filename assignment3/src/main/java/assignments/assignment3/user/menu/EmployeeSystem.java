package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        //* Method jika user employee berhasil login, maka akan tersedia 3 pilihan */
        if(choice == 1){
            //* Mengerjakan tiap nota yang ada di notaList NotaManager */
            System.out.println("Stand back! "+ loginMember.getNama() +" beginning to nyuci!");
            for(Nota nota : NotaManager.notaList){
                System.out.println(nota.kerjakan());
            }
            return false;
        }else if(choice == 2){
            //* Melakukan pengecekan status untuk tiap nota yang ada pada notaList pada NotaManager */
            for(Nota nota : NotaManager.notaList){
                System.out.println(nota.getNotaStatus());
            }
            return false;
        }else if(choice == 3){
            //* Jika user employee logout, maka logout sama dengan true */
            return true;
        }
        return false;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    public void addEmployee(Member[] arrMember) {
        //* Menambahkan member ke dalam array memberList */
        for(Member member:arrMember){
            Member[] tempArray = Arrays.copyOf(memberList, memberList.length+1);
            memberList = Arrays.copyOf(tempArray, tempArray.length);
            memberList[memberList.length-1]=member;
        }
    }
}