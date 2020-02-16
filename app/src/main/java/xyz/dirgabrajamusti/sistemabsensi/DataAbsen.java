package xyz.dirgabrajamusti.sistemabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.dirgabrajamusti.sistemabsensi.API.HolderApi;
import xyz.dirgabrajamusti.sistemabsensi.Model.Absensi;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseAbsensi;

public class DataAbsen extends AppCompatActivity {
    TextView textViewResult;
    private HolderApi holderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_absen);
        textViewResult = findViewById(R.id.text_view_result);

    }
    private void getAbsensi() {

        Call<ResponseAbsensi> call = holderApi.getAbsensi();
        call.enqueue(new Callback<ResponseAbsensi>() {
            @Override
            public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {

            }

            @Override
            public void onFailure(Call<ResponseAbsensi> call, Throwable t) {

            }
        });

    }
}
