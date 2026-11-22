package bioskop;

import java.util.ArrayList;

/**
 * Class Bioskop adalah pengelola seluruh data: daftar film, studio, dan tiket,
 * serta antrean pemrosesan tiket (ADT Queue). Bioskop menjalankan alur:
 * buat tiket -> masuk antrean -> diproses (dikonfirmasi atau ditolak) -> pendapatan.
 */
public class Bioskop {
    // Nilai bawaan untuk konstruktor default (private karena hanya dipakai di sini)
    private static final int JUMLAH_STUDIO_MAKS_BAWAAN = 5;

    private String namaBioskop;
    private String alamat;
    private int jumlahStudioMaks;
    private ArrayList<Film> daftarFilm;
    private ArrayList<Studio> daftarStudio;
    private ArrayList<Tiket> daftarTiket;
    private AntrianTiket antrian;
    private boolean buka;

    /**
     * Konstruktor default: bioskop dengan data kosong, batas 5 studio,
     * dan antrean berkapasitas bawaan.
     */
    public Bioskop() {
        this.daftarFilm = new ArrayList<>();
        this.daftarStudio = new ArrayList<>();
        this.daftarTiket = new ArrayList<>();
        this.antrian = new AntrianTiket();
        this.namaBioskop = "Bioskop";
        this.alamat = "-";
        this.jumlahStudioMaks = JUMLAH_STUDIO_MAKS_BAWAAN;
        this.buka = true;
    }

    /**
     * Konstruktor berparameter: membuat bioskop beserta antreannya.
     * Nilai yang punya aturan dikirim lewat setter supaya validasi tetap berjalan.
     *
     * @param kapasitasAntrian jumlah maksimal tiket yang boleh menunggu di antrean
     */
    public Bioskop(String namaBioskop, String alamat, int jumlahStudioMaks, int kapasitasAntrian) {
        // Koleksi dibuat lebih dulu karena setter jumlahStudioMaks memeriksa isinya
        this.daftarFilm = new ArrayList<>();
        this.daftarStudio = new ArrayList<>();
        this.daftarTiket = new ArrayList<>();
        setNamaBioskop(namaBioskop);
        this.alamat = alamat;
        setJumlahStudioMaks(jumlahStudioMaks);
        // Antrean dibuat oleh Bioskop sendiri (komposisi di diagram)
        this.antrian = new AntrianTiket(kapasitasAntrian);
        this.buka = true;
    }

    // ===== Getter dan Setter =====

    public String getNamaBioskop() {
        return namaBioskop;
    }

    public void setNamaBioskop(String namaBioskop) {
        // Validasi: nama tidak boleh null atau kosong
        if (namaBioskop == null || namaBioskop.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama bioskop tidak boleh kosong.");
        }
        this.namaBioskop = namaBioskop.trim();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getJumlahStudioMaks() {
        return jumlahStudioMaks;
    }

    public void setJumlahStudioMaks(int jumlahStudioMaks) {
        // Validasi: batas harus lebih dari 0
        if (jumlahStudioMaks <= 0) {
            throw new IllegalArgumentException("Jumlah studio maksimal harus lebih dari 0.");
        }
        // Validasi: batas baru tidak boleh lebih kecil dari studio yang sudah terdaftar
        if (jumlahStudioMaks < daftarStudio.size()) {
            throw new IllegalArgumentException(
                    "Batas tidak boleh lebih kecil dari jumlah studio terdaftar ("
                            + daftarStudio.size() + ").");
        }
        this.jumlahStudioMaks = jumlahStudioMaks;
    }

    /** Mengembalikan salinan daftar film supaya daftar asli tidak bisa diubah dari luar. */
    public ArrayList<Film> getDaftarFilm() {
        return new ArrayList<>(daftarFilm);
    }

    /** Mengembalikan salinan daftar studio. */
    public ArrayList<Studio> getDaftarStudio() {
        return new ArrayList<>(daftarStudio);
    }

    /** Mengembalikan salinan daftar tiket. */
    public ArrayList<Tiket> getDaftarTiket() {
        return new ArrayList<>(daftarTiket);
    }

    /** Antrean dikembalikan agar menu bisa memakai operasi ADT-nya (front, size, dan lain-lain). */
    public AntrianTiket getAntrian() {
        return antrian;
    }

    public boolean isBuka() {
        return buka;
    }

    public void setBuka(boolean buka) {
        this.buka = buka;
    }

    // ===== Method perilaku =====

    /**
     * Menambah film ke daftar. Film ditolak jika kosong, tidak punya kode,
     * atau kodenya sudah dipakai film lain.
     *
     * @return true jika berhasil ditambahkan
     */
    public boolean tambahFilm(Film film) {
        if (film == null) {
            System.out.println("Gagal: film tidak boleh kosong.");
            return false;
        }
        if (film.getKodeFilm() == null || film.getKodeFilm().trim().isEmpty()) {
            System.out.println("Gagal: kode film tidak boleh kosong.");
            return false;
        }
        for (Film f : daftarFilm) {
            if (f.getKodeFilm() != null && f.getKodeFilm().equalsIgnoreCase(film.getKodeFilm())) {
                System.out.println("Gagal: kode film " + film.getKodeFilm() + " sudah dipakai.");
                return false;
            }
        }
        daftarFilm.add(film);
        return true;
    }

    /**
     * Menambah studio ke daftar. Studio ditolak jika kosong, tidak punya kode,
     * kodenya sudah dipakai, atau jumlah studio sudah mencapai batas.
     *
     * @return true jika berhasil ditambahkan
     */
    public boolean tambahStudio(Studio studio) {
        if (studio == null) {
            System.out.println("Gagal: studio tidak boleh kosong.");
            return false;
        }
        if (studio.getKodeStudio() == null || studio.getKodeStudio().trim().isEmpty()) {
            System.out.println("Gagal: kode studio tidak boleh kosong.");
            return false;
        }
        if (daftarStudio.size() >= jumlahStudioMaks) {
            System.out.println("Gagal: jumlah studio sudah mencapai batas (" + jumlahStudioMaks + ").");
            return false;
        }
        for (Studio s : daftarStudio) {
            if (s.getKodeStudio() != null
                    && s.getKodeStudio().equalsIgnoreCase(studio.getKodeStudio())) {
                System.out.println("Gagal: kode studio " + studio.getKodeStudio() + " sudah dipakai.");
                return false;
            }
        }
        daftarStudio.add(studio);
        return true;
    }

    /**
     * Mencari film berdasarkan kode (persis sama) atau judul (mengandung kata kunci).
     * Kode diprioritaskan; huruf besar dan kecil diabaikan.
     *
     * @return film yang ditemukan, atau null jika tidak ada
     */
    public Film cariFilm(String kataKunci) {
        if (kataKunci == null || kataKunci.trim().isEmpty()) {
            return null;
        }
        String kunci = kataKunci.trim();
        // Prioritas 1: kode film persis sama
        for (Film f : daftarFilm) {
            if (f.getKodeFilm() != null && f.getKodeFilm().equalsIgnoreCase(kunci)) {
                return f;
            }
        }
        // Prioritas 2: judul mengandung kata kunci
        for (Film f : daftarFilm) {
            if (f.getJudul() != null && f.getJudul().toLowerCase().contains(kunci.toLowerCase())) {
                return f;
            }
        }
        return null;
    }

    /**
     * Mencari studio berdasarkan kode (huruf besar dan kecil diabaikan).
     *
     * @return studio yang ditemukan, atau null jika tidak ada
     */
    public Studio cariStudio(String kodeStudio) {
        if (kodeStudio == null || kodeStudio.trim().isEmpty()) {
            return null;
        }
        for (Studio s : daftarStudio) {
            if (s.getKodeStudio() != null && s.getKodeStudio().equalsIgnoreCase(kodeStudio.trim())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Membuat tiket berstatus MENUNGGU lalu memasukkannya ke antrean.
     * Setiap pelanggaran aturan menampilkan pesan dan mengembalikan null, tanpa menghentikan program.
     *
     * @return tiket yang dibuat, atau null jika gagal
     */
    public Tiket buatTiket(String namaPemesan, String kodeFilm, String kodeStudio,
                           int baris, int kolom) {
        if (!buka) {
            System.out.println("Gagal: bioskop sedang tutup.");
            return null;
        }
        Film film = cariFilm(kodeFilm);
        if (film == null) {
            System.out.println("Gagal: film dengan kode " + kodeFilm + " tidak ditemukan.");
            return null;
        }
        if (!film.isSedangTayang()) {
            System.out.println("Gagal: film " + film.getJudul() + " sedang tidak tayang.");
            return null;
        }
        Studio studio = cariStudio(kodeStudio);
        if (studio == null) {
            System.out.println("Gagal: studio dengan kode " + kodeStudio + " tidak ditemukan.");
            return null;
        }
        if (!studio.isAktif()) {
            System.out.println("Gagal: studio " + studio.getNamaStudio() + " sedang tidak aktif.");
            return null;
        }
        // Jika studio sudah punya film yang diputar, film pada tiket harus sama
        if (studio.getFilmDiputar() != null && studio.getFilmDiputar() != film) {
            System.out.println("Gagal: studio " + studio.getNamaStudio()
                    + " sedang memutar film lain (" + studio.getFilmDiputar().getJudul() + ").");
            return null;
        }
        // Aturan bisnis: kursi yang sudah terisi tidak bisa dipesan lagi, dan harus dalam kapasitas
        if (!studio.cekKursiTersedia(baris, kolom)) {
            System.out.println("Gagal: kursi baris " + baris + ", kolom " + kolom
                    + " sudah terisi atau di luar kapasitas studio.");
            return null;
        }
        try {
            // Kode tiket dibuat otomatis dari urutan tiket
            String kodeTiket = String.format("TKT-%03d", daftarTiket.size() + 1);
            Tiket tiket = new Tiket(kodeTiket, namaPemesan, film, studio, baris, kolom);
            // Antrean penuh: enqueue mencetak pesannya, tiket tidak dicatat
            if (!antrian.enqueue(tiket)) {
                return null;
            }
            daftarTiket.add(tiket);
            return tiket;
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal membuat tiket: " + e.getMessage());
            return null;
        }
    }

    /**
     * Memproses satu tiket terdepan dari antrean (dequeue), lalu mengonfirmasinya.
     * Tiket menjadi DIKONFIRMASI jika kursinya masih kosong, atau DITOLAK jika sudah terisi.
     *
     * @return true jika tiket berhasil dikonfirmasi, false jika antrean kosong atau tiket ditolak
     */
    public boolean prosesAntrian() {
        Tiket tiket = antrian.dequeue();
        if (tiket == null) {
            return false;
        }
        boolean berhasil = tiket.konfirmasi();
        if (berhasil) {
            System.out.println("Tiket " + tiket.getKodeTiket() + " berhasil dikonfirmasi (kursi "
                    + tiket.labelKursi() + ").");
        }
        return berhasil;
    }

    /**
     * Menjumlahkan total harga semua tiket berstatus DIKONFIRMASI.
     *
     * @return total pendapatan
     */
    public double hitungTotalPendapatan() {
        double total = 0;
        for (Tiket t : daftarTiket) {
            if (t.isDikonfirmasi()) {
                total += t.getTotalHarga();
            }
        }
        return total;
    }

    /**
     * Menampilkan ringkasan: jumlah film, studio, tiket per status, dan total pendapatan.
     */
    public void tampilkanRingkasan() {
        int dikonfirmasi = 0;
        for (Tiket t : daftarTiket) {
            if (t.isDikonfirmasi()) {
                dikonfirmasi++;
            }
        }
        int menunggu = antrian.size();
        // Tiket yang sudah keluar dari antrean dan tidak dikonfirmasi berarti ditolak
        int ditolak = daftarTiket.size() - dikonfirmasi - menunggu;

        System.out.println("===== Ringkasan " + namaBioskop + " =====");
        System.out.println("Alamat            : " + alamat);
        System.out.println("Status            : " + (buka ? "Buka" : "Tutup"));
        System.out.println("Jumlah film       : " + daftarFilm.size());
        System.out.println("Jumlah studio     : " + daftarStudio.size() + " (maks " + jumlahStudioMaks + ")");
        System.out.println("Jumlah tiket      : " + daftarTiket.size());
        System.out.println("  - Menunggu      : " + menunggu);
        System.out.println("  - Dikonfirmasi  : " + dikonfirmasi);
        System.out.println("  - Ditolak       : " + ditolak);
        System.out.println("Total pendapatan  : Rp" + hitungTotalPendapatan());
    }
}
