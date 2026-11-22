package bioskop;

import java.util.ArrayList;

/**
 * Class AntrianTiket adalah ADT Queue (FIFO) buatan sendiri untuk menampung
 * tiket yang menunggu diproses. Tiket yang masuk lebih dulu keluar lebih dulu.
 * Kapasitas antrean dibatasi, dan kondisi penuh atau kosong ditangani
 * dengan pesan kesalahan tanpa menghentikan program.
 */
public class AntrianTiket {
    // Kapasitas bawaan untuk konstruktor default
    private static final int KAPASITAS_BAWAAN = 10;

    private ArrayList<Tiket> daftarTiket;
    private int kapasitas;

    /**
     * Konstruktor default: antrean kosong dengan kapasitas bawaan (10).
     */
    public AntrianTiket() {
        this(KAPASITAS_BAWAAN);
    }

    /**
     * Konstruktor berparameter: antrean kosong dengan kapasitas tertentu.
     * Kapasitas dikirim lewat setter supaya validasi tetap berjalan.
     *
     * @param kapasitas jumlah maksimal tiket dalam antrean
     */
    public AntrianTiket(int kapasitas) {
        this.daftarTiket = new ArrayList<>();
        setKapasitas(kapasitas);
    }

    // ===== Getter dan Setter =====

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        // Validasi: kapasitas harus lebih dari 0
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas antrean harus lebih dari 0.");
        }
        // Validasi: kapasitas baru tidak boleh lebih kecil dari jumlah tiket yang sudah antre
        if (kapasitas < daftarTiket.size()) {
            throw new IllegalArgumentException(
                    "Kapasitas tidak boleh lebih kecil dari jumlah tiket yang sedang antre ("
                            + daftarTiket.size() + ").");
        }
        this.kapasitas = kapasitas;
    }

    // ===== Operasi ADT Queue =====

    /**
     * Memasukkan tiket ke belakang antrean.
     * Prasyarat: tiket tidak null dan antrean belum penuh.
     *
     * @param tiket tiket yang akan masuk antrean
     * @return true jika berhasil, false jika antrean penuh atau tiket null
     */
    public boolean enqueue(Tiket tiket) {
        if (tiket == null) {
            System.out.println("Gagal: tiket tidak boleh kosong.");
            return false;
        }
        if (isFull()) {
            System.out.println("Gagal: antrean penuh (kapasitas " + kapasitas + ").");
            return false;
        }
        daftarTiket.add(tiket);
        return true;
    }

    /**
     * Mengeluarkan tiket terdepan dari antrean.
     * Prasyarat: antrean tidak kosong.
     *
     * @return tiket terdepan, atau null jika antrean kosong
     */
    public Tiket dequeue() {
        if (isEmpty()) {
            System.out.println("Gagal: antrean kosong, tidak ada tiket untuk diproses.");
            return null;
        }
        return daftarTiket.remove(0);
    }

    /**
     * Melihat tiket terdepan tanpa mengeluarkannya.
     * Prasyarat: antrean tidak kosong.
     *
     * @return tiket terdepan, atau null jika antrean kosong
     */
    public Tiket front() {
        if (isEmpty()) {
            System.out.println("Gagal: antrean kosong, tidak ada tiket di depan.");
            return null;
        }
        return daftarTiket.get(0);
    }

    /**
     * Memeriksa apakah antrean kosong.
     *
     * @return true jika tidak ada tiket dalam antrean
     */
    public boolean isEmpty() {
        return daftarTiket.isEmpty();
    }

    /**
     * Memeriksa apakah antrean penuh.
     *
     * @return true jika jumlah tiket sudah sama dengan kapasitas
     */
    public boolean isFull() {
        return daftarTiket.size() >= kapasitas;
    }

    /**
     * Menghitung jumlah tiket dalam antrean.
     *
     * @return jumlah tiket yang sedang antre
     */
    public int size() {
        return daftarTiket.size();
    }

    /**
     * Menampilkan isi antrean dari depan ke belakang.
     */
    public void tampilkanIsi() {
        if (isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }
        System.out.println("Isi antrean (" + size() + "/" + kapasitas + "), dari depan ke belakang:");
        for (int i = 0; i < daftarTiket.size(); i++) {
            Tiket t = daftarTiket.get(i);
            System.out.println((i + 1) + ". " + t.getKodeTiket()
                    + " | " + t.getNamaPemesan()
                    + " | " + t.getFilm().getJudul()
                    + " | kursi " + t.labelKursi()
                    + " | " + t.getStatus());
        }
    }
}
