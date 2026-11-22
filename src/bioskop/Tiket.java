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
}
