package com.example.happytail.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shop_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String itemImg; // путь к изображению
    private double price;
    private double estimation; // рейтинг от 1.0 до 5.0
    private String specifications;
    @ElementCollection
    private List<Double> weight;

    public List<Double> getWeight() {
        return weight;
    }

    public void setWeight(List<Double> weight) {
        this.weight = weight;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public double getEstimation() {
        return estimation;
    }

    public void setEstimation(double estimation) {
        this.estimation = estimation;
    }
}
