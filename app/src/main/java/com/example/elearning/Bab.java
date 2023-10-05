package com.example.elearning;

public class Bab {
    private String idBab;
    private String judulBab;
    private String deskripsiBab;
    private String mapel;
    private String kelas;

    public Bab() {
        // Konstruktor kosong diperlukan untuk Firebase Realtime Database.
    }

    public Bab(String judulBab, String deskripsiBab, String mapel, String kelas) {
        this.judulBab = judulBab;
        this.deskripsiBab = deskripsiBab;
        this.mapel = mapel;
        this.kelas = kelas;
    }
    public void setidBab(String idBab) {
        this.idBab = idBab;
    }

    public String getIdBab() {
        return idBab;
    }

    public void setJudulBab(String judulBab) {
        this.judulBab = judulBab;
    }

    public String getJudulBab() {
        return judulBab;
    }

    public void setDeskripsiBab(String deskripsiBab) {
        this.deskripsiBab = deskripsiBab;
    }

    public String getDeskripsiBab() {
        return deskripsiBab;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getMapel() {
        return mapel;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKelas() {
        return kelas;
    }
}
