package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Thuoc implements Serializable {
    private Long id;
    private String tenThuoc;
    private String maTH;
    private Double donGia;
}
