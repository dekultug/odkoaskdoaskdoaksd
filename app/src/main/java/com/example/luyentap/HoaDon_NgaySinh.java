package com.example.luyentap;

import java.io.Serializable;

public class HoaDon_NgaySinh implements Serializable {

    private int ma;

    private String hoTen;

    private int soPhong;

    private double soTien;

    private int soNgayLuuTru;


    public HoaDon_NgaySinh(int ma, String hoTen, int soPhong, double soTien, int soNgayLuuTru) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.soPhong = soPhong;
        this.soTien = soTien;
        this.soNgayLuuTru = soNgayLuuTru;
    }

    public HoaDon_NgaySinh(String hoTen, int soPhong, double soTien, int soNgayLuuTru) {
        this.hoTen = hoTen;
        this.soPhong = soPhong;
        this.soTien = soTien;
        this.soNgayLuuTru = soNgayLuuTru;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public int getSoNgayLuuTru() {
        return soNgayLuuTru;
    }

    public void setSoNgayLuuTru(int soNgayLuuTru) {
        this.soNgayLuuTru = soNgayLuuTru;
    }
}
