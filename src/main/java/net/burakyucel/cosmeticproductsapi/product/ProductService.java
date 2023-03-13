package net.burakyucel.cosmeticproductsapi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(@DefaultValue("0") int pageNumber, @DefaultValue("10") int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");

        return productRepository.findAll(pages).getContent();
    }

    public Product addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());

        if (productOptional.isPresent()) {
            throw new IllegalStateException("The name is taken");
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);

        if (!exists) {
            throw new IllegalStateException(
                    "Student with id " + productId + " does not exist");
        }

        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + productId + " does not exist"));

        return product;
    }
}
