package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.BacSy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/bacsy")
public class BacSyController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/them")
    public String getViewBacSy(Model model){
        model.addAttribute("bacsy",new BacSy());
        return "bacsy/thembacsy";
    }
    @PostMapping("/them")
    public String create(BacSy bacSy, Model model){
        bacSy = rest.postForObject("https://phongkhamserver.herokuapp.com/bacsy", bacSy, BacSy.class);
        model.addAttribute("bacsy",bacSy);
        return "bacsy/thanhcong";
    }
    @GetMapping("/sua/{id}")
    public String getViewBacSyById( @PathVariable Long id,Model model){
        BacSy bacSy = rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy/{id}",BacSy.class,id);
        model.addAttribute("bacsy",bacSy);
        return "bacsy/suabacsy";
    }
    @PostMapping("/sua/{id}")
    public  String updateBacSy(BacSy bacSy,Model model){
        rest.put("https://phongkhamserver.herokuapp.com/bacsy/{id}",bacSy,bacSy.getId());
        model.addAttribute("bacsy",bacSy);
        return "bacsy/thanhcong";
    }
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        rest.delete("https://phongkhamserver.herokuapp.com/bacsy/{id}",id);
        return "redirect:/bacsy/all";
    }
    @GetMapping("/timkiem")
    public String timkiem(Model model){
        model.addAttribute("bacsys",null);
        return "bacsy/timkiembacsy";
    }
    @PostMapping("/timkiem")
    public String ketquaTimKiem(@RequestParam(name="duLieu") String duLieu,@RequestParam(name="thuocTinh") String thuocTinh, Model model){
        List<BacSy> bacSys = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy?duLieu="+duLieu+"&thuocTinh="+thuocTinh,BacSy[].class));
        model.addAttribute("bacsys",bacSys);
        return "bacsy/timkiembacsy";
    }
    @GetMapping("/all")
    public String getDanhSach(Model model){
        List<BacSy> bacSys = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy/all",BacSy[].class));
        model.addAttribute("bacsys",bacSys);
        return "bacsy/danhsach";
    }
    @GetMapping("/chitiet/{id}")
    public String getBacSy(@PathVariable Long id,Model model){
        BacSy bacSy = rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy/{id}",BacSy.class,id);
        model.addAttribute("bacsy",bacSy);
        return "bacsy/chitiet";
    }

}
