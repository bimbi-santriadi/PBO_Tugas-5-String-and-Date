import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat; // Import DecimalFormat

class User {
    private String username = "admin";  // Username
    private String password = "12345";   // Password

    // Metode untuk memvalidasi login
    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }
}

class Barang {
    private String noFaktur;  // Nomor faktur pembelian
    private String kodeBarang; // Kode unik barang
    private String namaBarang; // Nama barang
    private double totalHarga; // Total harga barang
    private int jumlahBeli;    // Jumlah barang yang dibeli

    // Konstruktor untuk menginisialisasi atribut Barang
    public Barang(String noFaktur, String kodeBarang, String namaBarang, double totalHarga, int jumlahBeli) {
        this.noFaktur = noFaktur;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.totalHarga = totalHarga;
        this.jumlahBeli = jumlahBeli;
    }

    // Metode untuk menampilkan detail pembelian
    public void displayDetail() {
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Format untuk total harga
        System.out.println("\nDetail Pembelian:");
        System.out.println("No. Faktur\t\t: " + noFaktur);
        System.out.println("Kode Barang\t\t: " + kodeBarang);
        System.out.println("Nama Barang\t\t: " + namaBarang);
        System.out.println("Total Harga\t\t: " + df.format(totalHarga)); // Menampilkan total harga dengan format
        System.out.println("Jumlah Beli\t\t: " + jumlahBeli);
        
        // Format untuk menampilkan tanggal dan waktu
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now(); // Mendapatkan waktu saat ini
        System.out.println("Tanggal dan Waktu\t: " + dtf.format(now)); // Menampilkan tanggal dan waktu yang diformat
    }
}

public class Supermarket {
    public static void main(String[] args) {
        // Menggunakan try-with-resources untuk menutup scanner secara otomatis
        try (Scanner scanner = new Scanner(System.in)) {
            User user = new User(); // Membuat objek User untuk login

            // Menampilkan bagian login
            System.out.println("+------------------------+");
            System.out.println("|        Log in          |");
            System.out.println("+------------------------+");
            System.out.print("Username\t: ");
            String inputUsername = scanner.nextLine(); // Input username
            System.out.print("Password\t: ");
            String inputPassword = scanner.nextLine(); // Input password

            // Validasi login
            if (!user.login(inputUsername, inputPassword)) {
                System.out.println("Login berhasil atau login gagal silakan diulang.");
                return; // Keluar jika login gagal
            }

            System.out.println("+----------------------------------------------+");
            System.out.println("| Selamat Datang di Supermarket Central Gadget |");
            System.out.println("+----------------------------------------------+");

            // Mengumpulkan informasi barang
            System.out.println("Tanggal dan Waktu\t: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            System.out.println("+----------------------------------------------+");
            System.out.print("No. Faktur\t\t: ");
            String noFaktur = scanner.nextLine();
            System.out.print("Kode Barang\t\t: ");
            String kodeBarang = scanner.nextLine();
            System.out.print("Nama Barang\t\t: ");
            String namaBarang = scanner.nextLine();
            System.out.print("Harga per Barang\t: ");
            
            // Mengambil input harga per barang dan mengonversinya
            double hargaPerBarang = 0;
            while (true) {
                try {
                    String inputHargaPerBarang = scanner.nextLine().replace(".", "").replace(",", ".");
                    hargaPerBarang = Double.parseDouble(inputHargaPerBarang); // Mengonversi input ke double
                    break; // Keluar dari loop jika berhasil
                } catch (NumberFormatException e) {
                    System.out.print("Format tidak valid. Masukkan Harga per Barang : ");
                }
            }

            System.out.print("Jumlah Beli\t\t: ");
            int jumlahBeli = scanner.nextInt();

            // Menghitung total harga
            double totalHarga = hargaPerBarang * jumlahBeli;

            // Membuat objek Barang dengan data yang diinput
            Barang barang = new Barang(noFaktur, kodeBarang, namaBarang, totalHarga, jumlahBeli);
            barang.displayDetail(); // Menampilkan detail pembelian

            System.out.println("+----------------------------------------------+");
            System.out.println("Kasir\t\t\t: Mentari");
            System.out.println("Terima kasih telah berbelanja di Supermarket Central Gadget!");
            System.out.println("+----------------------------------------------+");
        } // Scanner akan ditutup secara otomatis di sini
    }
}