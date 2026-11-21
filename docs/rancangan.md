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
- durasiMenit :int
- hargaTiket : double
- sedangTayang : boolean

Method perilaku:
- tampilkanInfo() : menampilkan data film di layar
- ubahStatusTayang(boolean status) : mengubah film jadi tayang / tidak tayang
- hitungDurasiJam() : mengubah durasi menit menjadi jam (double)

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
- hitungKapasitas() : mengembalikan jumlahBaris x jumlahKolom (int)
- cekKursiTersedia(int baris, int kolom) : true jika kursi masih kosong
- pesanKursi(int baris, int kolom) : menandai kursi terisi, gagal jika kursi sudah terisi
- hitungKursiKosong() : jumlah kursi yang masih kosong (int)
- tampilkanDenahKursi() : menampilkan denah kursi di layar

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
- hitungTotalHarga() : mengambil harga dari film dan menyimpannya ke totalHarga
- labelKursi() : mengubah baris dan kolom menjadi teks, misalnya "B5" (String)
- konfirmasi() : meminta Studio menandai kursi terisi, status jadi DIKONFIRMASI
- tolak() : mengubah status menjadi DITOLAK
- bayar() : void
- tampilkanInfo() : menampilkan data tiket di layar

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
- tambahFilm(Film film) : menambah film, menolak kode film yang sama
- tambahStudio(Studio studio) : menambah studio, menolak jika melebihi batas
- cariFilm(String kataKunci) : mencari film berdasarkan kode atau judul
- buatTiket(...) : membuat Tiket berstatus MENUNGGU lalu memasukkannya ke antrian
- prosesAntrian() : mengambil tiket terdepan dari antrian lalu memanggil konfirmasi()
- hitungTotalPendapatan() : menjumlahkan totalHarga semua tiket berstatus DIKONFIRMASI (double)
- tampilkanRingkasan() : jumlah film, studio, tiket, dan total pendapatan

Konstruktor:
- Bioskop() : default
- Bioskop(namaBioskop, alamat, jumlahStudioMaks, kapasitasAntrian)

Validasi di setter:
- namaBioskop tidak boleh kosong
- jumlahStudioMaks harus lebih dari 0

## AntrianTiket:
Atribut (Semua Private):
- daftarTiket : ArrayList<Tiket>
- kapasitas(int)

Operasi ublik:
- enqueue(Tiket tiket) : boolean
- dequeue() : Tiket
- front() : Tiket
- isEmpty() : boolean
- isFull() : boolean
- size() : int
- tampilkanIsi() : menampilkan isi antrean dari depan ke belakang

Konstruktor:
- AntrianTiket() : default (kapasitas bawaan, misalnya 10)
- AntrianTiket(int kapasitas)

Validasi:
- kapasitas harus lebih dari 0

## MenuBioskop
Atribut (private):
- bioskop : Bioskop
- input : Scanner

Method:
- main(String[] args) : titik awal program
- jalankan() : perulangan menu sampai pengguna memilih keluar
- tampilkanMenu() : mencetak 7 pilihan menu
- bacaInt(String pesan) : double (mengulang sampai valid)
- bacaTeks(String pesan) : String (mengulang sampai tidak kosong)
- menuFilm(), menuStudio(), menuBuatTiket(), menuCari(), menuAntrian(), menuLaporan() : satu method per menu

Konstruktor:
- MenuBioskop() : membuat objek Bioskop dan Scanner