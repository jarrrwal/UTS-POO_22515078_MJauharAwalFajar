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

    // ===== Method perilaku =====
    /**
     * Menghitung kapasitas studio
     *
     * @return jumlah baris x jumlah kolom
     */
    public int hitungKapasitas() {
        return jumlahBaris * jumlahKolom;
    }

    /**
     * Memeriksa apakah sebuah kursi masih kosong.
     * Nomor baris dan kolom dimulai dari 1.
     *
     * @return true jika posisi valid dan kursi masih kosong.
     */
    public boolean cekKursiTersedia(int baris, int kolom) {
        if (!posisiValid(baris, kolom)) {
            return false;
        }
        return !kursiTerisi[baris - 1][kolom - 1];
    }

    /**
     * Memesan kursi: menandainya sebagai terisi.
     * Aturan bisnis: kursi yang sudah terisi tidak bisa dipesan lagi,
     * dan posisi harus berada di dalam kapasitas studio.
     *
     * @return true jika berhasil, false jika gagal (pesan kesalah dicetak)
     */
    public boolean pesanKursi(int baris, int kolom) {
        if (!posisiValid(baris, kolom)) {
            System.out.println("Gagal: kursi baris " + baris + ", kolom " + kolom
                    + " di luar kapasitas studio (" + jumlahBaris + " baris x "
                    + jumlahKolom + " kolom).");
            return false;
        }
        if (kursiTerisi[baris - 1][kolom - 1]) {
            System.out.println("Gagal: kursi " + labelKursi(baris, kolom) + " sudah terisi.");
            return false;
        }
        kursiTerisi[baris - 1][kolom - 1] = true;
        return true;
    }

    /**
     * Menghitung jumlah kursi yang masih kosong.
     */
    public int hitungKursiKosong() {
        if (kursiTerisi == null) {
            return 0;
        }
        int kosong = 0;
        for (int i = 0; i < jumlahBaris; i++) {
            for (int j = 0; j < jumlahKolom; j++) {
                if (!kursiTerisi[i][j]) {
                    kosong++;
                }
            }
        }
        return kosong;
    }

    /**
     * Menampilkan denah kursi di layar. Baris diberi huruf (1=A, 2=B, dst)
     */
    public void tampilkanDenahKursi() {
        if (kursiTerisi == null) {
            System.out.println("Denah kursi belum tersedia.");
            return;
        }
        System.out.println("Denah " + namaStudio + " ([ ] = kosong, [X] = terisi)");
        System.out.println("            LAYAR");
        System.out.print("   ");
        for (int j = 1; j <= jumlahKolom; j++) {
            System.out.printf("%3d ", j);
        }
        System.out.println();
        for (int i = 0; i < jumlahBaris; i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < jumlahKolom; j++) {
                System.out.print(kursiTerisi[i][j] ? "[X] " : "[ ] ");
            }
            System.out.println();
        }
    }

    // ===== Method pembantu (private) =====
    /** Membuat denah kursi baru (semua kosong) jika ukuran sudah lengkap. */
    private void buatUlangDenah() {
        if (jumlahBaris > 0 && jumlahKolom > 0) {
            kursiTerisi = new boolean[jumlahBaris][jumlahKolom];
        }
    }

    /** Memeriksa apakah baris dan kolom berada di dalam kapasitas studio. */
    private boolean posisiValid(int baris, int kolom) {
        return kursiTerisi != null
                && baris >= 1 && baris <= jumlahBaris
                && kolom >= 1 && kolom <= jumlahKolom;
    }

    /** Mengubah baris dan kolom menjadi label, misalnya baris 2 kolom 5 menjadi "B5". */
    private String labelKursi(int baris, int kolom) {
        return (char) ('A' + baris - 1) + String.valueOf(kolom);
    }
}
