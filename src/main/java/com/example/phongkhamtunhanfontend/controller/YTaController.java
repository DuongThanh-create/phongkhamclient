package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.BacSy;
import com.example.phongkhamtunhanfontend.entity.YTa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/yta")
public class YTaController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/them")
    public String getViewYTa(Model model){
        model.addAttribute("yta",new YTa());
        return "yta/themyta";
    }
    @PostMapping("/them")
    public String create(YTa yTa, Model model){
        yTa = rest.postForObject("http://localhost:8085/yta",yTa, yTa.getClass());
        model.addAttribute("yta",yTa);
        return "yta/thanhcong";
    }
    @GetMapping("/sua/{id}")
    public String getViewYTaById(@PathVariable Long id, Model model){
        YTa yTa = rest.getForObject("http://localhost:8085/yta/{id}",YTa.class,id);
        model.addAttribute("yta",yTa);
        return "yta/suayta";
    }
    @PostMapping("/sua/{id}")
    public  String updateYTa(YTa yTa,Model model){
        rest.put("http://localhost:8085/yta/{id}",yTa,yTa.getId());
        model.addAttribute("yta",yTa);
        return "yta/thanhcong";
    }
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        rest.delete("http://localhost:8085/yta/{id}",id);
        return "redirect:/yta/all";
    }
    @GetMapping("/timkiem")
    public String timkiem(Model model){
        model.addAttribute("ytas",null);
        return "yta/timkiemyta";
    }
    @PostMapping("/timkiem")
    public String ketquaTimKiem(@RequestParam(name="duLieu") String duLieu,@RequestParam(name="thuocTinh") String thuocTinh, Model model){
        List<YTa> yTas = Arrays.asList(rest.getForObject("http://localhost:8085/yta?duLieu="+duLieu+"&thuocTinh="+thuocTinh,YTa[].class));
        model.addAttribute("ytas",yTas);
        return "yta/timkiemyta";
    }
    @GetMapping("/all")
    public String getDanhSach(Model model){
        List<YTa> ytas = Arrays.asList(rest.getForObject("http://localhost:8085/yta/all",YTa [].class));
        model.addAttribute("ytas",ytas);
        return "yta/danhsach";
    }
}
