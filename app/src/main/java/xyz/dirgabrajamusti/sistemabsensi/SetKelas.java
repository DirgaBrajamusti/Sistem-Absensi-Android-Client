package xyz.dirgabrajamusti.sistemabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import xyz.dirgabrajamusti.sistemabsensi.API.HolderApi;
import xyz.dirgabrajamusti.sistemabsensi.API.RetrofitFactory;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseKelas;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseLogin;

public class SetKelas extends AppCompatActivity {
    EditText et_setKelas;
    Button bt_setKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_kelas);

        et_setKelas = findViewById(R.id.et_kodeKelas);
        bt_setKelas = findViewById(R.id.bt_setKelas);

        bt_setKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager pref = new PrefManager(getApplicationContext());
                pref.setKelas(et_setKelas.getText().toString().trim());
                Toast.makeText(SetKelas.this,"Set data kelas Berhasil",Toast.LENGTH_LONG).show();
                startActivity(new Intent(SetKelas.this, MainActivity.class));
                finish();
            }
        });
    }
}
