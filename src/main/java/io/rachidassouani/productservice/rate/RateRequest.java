package io.rachidassouani.productservice.rate;

public record RateRequest(
        Long id,
        String title,
        String comment,
        int numberOfStarts) {
}
