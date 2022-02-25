package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.Thuoc;
import com.example.phongkhamtunhanfontend.entity.YTa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("thuoc")
public class ThuocController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/them")
    public String getViewThuoc(Model model){
        model.addAttribute("thuoc",new Thuoc());
        return "thuoc/themthuoc";
    }
    @PostMapping("/them")
    public String create(Thuoc thuoc, Model model){
        thuoc = rest.postForObject("http://localhost:8085/thuoc",thuoc, thuoc.getClass());
        model.addAttribute("thuoc",thuoc);
        return "thuoc/thanhcong";
    }
    @GetMapping("/sua/{id}")
    public String getViewThuocById(@PathVariable Long id, Model model){
        Thuoc thuoc = rest.getForObject("http://localhost:8085/thuoc/{id}",Thuoc.class,id);
        model.addAttribute("thuoc",thuoc);
        return "thuoc/suathuoc";
    }
    @PostMapping("/sua/{id}")
    public  String updateThuoc(Thuoc thuoc,Model model){
        rest.put("http://localhost:8085/thuoc/{id}",thuoc,thuoc.getId());
        model.addAttribute("thuoc",thuoc);
        return "thuoc/thanhcong";
    }
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        rest.delete("http://localhost:8085/thuoc/{id}",id);
        return "index";
    }
    @GetMapping("/timkiem")
    public String timkiem(Model model){
        model.addAttribute("thuoc",null);
        return "thuoc/timkiemthuoc";
    }
    @PostMapping("/timkiem")
    public String ketquaTimKiem(@RequestParam(name="duLieu") String duLieu,@RequestParam(name="thuocTinh") String thuocTinh, Model model){
        List<Thuoc> thuocs = Arrays.asList(rest.getForObject("http://localhost:8085/thuoc?duLieu="+duLieu+"&thuocTinh="+thuocTinh,Thuoc[].class));
        model.addAttribute("thuocs",thuocs);
        return "thuoc/timkiemthuoc";
    }
    @GetMapping("/all")
    public String getDanhSach(Model model){
        List<Thuoc> thuocs = Arrays.asList(rest.getForObject("http://localhost:8085/thuoc/all",Thuoc [].class));
        model.addAttribute("thuocs",thuocs);
        return "thuoc/danhsach";
    }
}
