package com.shuai.bean;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private String name;
    private Integer price;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Book() {
        System.out.println("book!!!!!!!!!!!!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
