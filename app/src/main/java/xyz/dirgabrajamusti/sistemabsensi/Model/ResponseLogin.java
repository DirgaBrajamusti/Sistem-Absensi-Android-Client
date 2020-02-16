package xyz.dirgabrajamusti.sistemabsensi.Model;

public class ResponseLogin {
    private Boolean success;
    private Data data;
    private String message;

    public ResponseLogin(Boolean success, Data data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
