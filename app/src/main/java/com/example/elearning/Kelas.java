package com.example.elearning;

public class Kelas {
    private String id;
    private String namaKelas;
    private String deskripsiKelas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getDeskripsiKelas() {
        return deskripsiKelas;
    }

    public void setDeskripsiKelas(String deskripsiKelas) {
        this.deskripsiKelas = deskripsiKelas;
    }

    public Kelas() {
        // Konstruktor kosong diperlukan untuk Firebase Realtime Database.
    }

    public Kelas(String namaKelas, String deskripsiKelas) {
        this.namaKelas = namaKelas;
        this.deskripsiKelas = deskripsiKelas;
    }
}
