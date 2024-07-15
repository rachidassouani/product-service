package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.rate.RateResponse;

import java.util.List;

public class ProductResponse {
    private Long id;
    private String title;
    private String subtitle;
    private double price;
    private String description;
    private List<RateResponse> rateResponses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RateResponse> getRateResponses() {
        return rateResponses;
    }

    public void setRateResponses(List<RateResponse> rateResponses) {
        this.rateResponses = rateResponses;
    }
}
