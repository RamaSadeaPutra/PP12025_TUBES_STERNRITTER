package MaintenanceTravel.entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StrukturList {
    private Node HEAD;

    public StrukturList() {
        this.HEAD = null;
    }

    public void addHead(Kendaraan data) {
        Node newNode = new Node(data);
        newNode.setNext(HEAD);
        HEAD = newNode;
    }

    public void addTail(Kendaraan data) {
        Node newNode = new Node(data);
        if (HEAD == null) {
            HEAD = newNode;
        } else {
            Node curNode = HEAD;
            while (curNode.getNext() != null) {
                curNode = curNode.getNext();
            }
            curNode.setNext(newNode);
        }
    }

    public void addMid(Kendaraan data, int posisi) {
        if (posisi <= 1 || HEAD == null) {
            addHead(data);
        } else {
            Node newNode = new Node(data);
            Node curNode = HEAD;
            int index = 1;

            
            while (curNode.getNext() != null && index < posisi - 1) {
                curNode = curNode.getNext();
                index++;
            }
            
            
            if (curNode.getNext() == null && index < posisi -1) { 
                addTail(data); 
            } else {
                newNode.setNext(curNode.getNext());
                curNode.setNext(newNode);
            }
        }
    }


    public void removeHead() {
        if (HEAD == null) {
            System.out.println("List kosong.");
        } else {
            Node temp = HEAD;
            HEAD = HEAD.getNext();
            dispose(temp);
            System.out.println("Data di head berhasil dihapus.");
        }
    }

    public void removeTail() {
        if (HEAD == null) {
            System.out.println("List kosong.");
        } else if (HEAD.getNext() == null) { 
            dispose(HEAD);
            HEAD = null;
            System.out.println("Satu-satunya elemen berhasil dihapus.");
        } else {
            Node curNode = HEAD;
            Node prevNode = null;

            while (curNode.getNext() != null) {
                prevNode = curNode;
                curNode = curNode.getNext();
            }

            prevNode.setNext(null);
            dispose(curNode);
            System.out.println("Data di tail berhasil dihapus.");
        }
    }

    public void removeMid(int posisi) {
        if (HEAD == null) {
            System.out.println("List kosong.");
        } else if (posisi <= 1) {
            removeHead();
        } else {
            Node curNode = HEAD;
            Node prevNode = null;
            int index = 1;

            while (curNode != null && index < posisi) {
                prevNode = curNode;
                curNode = curNode.getNext();
                index++;
            }

            if (curNode == null) {
                System.out.println("Posisi melebihi panjang list atau tidak valid.");
            } else {
                prevNode.setNext(curNode.getNext());
                dispose(curNode);
                System.out.println("Data di posisi " + posisi + " berhasil dihapus.");
            }
        }
    }


    public int size() {
        int count = 0;
        Node curNode = HEAD;
        while (curNode != null) {
            count++;
            curNode = curNode.getNext();
        }
        return count;
    }


    public boolean find(String noPol) {
        Node curNode = HEAD;
        while (curNode != null) {
            if (curNode.getData().getNoPol().equalsIgnoreCase(noPol)) {
                System.out.println("Data ditemukan:\n" + curNode.getData());
                return true;
            }
            curNode = curNode.getNext();
        }
        System.out.println("Data dengan NoPol '" + noPol + "' tidak ditemukan.");
        return false;
    }

    public void clear(boolean showMessage) {
        while (HEAD != null) {
            Node temp = HEAD;
            HEAD = HEAD.getNext();
            dispose(temp);
        }
        if (showMessage) {
            System.out.println("Semua data berhasil dihapus.");
        }
    }

    public void clear() {
        clear(true);
    }

    private void dispose(Node node) {
        node.setNext(null);
        node = null; 
    }

    public void displayElement() {
        if (HEAD == null) {
            System.out.println("List kosong.");
            return;
        }

        Node curNode = HEAD;
        while (curNode != null) {
            System.out.println(curNode.getData());
            curNode = curNode.getNext();
        }
    }

    //  Untuk menyimpan data di file txt
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Node curNode = HEAD;
            while (curNode != null) {
                Kendaraan k = curNode.getData();
                writer.write(k.getNoPol() + ";" + k.getSopir() + ";" +
                             k.getTanggal() + ";" + k.getKeterangan());
                writer.newLine();
                curNode = curNode.getNext();
            }
            System.out.println("Data berhasil disimpan ke file: " + filename);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
    
    // Untuk mengubah isi data dalam file txt
    public void editInFile(String filename, String noPolTarget, Kendaraan dataBaru) {
        File inputFile = new File(filename);
        if (!inputFile.exists()) {
            System.out.println("File tidak ditemukan: " + filename);
            return;
        }
        // Buat file temp di folder yang sama
        File tempFile = new File(inputFile.getParent(), "temp_" + inputFile.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                if (parts.length == 4 && parts[0].equalsIgnoreCase(noPolTarget)) {
                    writer.write(dataBaru.getNoPol() + ";" + dataBaru.getSopir() + ";" +
                                 dataBaru.getTanggal() + ";" + dataBaru.getKeterangan());
                    found = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            if (!found) {
                System.out.println("Data dengan NoPol " + noPolTarget + " tidak ditemukan di file.");
            } else {
                System.out.println("Data berhasil diubah.");
            }

        } catch (IOException e) {
            System.err.println("Gagal mengedit file: " + e.getMessage());
            return;
        }

        // Ganti file lama dengan file baru
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Gagal mengganti file lama dengan file baru.");
            }
        } else {
            System.err.println("Gagal menghapus file lama.");
        }
    }

    public void loadFromFile(String filename) {
        File inputFile = new File(filename);
        if (!inputFile.exists()) {
            // File belum ada, tidak perlu load apa-apa
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            clear(false); // Pastikan list kosong sebelum load, tanpa pesan
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Kendaraan k = new Kendaraan(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim()
                    );
                    addTail(k);
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }
    }
}