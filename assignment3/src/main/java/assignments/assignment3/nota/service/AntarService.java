package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean isDone;
    @Override
    public String doWork() {
        // TODO
        isDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        if(berat <=4){
            return 2000;
        }
        return 500*berat;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
