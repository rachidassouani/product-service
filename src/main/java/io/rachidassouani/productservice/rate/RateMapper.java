package io.rachidassouani.productservice.rate;

import org.springframework.stereotype.Service;

@Service
public class RateMapper {

    public RateResponse toRateResponse(Rate rate) {
        RateResponse rateResponse = new RateResponse();
        rateResponse.setId(rate.getId());
        rateResponse.setTitle(rate.getTitle());
        rateResponse.setComment(rate.getComment());
        rateResponse.setNumberOfStarts(rate.getNumberOfStarts());
        return rateResponse;
    }

    public Rate toRate(RateRequest rateRequest) {
        Rate rate = new Rate();
        rate.setTitle(rateRequest.title());
        rate.setComment(rateRequest.comment());
        rate.setNumberOfStarts(rateRequest.numberOfStarts());
        return rate;
    }
}
