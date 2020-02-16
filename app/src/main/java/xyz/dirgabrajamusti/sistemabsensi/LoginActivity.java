package xyz.dirgabrajamusti.sistemabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import xyz.dirgabrajamusti.sistemabsensi.API.HolderApi;
import xyz.dirgabrajamusti.sistemabsensi.API.RetrofitFactory;
import xyz.dirgabrajamusti.sistemabsensi.Model.Data;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseLogin;

public class LoginActivity extends AppCompatActivity {
    private String data_username, data_password;
    private PrefManager prefManager;
    private EditText uname, pwd;
    private HolderApi holderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = findViewById(R.id.editText_npm);
        pwd = findViewById(R.id.editText_Password);

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        holderApi = retrofit.create(HolderApi.class);
    }

    public void login(View view) {
        data_username = uname.getText().toString().trim();
        data_password = pwd.getText().toString().trim();
        String data_user_login = uname.getText().toString().trim() + "@poltekpos";

        if(!data_username.isEmpty() && !data_password.isEmpty()){
            Call<ResponseLogin> responseLoginCall = holderApi.loginRequest(data_user_login,data_password);
            responseLoginCall.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(LoginActivity.this, response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    ResponseLogin result = response.body();
                    if(result.getSuccess()){
                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_LONG).show();
                        Data data = response.body().getData();
                        PrefManager pref = new PrefManager(getApplicationContext());
                        pref.setRegister(data_username,data_username,data_password,data_password);
                        pref.setLoginState("true");
                        pref.setToken(data.getToken());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        ResponseLogin responseLogin = response.body();
                        Toast.makeText(LoginActivity.this, "Login Gagal, Cek kembali npm atau password", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"NPM atau Password Tidak Boleh Kosong",Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
