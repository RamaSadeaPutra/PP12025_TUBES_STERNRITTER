package MaintenanceTravel;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        StrukturList list = new StrukturList();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Tambah Kendaraan di Awal");
            System.out.println("2. Tambah Kendaraan di Tengah");
            System.out.println("3. Tambah Kendaraan di Akhir");
            System.out.println("4. Tampilkan Semua Kendaraan");
            System.out.println("5. Cari Kendaraan");
            System.out.println("6. Hapus Data di Awal");
            System.out.println("7. Hapus Data di Tengah");
            System.out.println("8. Hapus Data di Akhir");
            System.out.println("9. Tampilkan Jumlah Data");
            System.out.println("10. Simpan Data ke File");
            System.out.println("11. Ubah Data di File");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1:
                    Kendaraan awal = inputKendaraan(scanner);
                    list.addHead(awal);
                    System.out.println("Kendaraan ditambahkan di awal.");
                    break;
                    
                case 2:
                    Kendaraan tengah = inputKendaraan(scanner);
                    System.out.print("Masukkan posisi (mulai dari 1): ");
                    int posisi = scanner.nextInt();
                    scanner.nextLine(); // konsumsi newline

                    if (posisi < 1 || posisi > list.size() + 1) {
                        System.out.println("Posisi tidak valid. Harus antara 1 dan " + (list.size() + 1));
                        break;
                    }

                    list.addMid(tengah, posisi);
                    System.out.println("Kendaraan ditambahkan di posisi " + posisi);
                    break;

                case 3:
                    Kendaraan akhir = inputKendaraan(scanner);
                    list.addTail(akhir);
                    System.out.println("Kendaraan ditambahkan di akhir.");
                    break;
                    
                case 4:
                    System.out.println("\nDaftar Kendaraan:");
                    list.displayElement();
                    break;
                    
                case 5:
                    System.out.print("Masukkan NoPol yang dicari: ");
                    String cari = scanner.nextLine();
                    list.find(cari);
                    break;

                case 6:
                    list.removeHead();
                    break;

                case 7:
                    int ukuran = list.size();
                    if (ukuran < 3) {
                        System.out.println("Tidak ada data yang bisa dihapus di tengah. Minimal harus ada 3 data.");
                        break;
                    }

                    System.out.print("Masukkan posisi data yang ingin dihapus (2 hingga " + (ukuran - 1) + "): ");
                    int pos = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    if (pos <= 1 || pos >= ukuran) {
                        System.out.println("Posisi tidak valid untuk penghapusan tengah.");
                    } else {
                        list.removeMid(pos);
                        System.out.println("Data pada posisi " + pos + " berhasil dihapus.");
                    }
                    break;

                case 8:
                    list.removeTail();
                    break;

                case 9:
                    System.out.println("Jumlah kendaraan: " + list.size());
                    break;
                    
                case 10:
                    list.saveToFile("data_kendaraan.txt");
                    break;

                case 11:
                    System.out.print("Masukkan NoPol kendaraan yang ingin diubah: ");
                    String noPolTarget = scanner.nextLine();
                    System.out.println("Masukkan data baru:");
                    Kendaraan baru = inputKendaraan(scanner);
                    list.editInFile("data_kendaraan.txt", noPolTarget, baru);
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

