package bioskop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class MenuBioskop adalah class utama (Main) yang menyediakan menu console
 * untuk mengelola film, studio, tiket, antrean (ADT Queue), pencarian, dan laporan.
 * Semua input pengguna divalidasi sehingga input salah (huruf pada kolom angka,
 * nilai kosong) hanya menampilkan pesan dan tidak membuat program berhenti.
 */
public class MenuBioskop {
    private Bioskop bioskop;
    private Scanner input;

    /**
     * Konstruktor: membuat objek Bioskop (dengan data awal) dan Scanner untuk membaca input.
     */
    public MenuBioskop() {
        // Maksimal 5 studio dan antrean berkapasitas 5 (kecil agar mudah menguji antrean penuh)
        this.bioskop = new Bioskop("Bioskop Mini Kendari", "Kendari, Sulawesi Tenggara", 5, 5);
        this.input = new Scanner(System.in);
    }

    /**
     * Titik awal program.
     */
    public static void main(String[] args) {
        MenuBioskop menu = new MenuBioskop();
        menu.jalankan();
    }

    /**
     * Menjalankan perulangan menu utama sampai pengguna memilih keluar.
     */
    public void jalankan() {
        System.out.println("Selamat datang di " + bioskop.getNamaBioskop() + "!");
        boolean lanjut = true;
        while (lanjut) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu (1-7): ");
            switch (pilihan) {
                case 1:
                    menuFilm();
                    break;
                case 2:
                    menuStudio();
                    break;
                case 3:
                    menuBuatTiket();
                    break;
                case 4:
                    menuCari();
                    break;
                case 5:
                    menuAntrian();
                    break;
                case 6:
                    menuLaporan();
                    break;
                case 7:
                    lanjut = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Pilih angka 1 sampai 7.");
            }
        }
        input.close();
    }

    private void tampilkanMenu() {
        System.out.println();
        System.out.println("========== " + bioskop.getNamaBioskop() + " ==========");
        System.out.println("1. Kelola film (tambah dan tampil)");
        System.out.println("2. Kelola studio (tambah dan tampil)");
        System.out.println("3. Buat tiket");
        System.out.println("4. Cari data");
        System.out.println("5. Antrean tiket (operasi ADT Queue)");
        System.out.println("6. Laporan ringkasan");
        System.out.println("7. Keluar");
    }

    // ===== Pembacaan input yang divalidasi =====

    /**
     * Membaca bilangan bulat. Mengulang sampai input valid (bukan kosong, bukan huruf).
     */
    private int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();
            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong. Masukkan angka bulat.");
                continue;
            }
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka bulat, bukan huruf.");
            }
        }
    }

    /**
     * Membaca bilangan desimal. Mengulang sampai input valid.
     * Ketik angka tanpa pemisah ribuan, misalnya 50000.
     */
    private double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();
            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong. Masukkan angka.");
                continue;
            }
            try {
                double nilai = Double.parseDouble(teks);
                // NaN dan Infinity bisa lolos parseDouble, jadi ditolak secara khusus
                if (Double.isNaN(nilai) || Double.isInfinite(nilai)) {
                    System.out.println("Input tidak valid. Masukkan angka biasa.");
                    continue;
                }
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka, bukan huruf.");
            }
        }
    }

    /**
     * Membaca teks. Mengulang sampai input tidak kosong.
     */
    private String bacaTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();
            if (!teks.isEmpty()) {
                return teks;
            }
            System.out.println("Input tidak boleh kosong.");
        }
    }

    // ===== Menu 1: film =====

    private void menuFilm() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("--- Kelola Film ---");
            System.out.println("1. Tambah film");
            System.out.println("2. Tampilkan semua film");
            System.out.println("3. Ubah status tayang film");
            System.out.println("0. Kembali");
            int pilihan = bacaInt("Pilih: ");
            switch (pilihan) {
                case 1: {
                    String kode = bacaTeks("Kode film: ");
                    String judul = bacaTeks("Judul: ");
                    String genre = bacaTeks("Genre: ");
                    int durasi = bacaInt("Durasi (menit): ");
                    double harga = bacaDouble("Harga tiket (Rp, contoh 50000): ");
                    try {
                        // Validasi di setter bisa melempar pengecualian; ditangkap agar program tidak berhenti
                        Film film = new Film(kode, judul, genre, durasi, harga);
                        if (bioskop.tambahFilm(film)) {
                            System.out.println("Film berhasil ditambahkan.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    ArrayList<Film> daftar = bioskop.getDaftarFilm();
                    if (daftar.isEmpty()) {
                        System.out.println("Belum ada film.");
                    } else {
                        for (int i = 0; i < daftar.size(); i++) {
                            System.out.println("--- Film ke-" + (i + 1) + " ---");
                            daftar.get(i).tampilkanInfo();
                        }
                    }
                    break;
                }
                case 3: {
                    String kode = bacaTeks("Kode film: ");
                    Film film = bioskop.cariFilm(kode);
                    if (film == null) {
                        System.out.println("Film tidak ditemukan.");
                    } else {
                        int status = bacaInt("Film " + film.getJudul()
                                + " sedang tayang? (1 = ya, 0 = tidak): ");
                        if (status != 0 && status != 1) {
                            System.out.println("Pilihan tidak valid. Masukkan 1 atau 0.");
                        } else {
                            film.ubahStatusTayang(status == 1);
                            System.out.println("Status film " + film.getJudul() + " diubah menjadi "
                                    + (status == 1 ? "sedang tayang." : "tidak tayang."));
                        }
                    }
                    break;
                }
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ===== Menu 2: studio =====

    private void menuStudio() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("--- Kelola Studio ---");
            System.out.println("1. Tambah studio");
            System.out.println("2. Tampilkan semua studio");
            System.out.println("3. Tampilkan denah kursi studio");
            System.out.println("4. Atur film yang diputar di studio");
            System.out.println("0. Kembali");
            int pilihan = bacaInt("Pilih: ");
            switch (pilihan) {
                case 1: {
                    String kode = bacaTeks("Kode studio: ");
                    String nama = bacaTeks("Nama studio: ");
                    String jenis = bacaTeks("Jenis studio (misalnya Regular atau Premium): ");
                    int baris = bacaInt("Jumlah baris (1-26): ");
                    int kolom = bacaInt("Jumlah kolom: ");
                    try {
                        Studio studio = new Studio(kode, nama, jenis, baris, kolom);
                        if (bioskop.tambahStudio(studio)) {
                            System.out.println("Studio berhasil ditambahkan (kapasitas "
                                    + studio.hitungKapasitas() + " kursi).");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    ArrayList<Studio> daftar = bioskop.getDaftarStudio();
                    if (daftar.isEmpty()) {
                        System.out.println("Belum ada studio.");
                    } else {
                        for (int i = 0; i < daftar.size(); i++) {
                            Studio s = daftar.get(i);
                            System.out.println((i + 1) + ". " + s.getKodeStudio()
                                    + " | " + s.getNamaStudio()
                                    + " | " + s.getJenisStudio()
                                    + " | kapasitas " + s.hitungKapasitas()
                                    + " | kosong " + s.hitungKursiKosong()
                                    + " | film: " + (s.getFilmDiputar() != null
                                    ? s.getFilmDiputar().getJudul() : "-")
                                    + " | " + (s.isAktif() ? "aktif" : "tidak aktif"));
                        }
                    }
                    break;
                }
                case 3: {
                    String kode = bacaTeks("Kode studio: ");
                    Studio studio = bioskop.cariStudio(kode);
                    if (studio == null) {
                        System.out.println("Studio tidak ditemukan.");
                    } else {
                        studio.tampilkanDenahKursi();
                    }
                    break;
                }
                case 4: {
                    String kodeStudio = bacaTeks("Kode studio: ");
                    Studio studio = bioskop.cariStudio(kodeStudio);
                    if (studio == null) {
                        System.out.println("Studio tidak ditemukan.");
                        break;
                    }
                    String kodeFilm = bacaTeks("Kode film: ");
                    Film film = bioskop.cariFilm(kodeFilm);
                    if (film == null) {
                        System.out.println("Film tidak ditemukan.");
                        break;
                    }
                    studio.setFilmDiputar(film);
                    System.out.println("Studio " + studio.getNamaStudio() + " sekarang memutar "
                            + film.getJudul() + ".");
                    break;
                }
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ===== Menu 3: buat tiket (transaksi utama) =====

    private void menuBuatTiket() {
        System.out.println();
        System.out.println("--- Buat Tiket ---");
        ArrayList<Film> films = bioskop.getDaftarFilm();
        ArrayList<Studio> studios = bioskop.getDaftarStudio();
        if (films.isEmpty() || studios.isEmpty()) {
            System.out.println("Gagal: tambahkan minimal satu film dan satu studio terlebih dahulu.");
            return;
        }
        System.out.println("Film tersedia:");
        for (Film f : films) {
            System.out.println("  " + f.getKodeFilm() + " - " + f.getJudul()
                    + (f.isSedangTayang() ? "" : " (tidak tayang)"));
        }
        System.out.println("Studio tersedia:");
        for (Studio s : studios) {
            System.out.println("  " + s.getKodeStudio() + " - " + s.getNamaStudio()
                    + " (" + s.getJumlahBaris() + " baris x " + s.getJumlahKolom() + " kolom)");
        }
        String nama = bacaTeks("Nama pemesan: ");
        String kodeFilm = bacaTeks("Kode film: ");
        String kodeStudio = bacaTeks("Kode studio: ");
        int baris = bacaInt("Nomor baris kursi (1 = A, 2 = B, dst): ");
        int kolom = bacaInt("Nomor kolom kursi: ");

        // Seluruh aturan bisnis dijalankan di Bioskop.buatTiket; pelanggaran hanya menampilkan pesan
        Tiket tiket = bioskop.buatTiket(nama, kodeFilm, kodeStudio, baris, kolom);
        if (tiket != null) {
            System.out.println("Tiket berhasil dibuat dan masuk antrean.");
            tiket.tampilkanInfo();
        }
    }

    // ===== Menu 4: cari data =====

    private void menuCari() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("--- Cari Data ---");
            System.out.println("1. Cari film (kode atau judul)");
            System.out.println("2. Cari studio (kode)");
            System.out.println("3. Cari tiket (kode)");
            System.out.println("0. Kembali");
            int pilihan = bacaInt("Pilih: ");
            switch (pilihan) {
                case 1: {
                    String kunci = bacaTeks("Kode atau judul film: ");
                    Film film = bioskop.cariFilm(kunci);
                    if (film == null) {
                        System.out.println("Film tidak ditemukan.");
                    } else {
                        film.tampilkanInfo();
                    }
                    break;
                }
                case 2: {
                    String kode = bacaTeks("Kode studio: ");
                    Studio studio = bioskop.cariStudio(kode);
                    if (studio == null) {
                        System.out.println("Studio tidak ditemukan.");
                    } else {
                        System.out.println("Kode        : " + studio.getKodeStudio());
                        System.out.println("Nama        : " + studio.getNamaStudio());
                        System.out.println("Jenis       : " + studio.getJenisStudio());
                        System.out.println("Ukuran      : " + studio.getJumlahBaris() + " baris x "
                                + studio.getJumlahKolom() + " kolom");
                        System.out.println("Kapasitas   : " + studio.hitungKapasitas());
                        System.out.println("Kursi kosong: " + studio.hitungKursiKosong());
                    }
                    break;
                }
                case 3: {
                    String kode = bacaTeks("Kode tiket (contoh TKT-001): ");
                    Tiket ditemukan = null;
                    for (Tiket t : bioskop.getDaftarTiket()) {
                        if (t.getKodeTiket().equalsIgnoreCase(kode)) {
                            ditemukan = t;
                            break;
                        }
                    }
                    if (ditemukan == null) {
                        System.out.println("Tiket tidak ditemukan.");
                    } else {
                        ditemukan.tampilkanInfo();
                    }
                    break;
                }
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ===== Menu 5: operasi ADT Queue =====

    private void menuAntrian() {
        AntrianTiket antrian = bioskop.getAntrian();
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("--- Antrean Tiket (ADT Queue) ---");
            System.out.println("1. Tambah tiket ke antrean (enqueue)");
            System.out.println("2. Proses tiket terdepan (dequeue lalu konfirmasi)");
            System.out.println("3. Lihat tiket terdepan (front)");
            System.out.println("4. Tampilkan isi antrean");
            System.out.println("5. Cek status antrean (isEmpty, isFull, size)");
            System.out.println("6. Bayar tiket yang sudah dikonfirmasi");
            System.out.println("0. Kembali");
            int pilihan = bacaInt("Pilih: ");
            switch (pilihan) {
                case 1:
                    // Tiket baru selalu masuk antrean lewat proses pembuatan tiket
                    menuBuatTiket();
                    break;
                case 2:
                    // Antrean kosong ditangani oleh dequeue (pesan jelas, tanpa crash)
                    bioskop.prosesAntrian();
                    break;
                case 3: {
                    Tiket depan = antrian.front();
                    if (depan != null) {
                        System.out.println("Tiket terdepan:");
                        depan.tampilkanInfo();
                    }
                    break;
                }
                case 4:
                    antrian.tampilkanIsi();
                    break;
                case 5:
                    System.out.println("Antrean kosong : " + (antrian.isEmpty() ? "ya" : "tidak"));
                    System.out.println("Antrean penuh  : " + (antrian.isFull() ? "ya" : "tidak"));
                    System.out.println("Jumlah tiket   : " + antrian.size() + " dari kapasitas "
                            + antrian.getKapasitas());
                    break;
                case 6: {
                    String kode = bacaTeks("Kode tiket (contoh TKT-001): ");
                    Tiket ditemukan = null;
                    for (Tiket t : bioskop.getDaftarTiket()) {
                        if (t.getKodeTiket().equalsIgnoreCase(kode)) {
                            ditemukan = t;
                            break;
                        }
                    }
                    if (ditemukan == null) {
                        System.out.println("Tiket tidak ditemukan.");
                    } else {
                        ditemukan.bayar();
                        if (ditemukan.isSudahDibayar()) {
                            System.out.println("Pembayaran tiket " + ditemukan.getKodeTiket() + " tercatat.");
                        }
                    }
                    break;
                }
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ===== Menu 6: laporan =====

    private void menuLaporan() {
        System.out.println();
        bioskop.tampilkanRingkasan();
        ArrayList<Tiket> semua = bioskop.getDaftarTiket();
        if (!semua.isEmpty()) {
            System.out.println("Daftar tiket:");
            for (Tiket t : semua) {
                System.out.println("  " + t.getKodeTiket()
                        + " | " + t.getNamaPemesan()
                        + " | " + t.getFilm().getJudul()
                        + " | kursi " + t.labelKursi()
                        + " | " + t.getStatus()
                        + " | " + (t.isSudahDibayar() ? "sudah dibayar" : "belum dibayar"));
            }
        }
    }
}
