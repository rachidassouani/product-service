package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.exception.RequestValidationException;
import io.rachidassouani.productservice.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Product with id [%s] not found".formatted(id)));
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product productToSave = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(productToSave);
        return productMapper.toProductResponse(savedProduct);
    }

    public boolean deleteProductById(Long id) {
        return productRepository
                .findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                }).orElse(false);
    }

    public ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(id)));

        /*
         update the product only if the request body that accompanies the request contains fields
         that are neither null nor empty and that are not equal to the existing field.
         */

        boolean changes = false;
        if (productUpdateRequest.title() != null && !productUpdateRequest.title().isEmpty()
                && !productUpdateRequest.title().equals(product.getTitle())) {
            product.setTitle(productUpdateRequest.title());
            changes = true;
        }

        if (productUpdateRequest.subtitle() != null && !productUpdateRequest.subtitle().isEmpty()
                && !productUpdateRequest.subtitle().equals(product.getSubtitle())) {
            product.setSubtitle(productUpdateRequest.subtitle());
            changes = true;
        }

        if (productUpdateRequest.description() != null && !productUpdateRequest.description().isEmpty()
                && !productUpdateRequest.description().equals(product.getDescription())) {
            product.setDescription(productUpdateRequest.description());
            changes = true;
        }

        if (productUpdateRequest.price() != (product.getPrice())) {
            product.setPrice(productUpdateRequest.price());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No data changes found");
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.toProductResponse(savedProduct);
    }

    public List<ProductResponse> findAllProductsByPageAndSize(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size);

        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.getContent()
                .stream()
                .map(productMapper::toProductResponse).toList();
    }
}

