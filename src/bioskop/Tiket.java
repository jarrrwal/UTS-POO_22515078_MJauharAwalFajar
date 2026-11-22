package bioskop;

/**
 * Class Tiket mempresentasikan satu pemesanan kursi di bioskop.
 * Tiket menghubungkan sebuah Film dengan Studio, dan membawa data kursi
 * yang dipesan. Status tiket berjalan: MENUNGGU -> DIKONFIRMASI atau DITOLAK.
 */

public class Tiket {
    // Konstanta status (private karena hanya dipakai di dalam class ini)
    private static final String STATUS_MENUNGGU = "MENUNGGU";
    private static final String STATUS_DIKONFIRMASI = "DIKONFIRMASI";
    private static final String STATUS_DITOLAK = "DITOLAK";

    private String kodeTiket;
    private String namaPemesan;
    private Film film;
    private Studio studio;
    private int barisKursi;
    private int kolomKursi;
    private double totalHarga;
    private String status;
    private boolean sudahDibayar;

    /**
     * Konstruktor default: tiket kosong bersttus MENUNGGU dan belum dibayar.
     */
    public Tiket() {
        this.status = STATUS_MENUNGGU;
        this.sudahDibayar = false;
    }

    /**
     * Konstruktor berparameter: membuat tiket berstatus MENUNGGU.
     * Nilai yang punya aturan dikirim lewat setter supaya validsi tetap berjalan.
     */
    public Tiket(String kodeTiket, String namaPemesan, Film film, Studio studio,
                 int barisKursi, int kolomKursi) {
        this.kodeTiket = kodeTiket;
        setNamaPemesan(namaPemesan);
        setFilm(film);
        setStudio(studio);
        setBarisKursi(barisKursi);
        setKolomKursi(kolomKursi);
        this.status = STATUS_MENUNGGU;
        this.sudahDibayar = false;
        hitungTotalHarga();
    }

    // ===== Getter dan Setter =====

    public String getKodeTiket() {
        return kodeTiket;
    }

    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        // Validasi: nama pemesan tidak boleh null atau kosong
        if (namaPemesan == null || namaPemesan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pemesan tidak boleh kosong.");
        }
        this.namaPemesan = namaPemesan.trim();
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        // Validasi: tiket harus mengacu ke sebuah film
        if (film == null) {
            throw new IllegalArgumentException("Film pada tiket tidak boleh kosong.");
        }
        this.film = film;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        // Validasi: tiket harus mengacu ke sebuah studio
        if (studio == null) {
            throw new IllegalArgumentException("Studio pada tiket tidak boleh kosong.");
        }
        this.studio = studio;
    }

    public int getBarisKursi() {
        return barisKursi;
    }

    public void setBarisKursi(int barisKursi) {
        // Validasi: nomor baris harus lebih dari 0
        if (barisKursi <= 0) {
            throw new IllegalArgumentException("Nomor baris kursi harus lebih dari 0.");
        }
        this.barisKursi = barisKursi;
    }

    public int getKolomKursi() {
        return kolomKursi;
    }

    public void setKolomKursi(int kolomKursi) {
        // Validasi: nomor kolom harus lebih dari 0
        if (kolomKursi <= 0) {
            throw new IllegalArgumentException("Nomor kolom kursi harus lebih dari 0.");
        }
        this.kolomKursi = kolomKursi;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        // Validasi: status hanya boleh salah satu dari tiga nilai
        if (status == null
                || !(status.equals(STATUS_MENUNGGU)
                || status.equals(STATUS_DIKONFIRMASI)
                || status.equals(STATUS_DITOLAK))) {
            throw new IllegalArgumentException(
                    "Status harus MENUNGGU, DIKONFIRMASI, atau DITOLAK.");
        }
        this.status = status;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }

    public void setSudahDibayar(boolean sudahDibayar) {
        this.sudahDibayar = sudahDibayar;
    }

    // ===== Method perilaku =====

    /**
     * Menghitung total harga dari harga tiket film, lalu menyimpannya ke totalHarga.
     *
     * @return total harga tiket
     */
    public double hitungTotalHarga() {
        if (film == null) {
            totalHarga = 0;
        } else {
            totalHarga = film.getHargaTiket();
        }
        return totalHarga;
    }

    /**
     * Mengubah baris dan kolom menjadi label kursi, misalnya baris 2 kolom 5 menjadi "B5".
     *
     * @return label kursi, atau "-" jika kursi belum diisi
     */
    public String labelKursi() {
        if (barisKursi <= 0 || kolomKursi <= 0) {
            return "-";
        }
        return (char) ('A' + barisKursi - 1) + String.valueOf(kolomKursi);
    }

    /**
     * Mengonfirmasi tiket: meminta Studio menandai kursi sebagai terisi.
     * Jika kursi sudah terisi atau di luar kapasitas, tiket ditolak.
     *
     * @return true jika tiket berhasil dikonfirmasi, false jika gagal
     */
    public boolean konfirmasi() {
        if (!status.equals(STATUS_MENUNGGU)) {
            System.out.println("Tiket " + kodeTiket
                    + " tidak bisa dikonfirmasi karena statusnya " + status + ".");
            return false;
        }
        if (studio == null || film == null) {
            System.out.println("Tiket " + kodeTiket + " belum memiliki film atau studio.");
            tolak();
            return false;
        }
        // Aturan bisnis kursi (sudah terisi / di luar kapasitas) dijalankan oleh studio
        if (studio.pesanKursi(barisKursi, kolomKursi)) {
            this.status = STATUS_DIKONFIRMASI;
            return true;
        }
        System.out.println("Tiket " + kodeTiket + " ditolak.");
        tolak();
        return false;
    }

    /**
     * Menolak tiket dengan mengubah statusnya menjadi DITOLAK.
     * Tiket yang sudah dikonfirmasi tidak bisa ditolak.
     */
    public void tolak() {
        if (status.equals(STATUS_DIKONFIRMASI)) {
            System.out.println("Tiket " + kodeTiket + " sudah dikonfirmasi, tidak bisa ditolak.");
            return;
        }
        this.status = STATUS_DITOLAK;
    }

    /**
     * Menandai tiket sudah dibayar. Hanya tiket berstatus DIKONFIRMASI yang bisa dibayar.
     */
    public void bayar() {
        if (!status.equals(STATUS_DIKONFIRMASI)) {
            System.out.println("Tiket " + kodeTiket
                    + " belum dikonfirmasi, tidak bisa dibayar.");
            return;
        }
        if (sudahDibayar) {
            System.out.println("Tiket " + kodeTiket + " sudah dibayar sebelumnya.");
            return;
        }
        this.sudahDibayar = true;
    }

    /**
     * Menampilkan data tiket di layar.
     */
    public void tampilkanInfo() {
        System.out.println("Kode Tiket  : " + kodeTiket);
        System.out.println("Pemesan     : " + namaPemesan);
        System.out.println("Film        : " + (film != null ? film.getJudul() : "-"));
        System.out.println("Studio      : " + (studio != null ? studio.getNamaStudio() : "-"));
        System.out.println("Kursi       : " + labelKursi());
        System.out.println("Total Harga : Rp" + totalHarga);
        System.out.println("Status      : " + status);
        System.out.println("Pembayaran  : " + (sudahDibayar ? "Sudah dibayar" : "Belum dibayar"));
    }

    /**
     * Memeriksa apakah tiket sudah dikonfirmasi.
     * Dipakai class lain (misalnya Bioskop) tanpa membuka konstanta status.
     *
     * @return true jika statusnya DIKONFIRMASI
     */
    public boolean isDikonfirmasi() {
        return status.equals(STATUS_DIKONFIRMASI);
    }
}
