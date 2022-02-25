package com.example.phongkhamtunhanfontend.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ThongKeBenh implements Serializable {
    private Benh benh;
    private Integer soLuong;
}
