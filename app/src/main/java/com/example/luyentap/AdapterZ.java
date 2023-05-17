package com.example.luyentap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterZ extends BaseAdapter {

    private ArrayList<HoaDon_NgaySinh> list = new ArrayList<>();

    public IOnClick iOnClick = null;

    private TextView tvTen;

    private TextView tvSoPhong;

    private TextView tvSoTien;

    void themDuLieuVaoAdapter(List<HoaDon_NgaySinh> duLieuThemVao) {
        list.clear();
        list.addAll(duLieuThemVao);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        tvTen = view.findViewById(R.id.tvItemTen);

        tvSoPhong = view.findViewById(R.id.tvItemPhong);

        tvSoTien = view.findViewById(R.id.tvItemTien);

        HoaDon_NgaySinh hoaDonNgaySinh = (HoaDon_NgaySinh) getItem(position);

        tvTen.setText(hoaDonNgaySinh.getHoTen());

        tvSoTien.setText(  String.valueOf(hoaDonNgaySinh.getSoTien())   );

        tvSoPhong.setText(   "Phonghf : " +  String.valueOf(hoaDonNgaySinh.getSoPhong())   );

        tvSoTien.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                iOnClick.clickItem(hoaDonNgaySinh);

                return true;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClick.truyenData(hoaDonNgaySinh);
            }
        });

        return view;
    }


    interface IOnClick{
        public void clickItem(HoaDon_NgaySinh hoaDonNgaySinh);

        public void truyenData(HoaDon_NgaySinh hoaDonNgaySinh);
    }

}
