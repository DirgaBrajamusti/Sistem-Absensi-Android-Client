package xyz.dirgabrajamusti.sistemabsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    PrefManager pref;
    String npm, nama, kelas, mata_kuliah, minggu, inputQR;
    EditText et_matakuliah, et_minggu;
    ImageView QRImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefManager pref = new PrefManager(getApplicationContext());

        if (!pref.login()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        // Set User
        TextView welcome = findViewById(R.id.tv_namaUser);
        welcome.setText("This is your QR Code, " + pref.getUsername());

        // Set Kelas
        final TextView tv_kelas = findViewById(R.id.tv_kelas);
        if(pref.getDataKelas()==null){
            tv_kelas.setText("Silahkan set kode kelas anda pada bagian option");
        }else{
            tv_kelas.setText("Kode Kelas: " + pref.getDataKelas());
        }


        // Set Data
        npm = pref.getNPM();
        nama = pref.getUsername();
        kelas = pref.getDataKelas();
        generateQR();

        // Set Mata Kuliah dan Minggu
        et_matakuliah = findViewById(R.id.et_kodeMataKuliah);
        et_minggu = findViewById(R.id.et_minggu);
        et_matakuliah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mata_kuliah = s.toString();
                generateQR();
            }
        });
        et_minggu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                minggu = s.toString();
                generateQR();
            }
        });
    }

    public void generateQR(){
        inputQR = "npm=" + npm + "&" + "nama=" + nama + "&" + "kelas=" + kelas + "&" + "mata_kuliah=" + mata_kuliah+ "&" + "minggu=" + minggu;
        QRImage = findViewById(R.id.imageView_QR);
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(inputQR, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            QRImage.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.Logout) {
            PrefManager pref = new PrefManager(getApplicationContext());
            pref.clearData();
            Toast.makeText(getApplicationContext(), "Logout Berhasil", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (menuItem.getItemId() == R.id.setKelas) {
            startActivity(new Intent(this, SetKelas.class));
        }
        return true;
    }
}
