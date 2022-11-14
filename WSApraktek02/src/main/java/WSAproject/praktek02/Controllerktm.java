/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WSAproject.praktek02;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Controller

public class Controllerktm {
    
    @RequestMapping("/Projectidcard")
    
    public String Projectidcard (@RequestParam ("name") String text,
                                 @RequestParam ("nim") String nomer,
                                 @RequestParam ("tanggal")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                                 @RequestParam ("email") String email,
                                 @RequestParam ("jurusan") String jurusan,
                                 @RequestParam ("image") MultipartFile file, Model model)
                        
                                 throws IOException {
        SimpleDateFormat tanggal = new SimpleDateFormat("EEEE, yyyy-MM-dd");
        
        String newTanggal = tanggal.format(date);
        
        String blob = Base64.encodeBase64String(file.getBytes());
        String gambar = "data:iamge/jpeg;base64,".concat(blob);
        
         model.addAttribute("name", text);
         model.addAttribute("nim", nomer);
         model.addAttribute("tanggal", newTanggal);
         model.addAttribute("email",email);
         model.addAttribute("jurusan", jurusan);
         model.addAttribute("image",gambar);
                  
         return "viewpage";                        
    }
    
}
