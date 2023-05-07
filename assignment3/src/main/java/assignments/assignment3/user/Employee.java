package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
        employeeCount++;
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        //* Mmembuat id untuk user employee sesuai dengan ketentuan */
        String id= "";
        String[] arrNama = nama.split(" ");
        String namaDepan = arrNama[0].toUpperCase();
        id = namaDepan + "-" + employeeCount;
        return id;
    }
}
