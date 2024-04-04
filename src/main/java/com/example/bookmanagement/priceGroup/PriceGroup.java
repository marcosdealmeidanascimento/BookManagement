package com.example.bookmanagement.priceGroup;

import com.example.bookmanagement.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "PRICE_GROUP")
public class PriceGroup {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "price_group_id")
    private Long priceGroupId;
    @Column(columnDefinition = "Decimal(10,2)")
    private float floorPrice;
    @Column(columnDefinition = "Decimal(10,2)")
    private float ceilingPrice;
    @OneToOne(mappedBy = "priceGroup")
    private Book book;

    public PriceGroup() { }

    public PriceGroup(float floorPrice, float ceilingPrice) {
        this.floorPrice = floorPrice;
        this.ceilingPrice = ceilingPrice;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public float getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(float floorPrice) {
        this.floorPrice = floorPrice;
    }

    public float getCeilingPrice() {
        return ceilingPrice;
    }

    public void setCeilingPrice(float ceilingPrice) {
        this.ceilingPrice = ceilingPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
