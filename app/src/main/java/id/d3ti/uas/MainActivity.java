package id.d3ti.uas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import id.d3ti.uas.config.BarangDAO;
import id.d3ti.uas.config.BarangDAOImp;

public class MainActivity extends AppCompatActivity {

    private BarangDAO barangDAO;
    private ListView tvBiodata;
    private FloatingActionButton fa;
    ArrayList<HashMap<String, String>> listBiodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fa = (FloatingActionButton) findViewById(R.id.fa);
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
                finish();
            }
        });

        barangDAO = new BarangDAOImp(this);
        tvBiodata = (ListView) findViewById(R.id.tvBiodata);

        ReadBiodata();
    }

    public void ReadBiodata() {
        listBiodata = new ArrayList<>();

        Cursor cursor = barangDAO.read();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("id", cursor.getString(0));
                hm.put("nama", cursor.getString(1));
                hm.put("jumlah", cursor.getString(2));
                hm.put("harga", cursor.getString(3));
                hm.put("totalharga", cursor.getString(4));
                listBiodata.add(hm);
            } while (cursor.moveToNext());
        }

        String[] key = {"id", "nama", "jumlah", "harga", "totalharga"};
        int[] komponen = {R.id.tvId, R.id.tvNama, R.id.tvJumlah, R.id.tvHarga, R.id.tvTotalHarga};
        SimpleAdapter adapter = new SimpleAdapter(this, listBiodata, R.layout.items, key, komponen);
        tvBiodata.setAdapter(adapter);
    }
}
