package shoesweb.backend.Controller;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoesweb.backend.Entity.Color;
import shoesweb.backend.Exception.ResourceNotFoundException;
import shoesweb.backend.Repository.ColorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/admin/color")
public class ColorController {
    @Autowired
    private ColorRepository colorRepository;
    @GetMapping("/getAll")
    ResponseEntity<List<Color>> getAll(){
        return ResponseEntity.ok(colorRepository.findAll());
    }
    @PostMapping("/addColor")
    ResponseEntity<?> addColor(@RequestBody Color color){
        return ResponseEntity.ok(colorRepository.save(color));
    }
    @PutMapping("editColor/{id}")
    ResponseEntity<String> editColor(@PathVariable int id,@RequestBody Color newColor){
        Color color = colorRepository.findById(id);
        if(color == null){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        color.setName(newColor.getName());
        color.setImageName(newColor.getImageName());
        colorRepository.save(color);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteColor/{id}")
    ResponseEntity<String> deleteColor(@PathVariable("id") int id){
        Color color = colorRepository.findById(id);
        if(color == null){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        System.out.println(color.getId()+" "+color.getName());
        colorRepository.deleteById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
