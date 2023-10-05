package com.example.elearning;

public class Mapel {
    private String idMapel;
    private String namaMapel;
    private String deskripsiMapel;

    public String getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(String idMapel) {
        this.idMapel = idMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    public String getDeskripsiMapel() {
        return deskripsiMapel;
    }

    public void setDeskripsiMapel(String deskripsiMapel) {
        this.deskripsiMapel = deskripsiMapel;
    }

    public Mapel() {
        // Konstruktor kosong diperlukan untuk Firebase Realtime Database.
    }

    public Mapel(String namaMapel, String deskripsiMapel) {
        this.namaMapel = namaMapel;
        this.deskripsiMapel = deskripsiMapel;
    }
}

