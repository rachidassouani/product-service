package io.rachidassouani.productservice.product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {
        ProductResponse productById = productService.findProductById(id);
        return ResponseEntity.ok(productById);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        ProductResponse productResponse = productService.saveProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        if (productService.deleteProductById(id)) {
            return ResponseEntity.ok("Product with id [%s] deleted successfully".formatted(id));
        }
        return new ResponseEntity<>("Product with id [%s] not found".formatted(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productUpdateRequest);
        return ResponseEntity.ok("Product with id [%s] updated successfully".formatted(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProductsByPageAndSize(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        List<ProductResponse> allProductsByPageAndSize = productService
                .findAllProductsByPageAndSize(page, size);
        return ResponseEntity.ok(allProductsByPageAndSize);
    }

}
