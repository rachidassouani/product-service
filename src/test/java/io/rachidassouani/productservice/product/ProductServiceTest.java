package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.rate.RateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private final RateMapper rateMapper = new RateMapper();
    private final ProductMapper productMapper = new ProductMapper(rateMapper);

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, productMapper);
    }

    @Test
    public void testFindProductById() {
        // preparing product object
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setTitle("title");
        product.setSubtitle("subtitle");
        product.setDescription("description");
        product.setPrice(300);

        // calling mapper to convert product to product response
        ProductResponse expectedProductResponse = productMapper.toProductResponse(product);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // testing
        ProductResponse returnedProductResponse = productService.findProductById(productId);

        // asserts
        assertThat(returnedProductResponse.getId()).isEqualTo(expectedProductResponse.getId());
        assertThat(returnedProductResponse.getTitle()).isEqualTo(expectedProductResponse.getTitle());
        assertThat(returnedProductResponse.getSubtitle()).isEqualTo(expectedProductResponse.getSubtitle());
        assertThat(returnedProductResponse.getDescription()).isEqualTo(expectedProductResponse.getDescription());
        assertThat(returnedProductResponse.getPrice()).isEqualTo(expectedProductResponse.getPrice());
        verify(productRepository).findById(productId);
    }
}
