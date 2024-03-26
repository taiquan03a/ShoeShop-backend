package shoesweb.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shoesweb.backend.Entity.Color;
import shoesweb.backend.Entity.Product;
import shoesweb.backend.Entity.ProductDetail;
import shoesweb.backend.Entity.Size;
import shoesweb.backend.Repository.ColorRepository;
import shoesweb.backend.Repository.ProductDetailRepository;
import shoesweb.backend.Repository.ProductRepository;
import shoesweb.backend.Repository.SizeReponsitory;

import java.util.HashSet;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private SizeReponsitory sizeReponsitory;
    public Boolean addProduct(Product productRequest){
        Product  product = Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .detail(productRequest.getDetail())
                .describe(productRequest.getDescribe())
                .price(productRequest.getPrice())
                .starNumber(productRequest.getStarNumber())
                .build();
        Set<ProductDetail> listProductDetail = new HashSet<>();
        productRepository.save(product);
        int productId = product.getId();
        if(product != null){
            for(ProductDetail productDetailRequest :productRequest.getProductDetails()){
                Color color = colorRepository.findById(productDetailRequest.getColorId());
                Size size = sizeReponsitory.findById(productDetailRequest.getSizeId());
                Product product1 = productRepository.findById(productId);
                ProductDetail productDetail = ProductDetail.builder()
                        .color(color)
                        .size(size)
                        .product(product1)
                        .colorId(color.getId())
                        .sizeId(size.getId())
                        .quantity(productDetailRequest.getQuantity())
                        .build();
                productDetailRepository.save(productDetail);
                listProductDetail.add(productDetail);
            }
        }
        if(listProductDetail.isEmpty()){
            productRepository.delete(product);
            return false;
        }
        return true;
    }
    public Product editProduct(int id,Product newProduct){
        Product product = productRepository.findById(id);
        product.setName(newProduct.getName());
        product.setDescribe(newProduct.getDescribe());
        product.setDetail(newProduct.getDetail());
        product.setStarNumber(newProduct.getStarNumber());
        product.setPrice(newProduct.getPrice());
        productRepository.save(product);
        Set<ProductDetail> listProductDetail = new HashSet<>();
        Set<ProductDetail> productDetails = productDetailRepository.findProductDetailByProduct(product);
        productDetailRepository.deleteAll(productDetails);
        for(ProductDetail newProductDetail :newProduct.getProductDetails()){
            Color color = colorRepository.findById(newProductDetail.getColorId());
            Size size = sizeReponsitory.findById(newProductDetail.getSizeId());
            Product product1 = productRepository.findById(id);
            ProductDetail productDetail = ProductDetail.builder()
                    .color(color)
                    .size(size)
                    .product(product1)
                    .colorId(color.getId())
                    .sizeId(size.getId())
                    .quantity(newProductDetail.getQuantity())
                    .build();
            productDetailRepository.save(productDetail);
            listProductDetail.add(productDetail);
        }
        return product;
    }
    public Boolean deleteProduct(int id){
        Product product = productRepository.findById(id);
        if(product == null) return false;
        Set<ProductDetail> productDetails = productDetailRepository.findProductDetailByProduct(product);
        productDetailRepository.deleteAll(productDetails);
        productRepository.delete(product);
        return true;
    }
}
