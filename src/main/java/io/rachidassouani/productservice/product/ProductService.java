package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
