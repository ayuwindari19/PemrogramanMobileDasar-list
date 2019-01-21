package id.d3ti.uas.config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BarangDAOImp extends SQLiteOpenHelper implements BarangDAO {

    public BarangDAOImp(Context context) {
        super(context, "db_biodata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_barang (id INTEGER PRIMARY KEY, nama TEXT, jumlah TEXT, harga TEXT, totalharga TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE tbl_barang");
    }

    @Override
    public Cursor read() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbl_barang", null);
    }


    @Override
    public boolean create(Barang barang) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_barang VALUES ('" + barang.getId() + "','" + barang.getNama() + "','" + barang.getJumlah() + "','" + barang.getHarga() + "','" + barang.getTotalharga()+"')");
        return true;
    }

}
