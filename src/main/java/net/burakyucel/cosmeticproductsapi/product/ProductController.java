package net.burakyucel.cosmeticproductsapi.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<List<Product>>(productService.getProducts(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping({"/{productId}", "/{productId}/"})
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
        return new ResponseEntity<Product>(productService.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{productId}", "/{productId}/"})
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);

        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"/{productId}", "/{productId}/"})
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
        product.setId(productId);
        return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
    }
}
