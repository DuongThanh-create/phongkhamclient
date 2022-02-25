package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.Luong;
import com.example.phongkhamtunhanfontend.entity.Thuoc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/luong")
public class LuongController {
    private RestTemplate rest = new RestTemplate();
    @PostMapping
    public String getLuong(@RequestParam(name="thang") String thang, Model model){
        List<Luong> luongs = Arrays.asList(rest.getForObject("http://localhost:8085/luong?thang="+thang,Luong[].class));
        model.addAttribute("luongs",luongs);
        model.addAttribute("thang",thang);
        return "luong/tinhluong";
    }
    @GetMapping
    public String getViewLuong(Model model){
        model.addAttribute("luongs",null);
        return "luong/tinhluong";
    }
}
