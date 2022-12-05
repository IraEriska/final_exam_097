/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.coba;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController //memanggil restful controller
public class productController { //kelas public bernama productController
    private static final Map<String, Product> productRepo = new HashMap<>(); //menggunakan hashmap untuk menyimpan data dalam bentuk string kedalam Product.java
    static {
        Product honey = new Product(); //mendeklarasikan variabel baru dengan nama honey
        honey.setId("1"); //menentukan id ke-1
        honey.setName("Honey"); //menuliskan nama dari id ke-1 yaitu honey
        productRepo.put(honey.getId(), honey); //
        
        Product almond = new Product(); //mendeklarasikan variabel baru dengan nama almond
        almond.setId("2"); //menentukan id ke-2
        almond.setName("Almond"); //menuliskan nama dari id ke-2 yaitu almond
        productRepo.put(almond.getId(), almond);  //      
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE) //path untuk melakukan delete data ketika id pada kode => "/products/{id}"
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
    //jika tidak ada id untuk di hapus makan akan muncul pesan erorr "product could not be found" / "id product tidak dan tidak bisa di delete"  
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("id product tidak dan tidak bisa di delete", HttpStatus.NOT_FOUND);
        }
        //jika ada id untuk dihapus maka muncul pesan "product is delete successfully"
        else{
            productRepo.remove(id);
            return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK); //memunculkan pesan "Product is deleted successsfully"
        }
        

    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT) //path untuk melakukan edit data ketika id pada kode => "/product/{id}"
    public ResponseEntity<Object> updateProduct (@PathVariable("id") String id, @RequestBody Product product) {
    //jika tidak ada id untuk edit maka akan muncul pesan erorr "product could no be found"   
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("id product tidak ada", HttpStatus.NOT_FOUND);
        } // jika ada id yang dapat di edit maka akan muncul pesan "Product id update successsfully"
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product id update successsfully", HttpStatus.OK);
        }
        
        
    }
   
    @RequestMapping(value = "/products", method = RequestMethod.POST) //untuk menambahkan data pada path "/productcs"
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
     //jika id yang dimasukkan sudah ada makan akan muncul pesan erorr "/ the product is alreadly on the list"   
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("id sudah di buat silahkan masukkan id lagi", HttpStatus.CREATED);
        } //jika id yang dimasukkan belum ada dalam list maka, akan muncul pesan "product added successfully"
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }
        
        
    }
       //menampilkan produkpada path "/product"     
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
}

