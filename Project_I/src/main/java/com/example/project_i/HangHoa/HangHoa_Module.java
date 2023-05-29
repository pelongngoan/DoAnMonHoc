package com.example.project_i.HangHoa;

import java.time.LocalDate;

public class HangHoa_Module {
    public Integer maHang;
    public String tenHang;
    public String donViTinh;
    public String phanLoai;
    public Double giaNhap;
    public Double giaXuat;
    public Double giaBanLe;
    public Double tonKho;

    public HangHoa_Module() {
    }

    public HangHoa_Module(Integer maHang, String tenHang) {
        this.maHang = maHang;
        this.tenHang = tenHang;
    }

    public HangHoa_Module(Integer maHang, String tenHang, String donViTinh, String phanLoai, Double giaNhap, Double giaXuat, Double giaBanLe,Double tonKho) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.donViTinh = donViTinh;
        this.phanLoai = phanLoai;
        this.giaNhap = giaNhap;
        this.giaXuat = giaXuat;
        this.giaBanLe = giaBanLe;
        this.tonKho = tonKho;
    }

    public HangHoa_Module(Integer maHang,String tenHang, String donViTinh, Double giaNhap) {
        this.maHang= maHang;
        this.tenHang = tenHang;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
    }

    public Integer getMaHang() {
        return maHang;
    }

    public void setMaHang(Integer maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }


    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Double getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(Double giaXuat) {
        this.giaXuat = giaXuat;
    }

    public Double getGiaBanLe() {
        return giaBanLe;
    }

    public void setGiaBanLe(Double giaBanLe) {
        this.giaBanLe = giaBanLe;
    }

    public Double getTonKho() {
        return tonKho;
    }

    public void setTonKho(Double tonKho) {
        this.tonKho = tonKho;
    }
}
