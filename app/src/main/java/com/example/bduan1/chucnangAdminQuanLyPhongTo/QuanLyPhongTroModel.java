package com.example.bduan1.chucnangAdminQuanLyPhongTo;

public class QuanLyPhongTroModel {
    private String documentId;
    private String tenPhong;
    private String trangThai;
    private double tienPhong;
    private double tienDien;
    private double tienNuoc;
    private double phuPhi;
    private String ngayBatDau; // Thêm trường ngày bắt đầu
    private String ngayKetThuc; // Thêm trường ngày kết thúc
    private String yeuCau;
    private String trangThaiYeuCau;

    public QuanLyPhongTroModel(String yeuCau, String trangThaiYeuCau) {
        this.yeuCau = yeuCau;
        this.trangThaiYeuCau = trangThaiYeuCau;
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }

    public String getTrangThaiYeuCau() {
        return trangThaiYeuCau;
    }

    public void setTrangThaiYeuCau(String trangThaiYeuCau) {
        this.trangThaiYeuCau = trangThaiYeuCau;
    }

    public QuanLyPhongTroModel() {
    }

    // Constructor, getter và setter
    public QuanLyPhongTroModel(String tenPhong, String trangThai, double tienPhong, double tienDien, double tienNuoc, double phuPhi, String ngayBatDau, String ngayKetThuc) {
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.tienPhong = tienPhong;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.phuPhi = phuPhi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    // Getter và setter cho ngày bắt đầu và ngày kết thúc
    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    // Các getter và setter khác
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(double tienPhong) {
        this.tienPhong = tienPhong;
    }

    public double getTienDien() {
        return tienDien;
    }

    public void setTienDien(double tienDien) {
        this.tienDien = tienDien;
    }

    public double getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(double tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public double getPhuPhi() {
        return phuPhi;
    }

    public void setPhuPhi(double phuPhi) {
        this.phuPhi = phuPhi;
    }
}
