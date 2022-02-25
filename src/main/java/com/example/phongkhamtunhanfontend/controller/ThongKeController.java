package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.BenhNhan;
import com.example.phongkhamtunhanfontend.entity.ThongKeBenh;
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
@RequestMapping("/thongke")
public class ThongKeController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/benh")
    public String getThongKeBenhView(Model model){
        model.addAttribute("listthongkebenh",null);
        return "thongke/thongkebenh";
    }
    @PostMapping("/benh")
    public String getThongKeBenh(Model model, @RequestParam(name="thang") String thang){
        try{
            List<ThongKeBenh> listThongKe = Arrays.asList(rest.getForObject("http://localhost:8085/thongkebenh?thang="+thang, ThongKeBenh[].class));
            model.addAttribute("listthongkebenh",listThongKe);
            model.addAttribute("thang",thang);
        }catch (Exception e){
            model.addAttribute("listthongkebenh",null);

        }
        return "thongke/thongkebenh";
    }

}
