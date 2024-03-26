package shoesweb.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoesweb.backend.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);

}
