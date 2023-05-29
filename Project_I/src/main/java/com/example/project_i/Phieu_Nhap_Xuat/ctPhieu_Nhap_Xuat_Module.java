package com.example.project_i.Phieu_Nhap_Xuat;

import javafx.scene.control.TextField;

public class ctPhieu_Nhap_Xuat_Module extends phieu_Nhap_Xuat_Module {
    public Double soLuong;
    public Double total;
    public TextField textField;

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public ctPhieu_Nhap_Xuat_Module(Double soLuong, Double total) {
        this.soLuong = soLuong;
        this.total = total;
    }

    public ctPhieu_Nhap_Xuat_Module(Integer maHang, String tenHang, String donViTinh, Double soLuong, Double giaNhap, Double total) {
        super(maHang,tenHang,donViTinh,giaNhap);
        this.soLuong = soLuong;
        this.total = total;
    }

    public ctPhieu_Nhap_Xuat_Module(TextField textField) {
        this.textField = textField;
    }

    public Double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
