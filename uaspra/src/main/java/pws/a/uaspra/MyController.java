/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.a.uaspra;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Asus
 */

@Controller
@ResponseBody
public class MyController {
    
   Produk data = new Produk();
    ProdukJpaController actrl = new ProdukJpaController();
    
    @RequestMapping("getAll")
    public List<Produk> viewAll(){
        return actrl.findProdukEntities();
    }
    
    @RequestMapping("/getBarang/{id}")
    public String getName(@PathVariable("id") int id) {
        try {
            data = actrl.findProduk(id);
            return data.getBarang() + "<br>" + data.getJumlah();
        } catch (Exception error) {
            return "Data tidak ada";
        }

    }

    @RequestMapping("/getDelete/{id}")
    public String deleteData(@PathVariable("id") int id) {

        try {
            actrl.destroy(id);
            return id + " Deleted";
        } catch (Exception error) {
            return id + " tidak ada";
        }
    }
}
