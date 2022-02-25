package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;

@Data
public class Luong {
    private Long id;
    private String thang;
    private Double tien;
    private NhanVien nhanVien;
}
