package xyz.dirgabrajamusti.sistemabsensi.Model;

public class ResponseKelas {
    private Boolean success;
    private Kelas kelas;
    private String message;

    public ResponseKelas(Boolean success, Kelas kelas, String message) {
        this.success = success;
        this.kelas = kelas;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public String getMessage() {
        return message;
    }
}
