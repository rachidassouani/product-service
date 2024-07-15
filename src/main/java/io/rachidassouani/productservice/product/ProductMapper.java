package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.rate.Rate;
import io.rachidassouani.productservice.rate.RateMapper;
import io.rachidassouani.productservice.rate.RateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {

    private final RateMapper rateMapper;

    public ProductMapper(RateMapper rateMapper) {
        this.rateMapper = rateMapper;
    }

    public ProductResponse toProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        List<Rate> rates = product.getRates();

        productResponse.setId(product.getId());
        productResponse.setTitle(product.getTitle());
        productResponse.setSubtitle(product.getSubtitle());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());

        if (rates != null) {
            List<RateResponse> rateResponses = rates
                    .stream()
                    .map(rateMapper::toRateResponse)
                    .toList();
            productResponse.setRateResponses(rateResponses);
        }
        return productResponse;
    }
}
