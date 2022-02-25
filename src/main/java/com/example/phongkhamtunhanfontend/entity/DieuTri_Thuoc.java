package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class DieuTri_Thuoc implements Serializable {
    private Thuoc thuoc;
    private Integer soLuong;
}
