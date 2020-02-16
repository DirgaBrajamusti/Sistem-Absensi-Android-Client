package xyz.dirgabrajamusti.sistemabsensi;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Login";
    private static final String LOGIN_STATE = "false";
    private static  final String DATA_USERNAME = "username";
    private static  final String DATA_NPM = "npm";
    private static  final String DATA_PASSWORD = "password";
    private static  final String DATA_CONFIRM_PASSWORD = "confirm_password";
    private static final String DATA_TOKEN_API = "api";
    private static final String DATA_KELAS = "kelas";

    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setRegister(String username,String npm,String password, String confirmpassword){
        editor.putString(DATA_USERNAME,username);
        editor.putString(DATA_NPM,npm);
        editor.putString(DATA_PASSWORD,password);
        editor.putString(DATA_CONFIRM_PASSWORD,confirmpassword);
        editor.commit();
    }

    public void setLoginState(String lg){
        editor.putString(LOGIN_STATE,lg);
        editor.commit();
    }
    public void setToken(String token){
        editor.putString(DATA_TOKEN_API,token);
        editor.commit();
    }
    public void setKelas(String kelas){
        editor.putString(DATA_KELAS, kelas);
        editor.commit();
    }


    public String getUsername(){
        return pref.getString(DATA_USERNAME,null);
    }
    public String getNPM(){
        return pref.getString(DATA_NPM,null);
    }
    public String getPassword(){
        return pref.getString(DATA_PASSWORD,null);
    }
    public String getConfirmPassword(){
        return pref.getString(DATA_CONFIRM_PASSWORD,null);
    }

    public String getDataTokenApi() {
        return pref.getString(DATA_TOKEN_API,null);
    }
    public String getDataKelas(){
        return pref.getString(DATA_KELAS,null);
    }

    public boolean login()
    {
        if (!pref.getString(LOGIN_STATE,"false").equals("false"))
            return true;
        return false;
    }

    public void clearData(){
        editor.clear();
        editor.commit();
    }
}