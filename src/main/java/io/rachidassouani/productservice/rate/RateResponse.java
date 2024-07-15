package io.rachidassouani.productservice.rate;

public class RateResponse {
    private Long id;
    private String title;
    private String comment;
    private int numberOfStarts;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNumberOfStarts() {
        return numberOfStarts;
    }

    public void setNumberOfStarts(int numberOfStarts) {
        this.numberOfStarts = numberOfStarts;
    }
}
