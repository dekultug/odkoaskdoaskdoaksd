package com.example.luyentap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    HoaDon_NgaySinh hoaDonNgaySinh;

    private EditText editTextTen;

    private EditText editTextTien;

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        hoaDonNgaySinh = (HoaDon_NgaySinh) getIntent().getSerializableExtra("tunglv");

        editTextTen = findViewById(R.id.edtTen);

        editTextTien = findViewById(R.id.edtSoTien);

        back = findViewById(R.id.btnback);


        editTextTen.setText(hoaDonNgaySinh.getHoTen());

        editTextTien.setText(String.valueOf(hoaDonNgaySinh.getSoTien()));


    }
}
