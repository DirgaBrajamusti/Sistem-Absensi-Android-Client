package xyz.dirgabrajamusti.sistemabsensi.Model;

public class Data {
    private String token;
    private String name;

    public Data(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }
}
