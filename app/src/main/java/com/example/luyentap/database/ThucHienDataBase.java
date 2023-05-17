package com.example.luyentap.database;

import static com.example.luyentap.database.Sqlite_Made_Helper.KEY_HO_TEN;
import static com.example.luyentap.database.Sqlite_Made_Helper.KEY_MA;
import static com.example.luyentap.database.Sqlite_Made_Helper.KEY_SO_NGAY_LUU_TRU;
import static com.example.luyentap.database.Sqlite_Made_Helper.KEY_SO_PHONG;
import static com.example.luyentap.database.Sqlite_Made_Helper.KEY_SO_TIEN;
import static com.example.luyentap.database.Sqlite_Made_Helper.TEN_DATA_BASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.luyentap.HoaDon_NgaySinh;

import java.util.ArrayList;
import java.util.List;

public class ThucHienDataBase {
    private Sqlite_Made_Helper database;
    private Context context;

    public ThucHienDataBase(Sqlite_Made_Helper database) {
        this.database = database;
    }

    public ThucHienDataBase(Sqlite_Made_Helper database, Context context) {
        this.database = database;
        this.context = context;
    }

    public void themDuLieu(HoaDon_NgaySinh hoaDonNgaySinh) {
        SQLiteDatabase db = database.getWritableDatabase();

        /***\
         * public static String KEY_HO_TEN = "hoTen";
         *
         *     public static String KEY_SO_PHONG = "soPhong";
         *
         *     public static String KEY_SO_TIEN = "soTien";
         *
         *     public static String KEY_SO_NGAY_LUU_TRU = "soNgayLuuTru";
         */

        // điền các dữ liệu vào các cột
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MA, hoaDonNgaySinh.getMa());

        contentValues.put(KEY_HO_TEN, hoaDonNgaySinh.getHoTen());

        contentValues.put(KEY_SO_PHONG, hoaDonNgaySinh.getSoPhong());

        contentValues.put(KEY_SO_TIEN, hoaDonNgaySinh.getSoTien());

        contentValues.put(KEY_SO_NGAY_LUU_TRU, hoaDonNgaySinh.getSoNgayLuuTru());

        db.insert(TEN_DATA_BASE, null, contentValues);
    }

    public List<HoaDon_NgaySinh> layToanBoDuLieu() {
        SQLiteDatabase db = database.getReadableDatabase();

        ArrayList<HoaDon_NgaySinh> list = new ArrayList<>();

        String[] tenCacCot = {KEY_MA, KEY_HO_TEN, KEY_SO_PHONG, KEY_SO_TIEN, KEY_SO_NGAY_LUU_TRU};

        String dieuKienLoc = null;

        String[] mangGiaTri = null;

        String groupBy = null;

        String having = null;

        String orderBy = KEY_SO_PHONG + " desc";

        String limit = null;

        int sum = 0;


        /**
         * select * from
         *
         * select tencCot, tenMa form
         *
         * public Cursor query(String table, String[] columns, String selection,
         *             String[] selectionArgs, String groupBy, String having,
         *             String orderBy, String limit) {
         */

        Cursor cursor = db.query(TEN_DATA_BASE, tenCacCot, dieuKienLoc, mangGiaTri, groupBy, having, orderBy, limit);

        /**
         * this.ma = ma;
         *         this.hoTen = hoTen;
         *         this.soPhong = soPhong;
         *         this.soTien = soTien;
         *         this.soNgayLuuTru = soNgayLuuTru;
         */


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HoaDon_NgaySinh hoaDonNgaySinh = new HoaDon_NgaySinh(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getInt(4)
            );

            try {
                double soTien = Double.parseDouble(cursor.getString(3));
                int soNgay = Integer.parseInt(cursor.getString(4));

                sum += (soNgay * soTien);

            } catch (Exception e) {

            }

            list.add(hoaDonNgaySinh);
            cursor.moveToNext();
        }
        cursor.close();
        Toast.makeText(context, "tong so tien laf " + sum, Toast.LENGTH_SHORT).show();
        ;
        return list;
    }


    public List<HoaDon_NgaySinh> layDuLieuDanhSachCoSoTienLonHon(Double tien) {
        SQLiteDatabase db = database.getReadableDatabase();

        ArrayList<HoaDon_NgaySinh> list = new ArrayList<>();

        String[] tenCacCot = {KEY_MA, KEY_HO_TEN, KEY_SO_PHONG, KEY_SO_TIEN, KEY_SO_NGAY_LUU_TRU};

        String dieuKienLoc = null;

        String[] mangGiaTri = null;

        String groupBy = null;

        String having = null;

        String orderBy = KEY_SO_PHONG + " desc";

        String limit = null;


        Cursor cursor = db.query(TEN_DATA_BASE, tenCacCot, dieuKienLoc, mangGiaTri, groupBy, having, orderBy, limit);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                try {
                    double soTien = Double.parseDouble(cursor.getString(3));
                    if (soTien > tien) {
                        HoaDon_NgaySinh hoaDonNgaySinh = new HoaDon_NgaySinh(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getDouble(3),
                                cursor.getInt(4)
                        );
                        list.add(hoaDonNgaySinh);
                    }
                } catch (Exception e) {
                    list.addAll(new ArrayList<>());
                }
                cursor.moveToNext();
            }
        } else {
            list.addAll(new ArrayList<>());
        }

        return list;
    }

    public void uodateDuLieu(HoaDon_NgaySinh hoaDonNgaySinh) {
        SQLiteDatabase db = database.getWritableDatabase();

        /***\
         * public static String KEY_HO_TEN = "hoTen";
         *
         *     public static String KEY_SO_PHONG = "soPhong";
         *
         *     public static String KEY_SO_TIEN = "soTien";
         *
         *     public static String KEY_SO_NGAY_LUU_TRU = "soNgayLuuTru";
         */

        String dieuKienLoc = KEY_MA + " = ?";

        String[] mangGiaTri = {String.valueOf(hoaDonNgaySinh.getMa())};

        // điền các dữ liệu vào các cột
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MA, hoaDonNgaySinh.getMa());

        contentValues.put(KEY_HO_TEN, hoaDonNgaySinh.getHoTen());

        contentValues.put(KEY_SO_PHONG, hoaDonNgaySinh.getSoPhong());

        contentValues.put(KEY_SO_TIEN, hoaDonNgaySinh.getSoTien());

        contentValues.put(KEY_SO_NGAY_LUU_TRU, hoaDonNgaySinh.getSoNgayLuuTru());

        db.update(TEN_DATA_BASE, contentValues, dieuKienLoc, mangGiaTri);
    }

    public void deleteDuLieu(String ten) {
        SQLiteDatabase db = database.getWritableDatabase();

        String dieuKienLoc = KEY_HO_TEN + " = ?";

        String[] mangGiaTri = {ten};

        db.delete(TEN_DATA_BASE,dieuKienLoc,mangGiaTri);
    }
}
