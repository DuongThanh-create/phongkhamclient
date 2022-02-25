package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class BenhNhan implements Serializable {
    private Long id;
    private String cmt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;
    private String maBN;
    private String sdt;
    private String diaChi;
    private String hoTen;
}
