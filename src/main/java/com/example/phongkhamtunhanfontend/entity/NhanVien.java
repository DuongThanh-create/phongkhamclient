package com.example.phongkhamtunhanfontend.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class NhanVien implements Serializable {
    private Long id;
    private String maNV;
    private String hoTen;
    private String trinhDo;
    private String thamNien;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;
    private String cmt;
    private String sdt;
    private String diaChi;
    private String viTri;
    private Double luongCoBan;
    private Double luongCongThem;
}
