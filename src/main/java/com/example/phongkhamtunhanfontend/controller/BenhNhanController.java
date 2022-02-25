package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.BacSy;
import com.example.phongkhamtunhanfontend.entity.BenhNhan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("benhnhan")
public class BenhNhanController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/them")
    public String getViewBenhNhan(Model model){
        model.addAttribute("benhnhan",new BenhNhan());
        return "benhnhan/thembenhnhan";
    }
    @PostMapping("/them")
    public String create(BenhNhan benhNhan, Model model){
        benhNhan = rest.postForObject("https://phongkhamserver.herokuapp.com/benhnhan",benhNhan, BenhNhan.class);
        model.addAttribute("benhnhan",benhNhan);
        return "benhnhan/thanhcong";
    }
    @GetMapping("/sua/{id}")
    public String getViewBenhNhanById(@PathVariable Long id, Model model){
        BenhNhan benhNhan= rest.getForObject("https://phongkhamserver.herokuapp.com/benhnhan/{id}",BenhNhan.class,id);
        model.addAttribute("benhnhan",benhNhan);
        return "benhnhan/suabenhnhan";
    }
    @PostMapping("/sua/{id}")
    public  String updateBenhNhan(BenhNhan benhNhan,Model model){
        rest.put("https://phongkhamserver.herokuapp.com/benhnhan/{id}",benhNhan,benhNhan.getId());
        model.addAttribute("benhnhan",benhNhan);
        return "benhnhan/thanhcong";
    }
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        rest.delete("https://phongkhamserver.herokuapp.com/benhnhan/{id}",id);
        return "redirect:/benhnhan/all";
    }
    @GetMapping("/timkiem")
    public String timkiem(Model model){
        model.addAttribute("benhnhans",null);
        return "benhnhan/timkiembenhnhan";
    }
    @PostMapping("/timkiem")
    public String ketquaTimKiem(@RequestParam(name="duLieu") String duLieu,@RequestParam(name="thuocTinh") String thuocTinh, Model model){
        List<BenhNhan> benhNhans = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/benhnhan?duLieu="+duLieu+"&thuocTinh="+thuocTinh,BenhNhan[].class));
        model.addAttribute("benhnhans",benhNhans);
        return "benhnhan/timkiembenhnhan";
    }
    @GetMapping("/all")
    public String getDanhSach(Model model){
        List<BenhNhan> benhNhans = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/benhnhan/all",BenhNhan[].class));
        model.addAttribute("benhnhans",benhNhans);
        return "benhnhan/danhsach";
    }
}
