package MaintenanceTravel.entity;

public class Kendaraan {
    private String noPol;
    private String sopir;
    private String tanggal;
    private String keterangan;

    public Kendaraan(String noPol, String sopir, String tanggal, String keterangan) {
        this.noPol = noPol;
        this.sopir = sopir;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }

    public String getNoPol() {
        return noPol;
    }

    public String getSopir() {
        return sopir;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    @Override
    public String toString() {
        return "NoPol: " + noPol + ", Sopir: " + sopir +
               ", Tanggal: " + tanggal + ", Keterangan: " + keterangan;
    }
}


















