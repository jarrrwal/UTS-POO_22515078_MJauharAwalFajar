# Hasil Pengujian

| No | Skenario | Input | Hasil yang diharapkan | Hasil aktual | Status |
|---|---|---|---|---|---|
| 1 | Tambah film valid | Menu 1 > 1: F01, Laskar Pelangi, Drama, 120, 50000 | Film berhasil ditambahkan dan tampil di daftar film | Muncul "Film berhasil ditambahkan." | Lulus|
| 2 | Tambah studio valid | Menu 2 > 1: S1, Studio 1, Regular, 3, 4 | Studio berhasil ditambahkan, kapasitas 12 kursi | Muncul "Studio berhasil ditambahkan (kapasitas 12 kursi)." | Lulus |
| 3 | Buat tiket valid | Menu 3: Budi, F01, S1, baris 1, kolom 1 | Tiket TKT-001 dibuat berstatus MENUNGGU dan masuk antrean | Muncul "Tiket berhasil dibuat dan masuk antrean." dengan status MENUNGGU | Lulus |
| 4 | Proses antrean | Menu 5 > 2 | Tiket TKT-001 dikonfirmasi, kursi A1 | Muncul "Tiket TKT-001 berhasil dikonfirmasi (kursi A1)." | Lulus|
| 5 | Pesan kursi yang sudah terisi | Menu 3: Dewi, F01, S1, baris 1, kolom 1 | Pesan gagal karena kursi sudah terisi, program tetap berjalan | Muncul "Gagal: kursi baris 1, kolom 1 sudah terisi atau di luar kapasitas studio." dan menu tampil lagi | Lulus |
| 6 | Kursi di luar kapasitas studio | Menu 3: Eko, F01, S1, baris 4, kolom 1 | Pesan gagal karena di luar kapasitas, program tetap berjalan | Muncul pesan gagal yang sama dan menu tampil lagi | Lulus |
| 7 | Dua tiket untuk kursi yang sama dalam antrean | Menu 3: Ani kursi baris 1 kolom 2, lalu Citra kursi baris 1 kolom 2; Menu 5 > 2 dua kali | Tiket Ani dikonfirmasi, tiket Citra ditolak | Tiket Ani dikonfirmasi (kursi A2). Tiket Citra muncul "Gagal: kursi A2 sudah terisi." lalu ditolak | Lulus |
| 8 | Kode film duplikat | Menu 1 > 1: kode F01 lagi | Pesan gagal karena kode sudah dipakai | Muncul "Gagal: kode film F01 sudah dipakai." | Lulus |
| 9 | Dequeue saat antrean kosong | Menu 5 > 2 | Pesan antrean kosong, tanpa crash | Muncul "Gagal: antrean kosong, tidak ada tiket untuk diproses." | Lulus |
| 10 | Front saat antrean kosong | Menu 5 > 3 | Pesan antrean kosong, tanpa crash | Muncul "Gagal: antrean kosong, tidak ada tiket di depan." | Lulus |
| 11 | Huruf pada kolom angka | Pilih menu: abc | Pesan input tidak valid, lalu diminta ulang | Muncul "Input tidak valid. Masukkan angka bulat, bukan huruf." lalu diminta ulang | Lulus |
| 12 | Nilai kosong | Menu 3: nama pemesan dikosongkan | Pesan input tidak boleh kosong, lalu diminta ulang | Muncul "Input tidak boleh kosong." lalu diminta ulang | Lulus |
| 13 | Antrean penuh | 5 tiket tanpa diproses, lalu tiket ke-6 | Pesan antrean penuh (kapasitas 5) | Muncul "Gagal: antrean penuh (kapasitas 5)." | Lulus |
| 14 | Cek status antrean | Menu 5 > 5 | Antrean penuh: ya, jumlah 5 dari kapasitas 5 | Muncul "Antrean penuh : ya" dan "Jumlah tiket : 5 dari kapasitas 5" | Lulus |