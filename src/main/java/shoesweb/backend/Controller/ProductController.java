package shoesweb.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoesweb.backend.Entity.Product;
import shoesweb.backend.Repository.ProductDetailRepository;
import shoesweb.backend.Repository.ProductRepository;
import shoesweb.backend.Service.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productRepository.findAll());
    }
    @GetMapping("/{id}")
    ResponseEntity<?> productDetail(@PathVariable int id){
        Product product = productRepository.findById(id);
        if(product == null){
            return new ResponseEntity<String>("not found id = " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }
    @PostMapping("/add")
    ResponseEntity<?> addProduct(@RequestBody Product product){
         if(!productService.addProduct(product)){
             return ResponseEntity.badRequest().body("hay nhap day du thong tin ve size va color");
         }
         return ResponseEntity.ok("Them product thanh cong");
    }
    @PutMapping(value = "/edit/{id}")
    ResponseEntity<?> editProduct(@PathVariable int id,@RequestBody Product newProduct){
        Product product = productRepository.findById(id);
        if(product == null) return new ResponseEntity<String>("not found id = " + id,HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(productService.editProduct(id,newProduct));
    }
    @DeleteMapping(value = "delete/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable int id){
        if(!productService.deleteProduct(id)) return new ResponseEntity<String>("not found id = " + id,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("delete thanh cong id = " + id,HttpStatus.OK);
    }
}
