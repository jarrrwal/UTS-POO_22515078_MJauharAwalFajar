package bioskop;

public class Film {
    private String kodeFilm;
    private String judul;
    private String genre;
    private int durasiMenit;
    private double hargaTiket;
    private boolean sedangTayang;

    /**
     * Konstruktor default: membuat objek Film kosong
     */
    public Film() {
    }

    /**
     * Konstruktor berparameter: mengisi data film saat objek dibuat.
     * Nilai yang punya aturan dikirim lewat setter supaya validasi tetap berjalan.
     */
    public Film(String kodeFilm, String judul, String genre, int durasiMenit, double hargaTiket) {
        this.kodeFilm = kodeFilm;
        this.genre = genre;
        setJudul(judul);
        setDurasiMenit(durasiMenit);
        setHargaTiket(hargaTiket);
        this.sedangTayang = true; // film baru dianggap sedang tayang
    }

    //===== Getter dan Setter =====

    public String getKodeFilm() {
        return kodeFilm;
    }

    public void setKodeFilm(String kodeFilm) {
        this.kodeFilm = kodeFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        // Validasi: judul tidak boleh null atau kosong
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul film tidak boleh kosong.");
        }
        this.judul = judul.trim();
    }

    public String getGenre() {
        return genre;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public void setDurasiMenit(int durasiMenit) {
        // Validasi: durasi harus lebih dari 0
        if (durasiMenit <= 0) {
            throw new IllegalArgumentException("Durasi film harus lebih dari 0");
        }
        this.durasiMenit = durasiMenit;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(double hargaTiket) {
        // Validasi: harga harus lebih dari 0
        if (hargaTiket <= 0) {
            throw new IllegalArgumentException("Harga tiket harus lebih dari 0");
        }
        this.hargaTiket = hargaTiket;
    }

    public boolean isSedangTayang() {
        return sedangTayang;
    }

    public void setSedangTayang(boolean sedangTayang) {
        this.sedangTayang = sedangTayang;
    }
}
