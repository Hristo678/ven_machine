package com.example.ven_machine.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private Map<String, Product> products;

    private Integer coinsSum;

    private List<Integer> coins;

    public VendingMachine() {
        products = new HashMap<>();
        coinsSum = 0;
        coins = new ArrayList<>();
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Integer getCoinsSum() {
        return coinsSum;
    }

    public void setCoinsSum(Integer coinsSum) {
        this.coinsSum = coinsSum;
    }

    public boolean checkIfProductExists(String productName){
        return this.products.containsKey(productName);
    }

    public List<Integer> getCoins() {
        return coins;
    }

    public void setCoins(List<Integer> coins) {
        this.coins = coins;
    }
}
