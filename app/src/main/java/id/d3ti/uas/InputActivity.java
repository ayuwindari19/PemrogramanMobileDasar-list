package id.d3ti.uas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import id.d3ti.uas.config.Barang;
import id.d3ti.uas.config.BarangDAO;
import id.d3ti.uas.config.BarangDAOImp;

public class InputActivity extends AppCompatActivity {

    private BarangDAO dao;
    private Barang barang;
    private EditText tvId, tvNama, tvJumlah, tvHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dao = new BarangDAOImp(this);
        barang = new Barang();

        tvId = (EditText) findViewById(R.id.tvId);
        tvNama = (EditText) findViewById(R.id.tvNama);
        tvJumlah = (EditText) findViewById(R.id.tvJumlah);
        tvHarga = (EditText) findViewById(R.id.tvHarga);
    }

    public void btnSimpan(View v) {
        int id = Integer.valueOf(tvId.getText().toString());
        String nama = tvNama.getText().toString();
        int jumlah = Integer.valueOf(tvJumlah.getText().toString());
        int harga = Integer.valueOf(tvHarga.getText().toString());
        int totalharga = (jumlah * harga);

        if (id > 0 && nama.length() > 0 && jumlah > 0 && harga > 0) {
            barang.setId(id);
            barang.setNama(nama);
            barang.setJumlah(jumlah);
            barang.setHarga(harga);
            barang.setTotalharga(totalharga);
            dao.create(barang);
            Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InputActivity.this, MainActivity.class));
            finish();

        } else {
            Toast.makeText(this, "Inputan masih kosong!", Toast.LENGTH_SHORT).show();
        }
    }
}
