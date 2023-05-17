package com.example.luyentap;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.luyentap.database.Sqlite_Made_Helper;
import com.example.luyentap.database.ThucHienDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private ListView listView;

    private FloatingActionButton floatingActionButton;

    private AdapterZ adapterZ = new AdapterZ();

    private ArrayList<HoaDon_NgaySinh> hoaDonNgaySinhArrayList = new ArrayList<>();

    private Sqlite_Made_Helper sqlite_made_helper;

    private ThucHienDataBase dataBase;

    int dem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlite_made_helper = new Sqlite_Made_Helper(this.getApplicationContext());

        dataBase = new ThucHienDataBase(sqlite_made_helper,MainActivity.this);


        editText = findViewById(R.id.edtMain);

        listView = findViewById(R.id.lvMain);

        floatingActionButton = findViewById(R.id.fabButton);

        //
        listView.setAdapter(adapterZ);

        themDuLieu();

//        // fake data
//        for (int i = 0; i < 10; i++) {
//            hoaDonNgaySinhArrayList.add(new HoaDon_NgaySinh(1,"a",1,1,8));
//        }
//
//        /// đổ data vào adapter
//        adapterZ.themDuLieuVaoAdapter(hoaDonNgaySinhArrayList);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<HoaDon_NgaySinh> hoaDon_ngaySinhs = dataBase.layToanBoDuLieu();
                adapterZ.themDuLieuVaoAdapter(hoaDon_ngaySinhs);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double soTien = Double.parseDouble(s.toString().trim());
                    List<HoaDon_NgaySinh> list = dataBase.layDuLieuDanhSachCoSoTienLonHon(soTien);

                    adapterZ.themDuLieuVaoAdapter(list);


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "can kieu so", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        adapterZ.iOnClick = new AdapterZ.IOnClick() {
            @Override
            public void clickItem(HoaDon_NgaySinh hoaDonNgaySinh) {
                int demPhanTuLonHonSoTienHienTai = dataBase.layDuLieuDanhSachCoSoTienLonHon(hoaDonNgaySinh.getSoTien()).size();

                Toast.makeText(MainActivity.this, hoaDonNgaySinh.getHoTen() + "\n" + demPhanTuLonHonSoTienHienTai, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void truyenData(HoaDon_NgaySinh hoaDonNgaySinh) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("tunglv",hoaDonNgaySinh);

                intent.putExtras(bundle);

                startActivity(intent);


            }
        };

    }

    private void themDuLieu() {
        dataBase.themDuLieu(new HoaDon_NgaySinh(1,"Ten 1",201,1,2));
        dataBase.themDuLieu(new HoaDon_NgaySinh(2,"Ten 2",202,10,3));
        dataBase.themDuLieu(new HoaDon_NgaySinh(3,"Ten 3",203,16,4));
        dataBase.themDuLieu(new HoaDon_NgaySinh(4,"Ten 4",204,22,5));
        dataBase.themDuLieu(new HoaDon_NgaySinh(5,"Ten 5",205,30,6));
        dataBase.themDuLieu(new HoaDon_NgaySinh(6,"Ten 6",206,100,7));
    }
}
