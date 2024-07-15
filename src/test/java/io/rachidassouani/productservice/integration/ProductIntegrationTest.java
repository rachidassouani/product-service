package io.rachidassouani.productservice.integration;

import io.rachidassouani.productservice.product.ProductRequest;
import io.rachidassouani.productservice.product.ProductResponse;
import io.rachidassouani.productservice.product.ProductUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    private static final String PRODUCT_PATH = "api/v1/products";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testFindProductById() {
        // creating product request object
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("title integration test FindProductById");
        productRequest.setSubtitle("subtitle integration test FindProductById");
        productRequest.setDescription("description integration test FindProductById");
        productRequest.setPrice(99);

        // saving the product
        ProductResponse savedProduct = webTestClient.post()
                .uri(PRODUCT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(productRequest), ProductRequest.class)
                .exchange().expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {
                })
                .returnResult()
                .getResponseBody();

        // Calling findProductById endpoint
        ProductResponse productResponse = webTestClient.get()
                .uri(PRODUCT_PATH + "/{id}",  savedProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {})
                .returnResult()
                .getResponseBody();


        //Assert
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getTitle()).isEqualTo(productRequest.getTitle());
        assertThat(productResponse.getSubtitle()).isEqualTo(productRequest.getSubtitle());
        assertThat(productResponse.getDescription()).isEqualTo(productRequest.getDescription());
        assertThat(productResponse.getPrice()).isEqualTo(productRequest.getPrice());
    }

    @Test
    public void testSaveProduct() {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("title");
        productRequest.setSubtitle("subtitle");
        productRequest.setDescription("description");
        productRequest.setPrice(99);

        // saving product
        ProductResponse savedProduct = webTestClient.post()
                .uri(PRODUCT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(productRequest), ProductRequest.class)
                .exchange().expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {
                })
                .returnResult()
                .getResponseBody();

        // getting saved product
        ProductResponse productResponse = webTestClient.get()
                .uri(PRODUCT_PATH + "/{id}",  savedProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {})
                .returnResult()
                .getResponseBody();

        //Assert
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getId()).isEqualTo(savedProduct.getId());
        assertThat(productResponse.getTitle()).isEqualTo(savedProduct.getTitle());
        assertThat(productResponse.getSubtitle()).isEqualTo(savedProduct.getSubtitle());
        assertThat(productResponse.getDescription()).isEqualTo(savedProduct.getDescription());
        assertThat(productResponse.getPrice()).isEqualTo(savedProduct.getPrice());
    }


    @Test
    public void testDeleteProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("title");
        productRequest.setSubtitle("subtitle");
        productRequest.setDescription("description");
        productRequest.setPrice(99);

        // saving new product in order to delete it later
        ProductResponse savedProduct = webTestClient.post()
                .uri(PRODUCT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(productRequest), ProductRequest.class)
                .exchange().expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {
                })
                .returnResult()
                .getResponseBody();

        // delete product by id
        String responseBody = webTestClient.delete()
                .uri(PRODUCT_PATH + "/{id}", savedProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        // checking that the product is deleted: HttpStatus equals NotFound
        webTestClient.get()
                .uri(PRODUCT_PATH + "/{id}", savedProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testUpdateProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle("title");
        productRequest.setSubtitle("subtitle");
        productRequest.setDescription("description");
        productRequest.setPrice(99);

        ProductResponse savedProduct = webTestClient.post()
                .uri(PRODUCT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(productRequest), ProductRequest.class)
                .exchange().expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {
                })
                .returnResult()
                .getResponseBody();

        ProductUpdateRequest updateRequest = new ProductUpdateRequest(
                "new title",
                "new subtitle",
                30,
                "new description");

        String responseBody = webTestClient.put()
                .uri(PRODUCT_PATH + "/{id}", savedProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateRequest), ProductUpdateRequest.class)
                .exchange().expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();


        ProductResponse findUpdatedproductResponse = webTestClient.get()
                .uri(PRODUCT_PATH + "/{id}",  savedProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ProductResponse>() {})
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isEqualTo("Product with id [%s] updated successfully".formatted(savedProduct.getId()));
        assertThat(savedProduct.getId()).isEqualTo(findUpdatedproductResponse.getId());
        assertThat(updateRequest.title()).isEqualTo(findUpdatedproductResponse.getTitle());
        assertThat(updateRequest.subtitle()).isEqualTo(findUpdatedproductResponse.getSubtitle());
        assertThat(updateRequest.description()).isEqualTo(findUpdatedproductResponse.getDescription());
        assertThat(updateRequest.price()).isEqualTo(findUpdatedproductResponse.getPrice());
    }
}
