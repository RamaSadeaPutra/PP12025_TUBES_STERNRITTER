package MaintenanceTravel.services.util;

import java.util.Scanner;
import MaintenanceTravel.entity.StrukturList;
import MaintenanceTravel.entity.Kendaraan;

public class Main {
    public static void main(String[] args) {
        StrukturList list = new StrukturList();
        list.loadFromFile("docs/data_kendaraan.txt");
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Tambah Data Kendaraan");
            System.out.println("2. Hapus Data Kendaraan");
            System.out.println("3. Cari Data Kendaraan");
            System.out.println("4. Tampilkan Semua Data");
            System.out.println("5. Simpan ke File");
            System.out.println("6. Ubah Data di File");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    menuTambah(list, scanner);
                    break;
                case 2:
                    menuHapus(list, scanner);
                    break;
                case 3:
                    menuCari(list, scanner);
                    break;
                case 4:
                    menuTampil(list, scanner);
                    break;
                case 5:
                    list.saveToFile("docs/data_kendaraan.txt");
                    break;
                case 6:
                System.out.print("Masukkan NoPol kendaraan yang ingin diubah: ");
                String noPolTarget = scanner.nextLine();
                System.out.print("Masukkan Nama Sopir baru: ");
                String namaSopir = scanner.nextLine();
                System.out.print("Masukkan Tanggal Maintenance baru (YYYY-MM-DD): ");
                String tanggalMaintenance = scanner.nextLine();
                System.out.print("Masukkan Keterangan baru: ");
                String keterangan = scanner.nextLine();
                
                // NoPol tetap pakai noPolTarget
                Kendaraan baru = new Kendaraan(noPolTarget, namaSopir, tanggalMaintenance, keterangan);
                list.editInFile("docs/data_kendaraan.txt", noPolTarget, baru);
                list.loadFromFile("docs/data_kendaraan.txt");
                break;
                
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);

        scanner.close();
    }

 
  private static void menuTambah(StrukturList list, Scanner scanner) {
    boolean lanjut = true;
    while (lanjut) {
        System.out.println("\n--- Sub Menu Tambah Data ---");
        // Input data kendaraan dulu
        Kendaraan dataBaru = inputKendaraan(scanner);

        int pilih;
        do {
            System.out.println("Pilih posisi penambahan:");
            System.out.println("1. Tambah di Awal");
            System.out.println("2. Tambah di Tengah");
            System.out.println("3. Tambah di Akhir");
            System.out.println("0. Batal tambah (kembali ke Menu Tambah)");
            System.out.print("Pilih menu: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    list.addHead(dataBaru);
                    System.out.println("Kendaraan ditambahkan di awal.");
                    break;
                case 2:
                    System.out.print("Masukkan posisi (mulai dari 1): ");
                    int posisi = scanner.nextInt();
                    scanner.nextLine();
                    if (posisi < 1 || posisi > list.size() + 1) {
                        System.out.println("Posisi tidak valid. Harus antara 1 dan " + (list.size() + 1));
                        continue;
                    }
                    list.addMid(dataBaru, posisi);
                    System.out.println("Kendaraan ditambahkan di posisi " + posisi);
                    break;
                case 3:
                    list.addTail(dataBaru);
                    System.out.println("Kendaraan ditambahkan di akhir.");
                    break;
                case 0:
                    System.out.println("Batal menambah data.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    continue;
            }
            break; // keluar dari do-while setelah penambahan/batal
        } while (true);

        // Setelah penambahan, tawarkan pilihan lagi
        int ulang;
        do {
            System.out.println("\n1. Tambah data lagi");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            ulang = scanner.nextInt();
            scanner.nextLine();
            if (ulang == 1) {
                break; // lanjut tambah data
            } else if (ulang == 0) {
                lanjut = false; // keluar dari menuTambah
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        } while (ulang != 0 && ulang != 1);
    }
}
// ...existing code...

    private static void menuHapus(StrukturList list, Scanner scanner) {
        int pilih;
        do {
            System.out.println("\n--- Sub Menu Hapus Data ---");
            System.out.println("1. Hapus Data di Awal");
            System.out.println("2. Hapus Data di Tengah");
            System.out.println("3. Hapus Data di Akhir");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    list.removeHead();
                    break;
                case 2:
                    int ukuran = list.size();
                    if (ukuran < 3) {
                        System.out.println("Tidak ada data yang bisa dihapus di tengah. Minimal harus ada 3 data.");
                        break;
                    }
                    System.out.print("Masukkan posisi data yang ingin dihapus (2 hingga " + (ukuran - 1) + "): ");
                    int pos = scanner.nextInt();
                    scanner.nextLine();
                    if (pos <= 1 || pos >= ukuran) {
                        System.out.println("Posisi tidak valid untuk penghapusan tengah.");
                    } else {
                        list.removeMid(pos);
                        System.out.println("Data pada posisi " + pos + " berhasil dihapus.");
                    }
                    break;
                case 3:
                    list.removeTail();
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 0);
    }

    private static void menuCari(StrukturList list, Scanner scanner) {
        int pilih;
        do {
            System.out.println("\n--- Sub Menu Cari Data ---");
            System.out.println("1. Cari berdasarkan NoPol");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Masukkan NoPol yang dicari: ");
                    String cari = scanner.nextLine();
                    list.find(cari);
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 0);
    }

    private static void menuTampil(StrukturList list, Scanner scanner) {
        int pilih;
        do {
            System.out.println("\n--- Sub Menu Tampilkan Data ---");
            System.out.println("1. Tampilkan Semua Kendaraan");
            System.out.println("2. Tampilkan Jumlah Data");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    System.out.println("\nDaftar Kendaraan:");
                    list.displayElement();
                    break;
                case 2:
                    System.out.println("Jumlah kendaraan: " + list.size());
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 0);
    }

    private static Kendaraan inputKendaraan(Scanner scanner) {
        System.out.print("Masukkan Nomor Polisi: ");
        String noPol = scanner.nextLine();
        System.out.print("Masukkan Nama Sopir: ");
        String namaSopir = scanner.nextLine();
        System.out.print("Masukkan Tanggal Maintenance (YYYY-MM-DD): ");
        String tanggalMaintenance = scanner.nextLine();
        System.out.print("Masukkan Keterangan: ");
        String keterangan = scanner.nextLine();
        return new Kendaraan(noPol, namaSopir, tanggalMaintenance, keterangan);
    }
}

