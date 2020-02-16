package xyz.dirgabrajamusti.sistemabsensi.Model;

public class Kelas {
    private int id;
    private String nama_kelas;
    private String jurusan_id;

    public Kelas(int id, String nama_kelas, String jurusan_id) {
        this.id = id;
        this.nama_kelas = nama_kelas;
        this.jurusan_id = jurusan_id;
    }

    public int getId() {
        return id;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public String getJurusan_id() {
        return jurusan_id;
    }
}
