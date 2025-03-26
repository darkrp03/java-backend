package com.backend.models;

public class OlxItem {
    private String name;
    private double price;
    private String url;

    public OlxItem(String name, double price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
