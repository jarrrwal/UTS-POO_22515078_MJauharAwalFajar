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