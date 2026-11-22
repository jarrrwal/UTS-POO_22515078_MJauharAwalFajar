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
}
