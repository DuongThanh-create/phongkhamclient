package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
@Data
public class DieuTri implements Serializable {
    private Long id;
    private String cachDung;
    private Set<DieuTri_Thuoc> dieuTri_Thuocs;
}
