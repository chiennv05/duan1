package com.example.bduan1.QuanLyHoaDon;


public class HoaDon {

    private String tenPhong;
    private String soDien;
    private String giaPhong;
    private String tienDien;
    private String tienNuoc;
    private String tienDichVu;
    private String soNguoi;
    private double tongTien;
    public HoaDon() {
        // Constructor mặc định, cần thiết cho Firebase
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public HoaDon(String tenPhong, String soDien, String giaPhong, String tienDien, String tienNuoc, String tienDichVu, String soNguoi, double tongTien) {
        this.tenPhong = tenPhong;
        this.soDien = soDien;
        this.giaPhong = giaPhong;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.tienDichVu = tienDichVu;
        this.soNguoi = soNguoi;
        this.tongTien = tongTien;
    }

    // Getters and setters
    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getSoDien() {
        return soDien;
    }

    public void setSoDien(String soDien) {
        this.soDien = soDien;
    }

    public String getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(String giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getTienDien() {
        return tienDien;
    }

    public void setTienDien(String tienDien) {
        this.tienDien = tienDien;
    }

    public String getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(String tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public String getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(String tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public String getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(String soNguoi) {
        this.soNguoi = soNguoi;
    }
}

