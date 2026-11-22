package bioskop;

/**
 * Class Studio mempresentasikan satu ruang studio bioskop.
 * Menyimpan denah kursi (true = terisi, false = kosong) dan menjalankan aturan bisnis
 * kursi yang sudah terisi tidak bisa dipesan lagi, dan jumlah kursi dibatasi kapasitas studio.
 */

public class Studio {
    private String kodeStudio;
    private String namaStudio;
    private String jenisStudio;
    private int jumlahBaris;
    private int jumlahKolom;
    private boolean[][] kursiTerisi;
    private Film filmDiputar;
    private boolean aktif;

    /**
     * Konstruktor default: studio kosong,denah kursi dibuat setelah ukuran diisi.
     */
    public Studio() {
        this.aktif = true;
    }

    /**
     * Konstruktor berparameter: membuat studio beserta denah kursinya.
     * Nama dan ukuran dikirim lewat setter supaya validasi tetap berjalan.
     */
    public Studio(String kodeStudio, String namaStudio, String jenisStudio, int jumlahBaris, int jumlahKolom) {
        this.kodeStudio = kodeStudio;
        this.jenisStudio = jenisStudio;
        setNamaStudio(namaStudio);
        setJumlahBaris(jumlahBaris);
        setJumlahKolom(jumlahKolom);
        this.aktif = true;
    }

    // ===== Getter dan Setter =====

    public String getKodeStudio() {
        return kodeStudio;
    }

    public void setKodeStudio(String kodeStudio) {
        this.kodeStudio = kodeStudio;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        // Validasi: nama tidak boleh null atau kosong
        if (namaStudio == null || namaStudio.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama studio tidak boleh kosong.");
        }
        this.namaStudio = namaStudio.trim();
    }

    public String getJenisStudio() {
        return jenisStudio;
    }

    public void setJenisStudio(String jenisStudio) {
        this.jenisStudio = jenisStudio;
    }

    public int getJumlahBaris() {
        return jumlahBaris;
    }

    public void setJumlahBaris(int jumlahBaris) {
        // Validasi: 1 sampai 26 baris (label bisa memakai huruf A-Z)
        if (jumlahBaris <= 0 || jumlahBaris > 26) {
            throw new IllegalArgumentException("Jumlah baris harus antara 1 sampai 26.");
        }
        this.jumlahBaris = jumlahBaris;
        buatUlangDenah();
    }

    public Film getFilmDiputar() {
        return filmDiputar;
    }

    public void setFilmDiputar(Film filmDiputar) {
        this.filmDiputar = filmDiputar;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }
}
