package shoesweb.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoesweb.backend.Entity.Product;
import shoesweb.backend.Entity.ProductDetail;

import java.util.Set;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    Set<ProductDetail> findProductDetailByProduct(Product product);
}
