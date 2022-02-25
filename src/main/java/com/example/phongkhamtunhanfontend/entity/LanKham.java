package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
public class LanKham implements Serializable {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayVaoVien;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayRaVien;
    private String maLanKham;
    private Double tongTienKham;
    private String tinhTrangKham;
    private BenhNhan benhNhan;
    private BacSy bacSy;
    private YTa yTa;
    private Benh benh;
    private DieuTri dieuTri;
}
