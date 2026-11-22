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
}
