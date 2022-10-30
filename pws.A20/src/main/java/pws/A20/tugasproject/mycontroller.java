/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.A20.tugasproject;


import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Controller
public class mycontroller {
    @RequestMapping("/hello")
//     multipart digunakan mengambil data
    public String tampilan(
            @RequestParam(value = "nama") String isipertama,
            @RequestParam(value = "alamat") String isikedua,
            Model next,
            @RequestParam(value = "gambar") MultipartFile isiketiga
    ) throws IOException {
        //getByte digunakan untuk mengubah gambar ke tipe byte
        byte[] img = isiketiga.getBytes();
//        String tipefile = isiketiga.getContentType();
        //Base64 digunakan untuk mengubah tipe byte ke base64
        String base64Image = Base64.encodeBase64String(img);
        //concat digunakan untuk menggabungkan 2 string menjadi1, data:image adalah path local
        String imglink = "data:image/png;base64,".concat(base64Image);
        next.addAttribute("paket1", isipertama);
        next.addAttribute("paket2", isikedua);
        next.addAttribute("paket3", imglink);
        return "viewpage";
    }

}
