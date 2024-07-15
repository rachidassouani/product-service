package io.rachidassouani.productservice.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Find product by ID", description = "Returns a product based on ID")
    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {
        logger.info("Received request to find product with ID: {}", id);
        ProductResponse productById = productService.findProductById(id);
        return ResponseEntity.ok(productById);
    }

    @PostMapping
    @Operation(summary = "Save product", description = "save product with its rates, Returns product response DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        logger.info("Received request to save product");
        ProductResponse productResponse = productService.saveProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete product by ID", description = "Delete a product based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product to delete not found")
    })
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        logger.info("Received request to delete product with ID: {}", id);
        if (productService.deleteProductById(id)) {
            return ResponseEntity.ok("Product with id [%s] deleted successfully".formatted(id));
        }
        logger.warn("Cannot delete product with ID: {}, because its not founded", id);
        return new ResponseEntity<>("Product with id [%s] not found".formatted(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update product by ID", description = "Update product based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {
        logger.info("Received request to update product with ID: {}", id);
        ProductResponse productResponse = productService.updateProduct(id, productUpdateRequest);
        return ResponseEntity.ok("Product with id [%s] updated successfully".formatted(id));
    }

    @GetMapping
    @Operation(summary = "Find all products by page and size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return list of products by page and size"),
    })
    public ResponseEntity<List<ProductResponse>> findAllProductsByPageAndSize(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        logger.info("Received request to find all  products by page and size");
        List<ProductResponse> allProductsByPageAndSize = productService
                .findAllProductsByPageAndSize(page, size);
        return ResponseEntity.ok(allProductsByPageAndSize);
    }
}
