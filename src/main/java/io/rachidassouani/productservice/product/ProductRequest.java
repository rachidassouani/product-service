package io.rachidassouani.productservice.product;

import io.rachidassouani.productservice.rate.RateRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ProductRequest {
    private Long id;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotNull(message = "Subtitle is required")
    @NotEmpty(message = "Subtitle is required")
    @Size(max = 200, message = "Subtitle must be less than 200 characters")
    private String subtitle;


    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;


    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    private List<RateRequest> rateRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Title is required") @NotEmpty(message = "Title is required") @Size(max = 100, message = "Title must be less than 100 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Title is required") @NotEmpty(message = "Title is required") @Size(max = 100, message = "Title must be less than 100 characters") String title) {
        this.title = title;
    }

    public @NotNull(message = "Subtitle is required") @NotEmpty(message = "Subtitle is required") @Size(max = 200, message = "Subtitle must be less than 200 characters") String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(@NotNull(message = "Subtitle is required") @NotEmpty(message = "Subtitle is required") @Size(max = 200, message = "Subtitle must be less than 200 characters") String subtitle) {
        this.subtitle = subtitle;
    }

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price is required") @Min(value = 0, message = "Price must be greater than or equal to 0") double price) {
        this.price = price;
    }

    public @NotNull(message = "Description is required") @NotEmpty(message = "Description is required") @Size(max = 1000, message = "Description must be less than 1000 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "Description is required") @NotEmpty(message = "Description is required") @Size(max = 1000, message = "Description must be less than 1000 characters") String description) {
        this.description = description;
    }

    public List<RateRequest> getRateRequests() {
        return rateRequests;
    }

    public void setRateRequests(List<RateRequest> rateRequests) {
        this.rateRequests = rateRequests;
    }
}
