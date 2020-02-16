package xyz.dirgabrajamusti.sistemabsensi.Model;

public class ResponseAbsensi {
    private Boolean success;
    private Absensi absensi;
    private String message;

    public ResponseAbsensi(Boolean success, Absensi absensi, String message) {
        this.success = success;
        this.absensi = absensi;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Absensi getAbsensi() {
        return absensi;
    }

    public String getMessage() {
        return message;
    }
}
