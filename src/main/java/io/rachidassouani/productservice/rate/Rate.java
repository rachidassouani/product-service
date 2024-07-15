package io.rachidassouani.productservice.rate;

import io.rachidassouani.productservice.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "comment", length = 300)
    private String comment;

    @Column(name = "number_of_starts")
    private int numberOfStarts;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
