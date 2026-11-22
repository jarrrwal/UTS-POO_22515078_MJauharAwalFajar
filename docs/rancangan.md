# Daftar Kelas

1. Film
2. Studio
3. Tiket
4. Bioskop
5. AntrianTiket (ADT Queue)
6. MenuBioskop (Main/menu)

## Film
Atribut:
- kodeFilm : String
- judul : String
- genre : String
- durasiMenit : int
- hargaTiket : double
- sedangTayang : boolean

Method perilaku:
- tampilkanInfo() : void, menampilkan data film di layar
- ubahStatusTayang(boolean status) : void, mengubah film jadi tayang / tidak tayang
- hitungDurasiJam() : double, mengubah durasi menit menjadi jam 

Konstruktor:
- Film() : default
- Film(kodeFilm, judul, genre, durasiMenit, hargaTiket)

Validasi di setter:
- durasiMenit harus lebih dari 0
- hargaTiket harus lebih dari 0
- judul tidak boleh kosong

## Studio
Atribut:
- kodeStudio : String
- namaStudio : String
- jenisStudio : String
- jumlahBaris : int
- jumlahKolom : int
- kursiTerisi : boolean[][] (true = terisi, false = kosong)
- filmDiputar : Film (film yang sedang tayang di studio ini)
- aktif : boolean

Method perilaku:
- hitungKapasitas() : int, mengembalikan jumlahBaris x jumlahKolom
- cekKursiTersedia(int baris, int kolom) : boolean, true jika kursi masih kosong
- pesanKursi(int baris, int kolom) : boolean, menandai kursi terisi, false jika kursi sudah terisi
- hitungKursiKosong() : int, jumlah kursi yang masih kosong
- tampilkanDenahKursi() : void, menampilkan denah kursi di layar

Konstruktor:
- Studio() : default
- Studio(kodeStudio, namaStudio, jenisStudio, jumlahBaris, jumlahKolom)

Validasi di setter:
- jumlahBaris dan jumlahKolom harus lebih dari 0
- namaStudio tidak boleh kosong
- nomor baris dan kolom yang dipesan harus berada dalam kapasitas studio

## Tiket
Atribut:
- kodeTiket : String
- namaPemesan : String
- film : Film
- studio : Studio
- barisKursi : int
- kolomKursi : int
- totalHarga : double
- status : String (MENUNGGU, DIKONFIRMASI, atau DITOLAK)
- sudahDibayar : boolean

Method perilaku:
- hitungTotalHarga() : double, mengambil harga dari film dan menyimpannya ke totalHarga
- labelKursi() : String, mengubah baris dan kolom menjadi teks, misalnya "B5"
- konfirmasi() : boolean, meminta Studio menandai kursi terisi, status jadi DIKONFIRMASI
- tolak() : void, mengubah status menjadi DITOLAK
- bayar() : void, mengubah sudahDibayar menjadi true
- tampilkanInfo() : void, menampilkan data tiket di layar

Konstruktor:
- Tiket() : default
- Tiket(kodeTiket, namaPemesan, film, studio, barisKursi, kolomKursi)

Validasi di setter:
- namaPemesan tidak boleh kosong
- barisKursi dan kolomKursi harus lebih dari 0
- status hanya boleh salah satu dari tiga nilai di atas

## Bioskop
Atribut:
- namaBioskop : String
- alamat : String
- jumlahStudioMaks : int (batas jumlah studio yang boleh didaftarkan)
- daftarFilm : ArrayList<Film>
- daftarStudio : ArrayList<Studio>
- daftarTiket : ArrayList<Tiket>
- antrian : AntrianTiket
- buka : boolean

Method perilaku:
- tambahFilm(Film film) : boolean, menolak kode film yang sama
- tambahStudio(Studio studio) : boolean, menolak jika melebihi batas
- cariFilm(String kataKunci) : Film, mencari berdasarkan kode atau judul
- cariStudio(String kodeStudio) : Studio, mencari studio berdasarkan kode
- buatTiket(String namaPemesan, String kodeFilm, String kodeStudio, int baris, int kolom) : Tiket, membuat Tiket berstatus MENUNGGU lalu memasukkannya ke antrian
- prosesAntrian() : boolean, mengambil tiket terdepan dari antrian lalu memanggil konfirmasi()
- hitungTotalPendapatan() : double, menjumlahkan totalHarga tiket berstatus DIKONFIRMASI
- tampilkanRingkasan() : void, menampilkan jumlah film, studio, tiket, dan total pendapatan

Konstruktor:
- Bioskop() : default
- Bioskop(namaBioskop, alamat, jumlahStudioMaks, kapasitasAntrian)

Validasi di setter:
- namaBioskop tidak boleh kosong
- jumlahStudioMaks harus lebih dari 0

## AntrianTiket
Atribut (Semua Private):
- daftarTiket : ArrayList<Tiket>
- kapasitas : int 

Operasi publik:
- enqueue(Tiket tiket) : boolean
- dequeue() : Tiket
- front() : Tiket
- isEmpty() : boolean
- isFull() : boolean
- size() : int
- tampilkanIsi() : void, menampilkan isi antrean dari depan ke belakang

Konstruktor:
- AntrianTiket() : default (kapasitas bawaan, misalnya 10)
- AntrianTiket(int kapasitas)

Validasi:
- kapasitas harus lebih dari 0

## MenuBioskop
Atribut (private):
- bioskop : Bioskop
- input : Scanner

Method publik:
- main(String[] args) : titik awal program
- jalankan() : void, perulangan menu sampai pengguna memilih keluar

Method private:
- tampilkanMenu() : void, mencetak 7 pilihan menu
- bacaInt(String pesan) : int, mengulang sampai input angka valid
- bacaDouble(String pesan) : double, mengulang sampai input angka valid
- bacaTeks(String pesan) : String, mengulang sampai tidak kosong
- menuFilm(), menuStudio(), menuBuatTiket(), menuCari(), menuAntrian(), menuLaporan() : void, satu method per menu

Konstruktor:
- MenuBioskop() : membuat objek Bioskop dan Scanner

## Catatan Penyimpangan dari Diagram (untuk Bab 3)

### Studio
- Ada tiga method private pembantu yang tidak digambar di diagram:
  - buatUlangDenah() : membuat denah kursi baru saat ukuran studio berubah
  - posisiValid(baris, kolom) : memeriksa apakah posisi berada di dalam kapasitas
  - labelKursi(baris, kolom) : mengubah posisi kursi menjadi label, misalnya "B5"
- jumlahBaris dibatasi maksimal 26, karena label baris memakai huruf A sampai Z.
- Atribut kursiTerisi tidak punya getter, supaya isi kursi hanya bisa diubah lewat pesanKursi() (enkapsulasi).

### Tiket
- Ada tiga konstanta status (private static final): STATUS_MENUNGGU, STATUS_DIKONFIRMASI, STATUS_DITOLAK. Konstanta tidak digambar di diagram.
- bayar() dan tolak() punya pengaman status (hanya tiket yang sudah dikonfirmasi yang bisa dibayar, dan tiket yang sudah dikonfirmasi tidak bisa ditolak). Ini hanya mengatur isi method, nama dan tipe kembalian sama dengan diagram.
- totalHarga tidak punya setter, karena nilainya dihitung oleh hitungTotalHarga().
- Ada method public tambahan isDikonfirmasi() : boolean yang tidak digambar di diagram. Dipakai Bioskop saat menghitung pendapatan, supaya konstanta status tidak perlu dibuka ke luar class.

### AntrianTiket
- Ada konstanta private static final KAPASITAS_BAWAAN = 10 untuk konstruktor default. Konstanta tidak digambar di diagram.
- Ada getKapasitas() dan setKapasitas(int) dengan validasi (kapasitas harus lebih dari 0 dan tidak boleh lebih kecil dari jumlah tiket yang sedang antre). Getter dan setter tidak digambar di diagram, sama seperti class lain.

### Bioskop
- Ada konstanta private static final JUMLAH_STUDIO_MAKS_BAWAAN = 5 untuk konstruktor default. Konstanta tidak digambar di diagram.
- Ada getter dan setter yang tidak digambar di diagram. getDaftarFilm(), getDaftarStudio(), dan getDaftarTiket() mengembalikan salinan daftar supaya daftar asli tidak bisa diubah dari luar. getAntrian() mengembalikan antrean agar menu bisa memakai operasi ADT-nya.
- Kode tiket dibuat otomatis oleh buatTiket() dengan format TKT-001, TKT-002, dan seterusnya.
- buatTiket() memeriksa aturan tambahan di dalam method: bioskop harus buka, film harus sedang tayang, studio harus aktif, studio yang sudah memutar film tertentu hanya menerima tiket film itu, dan kursi harus tersedia dan berada dalam kapasitas. Ini mengatur isi method, nama dan tipe kembalian sama dengan diagram.
- Studio digambar sebagai komposisi, tetapi di kode objek Studio dibuat di luar lalu didaftarkan lewat tambahStudio().