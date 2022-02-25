package com.example.phongkhamtunhanfontend.controller;

import com.example.phongkhamtunhanfontend.entity.*;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Data
@RequestMapping("/lankham")
public class LanKhamController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/them")
    public String getThemLanKham(Model model){
        List<BacSy> bacSys = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy/all",BacSy[].class));
        List<YTa> yTas = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/yta/all",YTa[].class));
        List<BenhNhan> benhNhans = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/benhnhan/all",BenhNhan[].class));
        List<Thuoc> thuocs = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/thuoc/all",Thuoc[].class));
        List<Benh> benhs = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/benh/all",Benh[].class));
        model.addAttribute("bacsys",bacSys);
        model.addAttribute("ytas",yTas);
        model.addAttribute("benhnhans",benhNhans);
        model.addAttribute("thuocs",thuocs);
        model.addAttribute("benhs",benhs);
        return "/lankham/themlankham";
    }
    @PostMapping("/them")
    public String themLanKham(@RequestParam(name="bacsy") Long idBS, @RequestParam(name="yta") Long idYTa,Model model,
                              @RequestParam(name="benhnhan") Long idBN,@RequestParam(name="benh") Long idBenh,
                              @RequestParam(name="idthuocs",required = false) Long[] idThuocs, @RequestParam(name="soluongs",required = false) Integer[] soLuongs,
                              @RequestParam(name = "cachdung") String cachDung,@RequestParam(name="malankham") String maLK,
                              @RequestParam(name="ngayvaovien") String ngayVaoVien,@RequestParam(name="ngayravien") String ngayRaVien){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LanKham lanKham = new LanKham();
        lanKham.setMaLanKham(maLK);
        try {
            lanKham.setNgayRaVien(sdf.parse(ngayRaVien));
            lanKham.setNgayVaoVien(sdf.parse(ngayVaoVien));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BacSy bacSy = new BacSy();
        bacSy.setId(idBS);
        YTa yTa = new YTa();
        yTa.setId(idYTa);
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setId(idBN);
        Benh benh = new Benh();
        benh.setId(idBenh);
        if (idThuocs!=null) {
            DieuTri dieuTri = new DieuTri();
            Set<DieuTri_Thuoc> dts = new HashSet<>();
            dieuTri.setDieuTri_Thuocs(dts);
            for (int i = 0; i < idThuocs.length; i++) {
                DieuTri_Thuoc dieuTri_thuoc = new DieuTri_Thuoc();
                Thuoc thuoc = new Thuoc();
                thuoc.setId(idThuocs[i]);
                dieuTri_thuoc.setThuoc(thuoc);
                dieuTri_thuoc.setSoLuong(soLuongs[i]);
                dts.add(dieuTri_thuoc);
            }
            dieuTri.setCachDung(cachDung);
            lanKham.setDieuTri(dieuTri);
        }

        lanKham.setBacSy(bacSy);
        lanKham.setYTa(yTa);
        lanKham.setBenh(benh);
        lanKham.setBenhNhan(benhNhan);
        LanKham lK = rest.postForObject("https://phongkhamserver.herokuapp.com/lankham",lanKham,LanKham.class);
        model.addAttribute("lankham",lK);
        return "lankham/thanhcong";
    }
    @GetMapping("/timkiem")
    public String timKiem(Model model){
        model.addAttribute("lankham",null);
        return "lankham/timkiemlankham";
    }
    @PostMapping("/timkiem")
    public String ketquaTimKiem(@RequestParam(name="maLK") String maLK, Model model){
        LanKham lanKham = rest.getForObject("https://phongkhamserver.herokuapp.com/lankham?maLK="+maLK,LanKham.class);
        model.addAttribute("lankham",lanKham);
        return "lankham/timkiemlankham";
    }
    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id){
        rest.delete("https://phongkhamserver.herokuapp.com/lankham/{id}",id);
        return "index";
    }
    @GetMapping("/sua/{id}")
    public String getViewLanKhamById( @PathVariable Long id,Model model){
        LanKham lanKham = rest.getForObject("https://phongkhamserver.herokuapp.com/lankham/{id}",LanKham.class,id);
        List<BacSy> bacSys = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy/all",BacSy[].class));
        List<YTa> yTas = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/yta/all",YTa[].class));
        List<BenhNhan> benhNhans = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/benhnhan/all",BenhNhan[].class));
        List<Thuoc> thuocs = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/thuoc/all",Thuoc[].class));
        List<Benh> benhs = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/benh/all",Benh[].class));
        model.addAttribute("bacsys",bacSys);
        model.addAttribute("ytas",yTas);
        model.addAttribute("benhnhans",benhNhans);
        model.addAttribute("thuocs",thuocs);
        model.addAttribute("benhs",benhs);
        model.addAttribute("lankham",lanKham);
        return "lankham/sualankham";
    }
    @PostMapping("/sua/{id}")
    public String update(@RequestParam(name="bacsy") Long idBS, @RequestParam(name="yta") Long idYTa,Model model,@PathVariable Long id,
                         @RequestParam(name="benhnhan") Long idBN,@RequestParam(name="benh") Long idBenh,@RequestParam(name="iddieutri",required = false) Long idDieuTri,
                         @RequestParam(name="idthuocs",required = false) Long[] idThuocs, @RequestParam(name="soluongs",required = false) Integer[] soLuongs,
                         @RequestParam(name = "cachdung") String cachDung,@RequestParam(name="malankham") String maLK,
                         @RequestParam(name="ngayvaovien") String ngayVaoVien,@RequestParam(name="ngayravien") String ngayRaVien){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LanKham lanKham = new LanKham();
        lanKham.setMaLanKham(maLK);
        try {
            lanKham.setNgayRaVien(sdf.parse(ngayRaVien));
            lanKham.setNgayVaoVien(sdf.parse(ngayVaoVien));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BacSy bacSy = rest.getForObject("https://phongkhamserver.herokuapp.com/bacsy/{id}",BacSy.class,idBS);
        YTa yTa = rest.getForObject("https://phongkhamserver.herokuapp.com/yta/{id}",YTa.class,idYTa);
        BenhNhan benhNhan = rest.getForObject("https://phongkhamserver.herokuapp.com/benhnhan/{id}",BenhNhan.class,idBN);
        Benh benh = rest.getForObject("https://phongkhamserver.herokuapp.com/benh/{id}",Benh.class,idBenh);
        if (idThuocs!=null) {
            DieuTri dieuTri = new DieuTri();
            dieuTri.setId(idDieuTri);
            Set<DieuTri_Thuoc> dts = new HashSet<>();
            dieuTri.setDieuTri_Thuocs(dts);
            for (int i = 0; i < idThuocs.length; i++) {
                DieuTri_Thuoc dieuTri_thuoc = new DieuTri_Thuoc();
                Thuoc thuoc = rest.getForObject("https://phongkhamserver.herokuapp.com/thuoc/{id}",Thuoc.class,idThuocs[i]);
                dieuTri_thuoc.setThuoc(thuoc);
                dieuTri_thuoc.setSoLuong(soLuongs[i]);
                dts.add(dieuTri_thuoc);
            }
            dieuTri.setCachDung(cachDung);
            lanKham.setDieuTri(dieuTri);
        }

        lanKham.setBacSy(bacSy);
        lanKham.setYTa(yTa);
        lanKham.setBenh(benh);
        lanKham.setBenhNhan(benhNhan);
        lanKham.setId(id);
        rest.put("https://phongkhamserver.herokuapp.com/lankham/{id}",lanKham,id);
        model.addAttribute("lankham",lanKham);
        return "lankham/thanhcong";
    }
    @GetMapping("/all")
    public String getViewDanhSach(Model model){
        model.addAttribute("lankhams",null);
        return "lankham/danhsach";
    }
    @PostMapping("/all")
    public String getDanhSach(Model model,@RequestParam(name="ngaybatdau") String ngaybatdau, @RequestParam(name="ngayketthuc") String ngayketthuc){
        List<LanKham> lanKhams = Arrays.asList(rest.getForObject("https://phongkhamserver.herokuapp.com/lankham/all?ngaybatdau="+ngaybatdau+"&ngayketthuc="+ngayketthuc,LanKham[].class));
        model.addAttribute("lankhams",lanKhams);
        model.addAttribute("ngaybatdau",ngaybatdau);
        model.addAttribute("ngayketthuc",ngayketthuc);
        return "lankham/danhsach";
    }
    @GetMapping("/chitiet/{id}")
    public String getLanKham(@PathVariable Long id,Model model){
        LanKham lanKham = rest.getForObject("https://phongkhamserver.herokuapp.com/lankham/{id}",LanKham.class,id);
        model.addAttribute("lankham",lanKham);
        return "lankham/chitiet";
    }
}
