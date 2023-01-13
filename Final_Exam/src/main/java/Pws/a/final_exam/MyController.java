/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pws.a.final_exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController
@CrossOrigin
@ResponseBody
public class MyController {
    
    //disini ada anama databasenya yaitu surat, serta terdapat juga perintah atau tampilan jika berhasil dengan tidaknya ketika get
    Surat data = new Surat();
    SuratJpaController control = new SuratJpaController();
    
    //perintah untuk get dan terdapat database yang akan di panggil juga
    @GetMapping(value = "/GET", produces = APPLICATION_JSON_VALUE)
    public List<Surat> getData(){
        List<Surat> buffer = new ArrayList<>();
        try
        {
            buffer = control.findSuratEntities();
        }
        catch (Exception error)
        {
            
        }
        return buffer;
    }
    
    //perintah untuk post dan terdapat database yang akan di panggil juga, serta terdapat juga perintah atau tampilan jika berhasil dengan tidaknya ketika kita post
    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData (HttpEntity<String> datasend) throws JsonProcessingException, Object
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    control.create(data);
                    feedback = data.getNosurat()+ "Saved";
                }
                catch (Exception error)
                        {
                            feedback = error.getMessage();
                        }
                return feedback;
    }
    
    //perintah untuk put dan terdapat database yang akan di panggil juga, serta terdapat juga perintah atau tampilan jika berhasil dengan tidaknya ketika kita mengupdate
    @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String updateData (HttpEntity<String> datasend) throws JsonProcessingException, Object
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    control.edit(data);
                    feedback = data.getNosurat()+ "Saved";
                }
                catch (Exception error)
                        {
                            feedback = error.getMessage();
                        }
                return feedback;
    }
    
    //perintah untuk delete dan terdapat database yang akan di panggil juga, serta terdapat juga perintah atau tampilan jika berhasil dengan tidaknya ketika mendelet
    @DeleteMapping(value = "/DELETE", consumes = APPLICATION_JSON_VALUE)
    public String deleteData (HttpEntity<String> datasend) throws JsonProcessingException, Object
    {
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), Surat.class);
                try
                {
                    control.destroy(data.getNosurat());
                    feedback = data.getNosurat()+ "Deleted";
                }
                catch (Exception error)
                        {
                            feedback = error.getMessage();
                        }
                return feedback;
    }
}
