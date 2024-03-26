package shoesweb.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoesweb.backend.Entity.Size;
import shoesweb.backend.Repository.SizeReponsitory;

import java.util.List;

@RestController
@RequestMapping("api/admin/size")
public class SizeController {
    @Autowired
    private SizeReponsitory sizeReponsitory;
    @GetMapping("/all")
    public ResponseEntity<List<Size>> getAll(){
        return ResponseEntity.ok(sizeReponsitory.findAll());
    }
    @PostMapping("/addSize")
    ResponseEntity<?> addSize(@RequestBody Size size){
        return ResponseEntity.ok(sizeReponsitory.save(size));
    }
    @PutMapping("/editSize/{id}")
    ResponseEntity<String> editSize(@PathVariable int id,@RequestBody Size newSize){
        Size size = sizeReponsitory.findById(id);
        if(size == null){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        size.setName(newSize.getName());
        System.out.println(size.getName());
        System.out.println(size.getId()+" "+size.getName());
        sizeReponsitory.save(size);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @DeleteMapping("/deleteSize/{id}")
    ResponseEntity<String> deleteSize(@PathVariable int id){
        Size size = sizeReponsitory.findById(id);

        if(size == null){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        sizeReponsitory.deleteById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
