package xyz.dirgabrajamusti.sistemabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, conPassword;
    private HolderApi holderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editText_npm);
        email = findViewById(R.id.editText_npm);
        password = findViewById(R.id.editText_Password);
        conPassword = findViewById(R.id.editText_ConPassword);

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        holderApi = retrofit.create(HolderApi.class);
    }

    public void registrasi(View view) {
        String dataUser = username.getText().toString().trim();
        String dataEmail = email.getText().toString().trim() + "@poltekpos";
        String dataPass = password.getText().toString().trim();
        String dataConPass = conPassword.getText().toString().trim();
        if(!dataUser.isEmpty()){
            Call<ResponseLogin> responseLoginCall = holderApi.registerRequest(dataUser,dataEmail,dataPass,dataConPass);
            responseLoginCall.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    ResponseLogin result = response.body();
                    if(result.getSuccess()){
                        Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }else{
                        ResponseLogin responseLogin = response.body();
                        Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"Data tidak boleh ada yang kosong",Toast.LENGTH_LONG).show();
        }
    }

    public void login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
