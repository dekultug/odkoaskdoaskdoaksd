package com.example.luyentap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite_Made_Helper extends SQLiteOpenHelper {

    public static String KEY_BANG = "SQLITE_MA_DE";

    public static int KEY_VERSION = 1;

    public static String TEN_DATA_BASE = "HOA_DON_NGAY_SINH";

    /**
     * private int ma;
     * <p>
     * private String hoTen;
     * <p>
     * private int soPhong;
     * <p>
     * private double soTien;
     * <p>
     * private int soNgayLuuTru;
     */

    public static String KEY_MA = "ma";

    public static String KEY_HO_TEN = "hoTen";

    public static String KEY_SO_PHONG = "soPhong";

    public static String KEY_SO_TIEN = "soTien";

    public static String KEY_SO_NGAY_LUU_TRU = "soNgayLuuTru";


    public Sqlite_Made_Helper(Context context) {
        super(context, KEY_BANG, null, KEY_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo bảng
        String taoBang = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,  %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TEN_DATA_BASE, KEY_MA,KEY_HO_TEN, KEY_SO_PHONG, KEY_SO_TIEN, KEY_SO_NGAY_LUU_TRU);
        db.execSQL(taoBang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // có thể nâng phiên
        // thực hiện thêm sửa xóa
        String xoaBang = String.format("DROP TABLE IF EXISTS %s", TEN_DATA_BASE);
        db.execSQL(xoaBang);

        // tạo lại
        onCreate(db);
    }
}
