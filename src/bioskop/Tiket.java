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
        if (status = null
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
}
