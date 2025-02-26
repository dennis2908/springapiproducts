package com.example.live.product;

import com.example.live.function.ResponseHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  // public List<Product> getAllProducts() {
  //   return productRepository.findAll();
  // } 
  ResponseEntity<Object> getAllProducts() {
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      productRepository.findAll()
    );
  }

  @GetMapping("/pagination/{limit}/{offset}")
  // public List<Product> getProducts() {
  //   return productRepository.findAll();
  // } 
  ResponseEntity<Object> getProducts(@PathVariable Long limit, Long offset) {
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      productRepository.getProduct(limit,offset)
    );
  }


  @GetMapping("/{id}")
  // public Product getProductById(@PathVariable Long id) {
  //   return productRepository.findById(id).get();
  // }

  ResponseEntity<Object> getProductById(@PathVariable Long id) {
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      productRepository.findById(id).get()
    );
  }

  @PostMapping
  // public Product createProduct(@RequestBody Product product) {
  //   return productRepository.save(product);
  // }
  ResponseEntity<Object> createProduct(@RequestBody Product product) {
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      productRepository.save(product)
    );
  }
  
  @PutMapping("/{id}")
  // public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
  //   Product existingProduct = productRepository.findById(id).get();
  //   existingProduct.setName(product.getName());
  //   existingProduct.setRackNo(product.getRackNo());
  //   existingProduct.setYearPublish(product.getYearPublish());
  //   existingProduct.setPublisher(product.getPublisher());
  //   return productRepository.save(existingProduct);
  // }
  ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    Product existingProduct = productRepository.findById(id).get();
    existingProduct.setName(product.getName());
    existingProduct.setSku(product.getSku());
    existingProduct.setImage(product.getImage());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setDescription(product.getDescription());
    existingProduct.setPrice(product.getPrice());
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      productRepository.save(existingProduct)
    );
  }

  @DeleteMapping("/{id}")
  public String deleteProduct(@PathVariable Long id) {
    try {
      productRepository.findById(id).get();
      productRepository.deleteById(id);
      return "Product deleted successfully";
    } catch (Exception e) {
      return "Product not found";
    }
  }
  
}
