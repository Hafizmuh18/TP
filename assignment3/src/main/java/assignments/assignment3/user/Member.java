package assignments.assignment3.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import assignments.assignment3.nota.Nota;
public class Member {
    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[0];

    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Method otentikasi member dengan ID dan password yang diberikan.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password);
    }

    /**
     * Menambahkan nota baru ke NotaList instance member.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public void addNota(Nota nota) {
        //* Menambahkan nota ke dalam array notaList yang ada di state member dengan memanfaatkan arrays copy of */
        Nota[] tempArray = Arrays.copyOf(notaList, notaList.length+1);
        notaList = Arrays.copyOf(tempArray, tempArray.length);
        notaList[notaList.length-1]=nota;
    }

    /**
     * Method otentikasi member dengan password yang diberikan.
     *
     * @param password -> sandi password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    protected boolean authenticate(String password) {
        //* Memastikan user yang mencoba login ke sebuah akun memasukan password yang benar */
        if(this.password.equals(password)){
            return true;
        }
        return false;
    }

    // Dibawah ini adalah getter

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }
}