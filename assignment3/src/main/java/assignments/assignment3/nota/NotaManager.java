package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Arrays;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    public static Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //* Menambahkan hari pada perogram dengan 1, dan menjalankan method toNextDay untuk tiap nota */
        cal.add(Calendar.DAY_OF_MONTH,1);
        for(Nota nota:notaList){
            nota.toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        //* Menambahkan nota ke dalam array notaList yang ada pada NotaManager dengan memeanfaatkan method Arrays.copyOf */
        Nota[] tempArray = Arrays.copyOf(notaList, notaList.length+1);
        notaList = Arrays.copyOf(tempArray, tempArray.length);
        notaList[notaList.length-1]=nota;
    }
}
