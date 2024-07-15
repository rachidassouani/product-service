package io.rachidassouani.productservice.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductUpdateRequest(

        @NotNull(message = "Title is required")
        @NotEmpty(message = "Title is required")
        @Size(max = 100, message = "Title must be less than 100 characters")
        String title,

        @NotNull(message = "Subtitle is required")
        @NotEmpty(message = "Subtitle is required")
        @Size(max = 200, message = "Subtitle must be less than 200 characters")
        String subtitle,

        @NotNull(message = "Price is required")
        @Min(value = 0, message = "Price must be greater than or equal to 0")
        double price,

        @NotNull(message = "Description is required")
        @NotEmpty(message = "Description is required")
        @Size(max = 1000, message = "Description must be less than 1000 characters")
        String description) {}
