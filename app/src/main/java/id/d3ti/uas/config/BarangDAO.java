package id.d3ti.uas.config;

import android.database.Cursor;

public interface BarangDAO {

    Cursor read();

    boolean create(Barang barang);

}
