package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.rate.RateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void testSaveProduct() {
        // product to save
        ProductRequest productToSave = new ProductRequest();
        productToSave.setId(1L);
        productToSave.setTitle("Test Product");
        productToSave.setDescription("Test Description");
        productToSave.setPrice(50.99);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setTitle("Test Product");
        savedProduct.setDescription("Test Description");
        savedProduct.setPrice(50.99);

        // saved product
        ProductResponse expectedProductResponse = new ProductResponse();
        expectedProductResponse.setId(1L);
        expectedProductResponse.setTitle("Test Product");
        expectedProductResponse.setDescription("Test Description");
        expectedProductResponse.setPrice(50.99);

        when(productRepository.save(any())).thenReturn(savedProduct);
        ProductResponse savedProductResponse = productService.saveProduct(productToSave);

        // Assert
        assertNotNull(savedProductResponse);
        assertEquals(expectedProductResponse.getId(), savedProductResponse.getId());
        assertEquals(expectedProductResponse.getTitle(), savedProductResponse.getTitle());
        assertEquals(expectedProductResponse.getDescription(), savedProductResponse.getDescription());
        assertEquals(expectedProductResponse.getPrice(), savedProductResponse.getPrice());

        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void testDeleteProductWhenProductExists() {
        Long id = 1L;
        Product foundedProduct = new Product();
        foundedProduct.setId(1L);
        foundedProduct.setTitle("Test Product");
        foundedProduct.setDescription("Test Description");
        foundedProduct.setPrice(50.99);

        when(productRepository.findById(id)).thenReturn(Optional.of(foundedProduct));
        boolean deleted = productService.deleteProductById(id);

        // assert
        assertTrue(deleted);
    }

    @Test
    public void testDeleteProductWhenProductNotExists() {
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        boolean deleted = productService.deleteProductById(id);

        // assert
        assertFalse(deleted);
    }
}
