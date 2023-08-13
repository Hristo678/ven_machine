package com.example.ven_machine.services;

import com.example.ven_machine.constants.ResponseStatus;
import com.example.ven_machine.models.Product;
import com.example.ven_machine.models.VendingMachine;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.ven_machine.constants.CoinsConstants.COINS;

@Service
public class VendingMachineService {

    private VendingMachine vendingMachine;

    public VendingMachineService() {
        this.vendingMachine = new VendingMachine();
    }


    public Map<String, Product> getProducts() {
        return this.vendingMachine.getProducts();
    }

    public void insertCoin(Integer value) {
        this.vendingMachine.setCoinsSum(this.vendingMachine.getCoinsSum() + value);
        this.vendingMachine.getCoins().add(value);
    }

    public List<Integer> buyProduct(String productName) {

        if (this.vendingMachine.checkIfProductExists(productName)) {
            Product product = this.vendingMachine.getProducts().get(productName);
            if (this.vendingMachine.getCoinsSum() >= product.getPrice() && product.getQuantity() > 0) {
                this.vendingMachine.setCoinsSum(this.vendingMachine.getCoinsSum() - product.getPrice());
                List<Integer> change = getChange(COINS, this.vendingMachine.getCoinsSum());
                this.vendingMachine.setCoins(new ArrayList<>());
                this.vendingMachine.setCoinsSum(0);
                product.setQuantity(product.getQuantity() - 1);
                return change;
            }
        }
        return null;
    }

    public String addNewProduct(Product product) {
        if (!this.vendingMachine.checkIfProductExists(product.getName())) {
            this.vendingMachine.getProducts().put(product.getName(), product);
            return ResponseStatus.OK.toString();
        } else {
            return "This product exists";
        }
    }

    public String insertProduct(String productName, int quantity) {
        if (this.vendingMachine.checkIfProductExists(productName)) {
            Product product = this.vendingMachine.getProducts().get(productName);
            for (int i = 0; i < quantity; i++) {
                if (product.getQuantity() < 10) {
                    product.setQuantity(product.getQuantity() + 1);
                } else {
                    return "Max capacity";
                }
            }
            return ResponseStatus.OK.toString();
        }
        return "No such product!";
    }

    public String removeProduct(String productName) {
        if (this.vendingMachine.checkIfProductExists(productName)) {
            this.vendingMachine.getProducts().remove(productName);
            return ResponseStatus.OK.toString();
        } else {
            return "No such product!";
        }
    }

    public String updateProduct(Product product) {
        if (this.vendingMachine.checkIfProductExists(product.getName())) {
            this.vendingMachine.getProducts().replace(product.getName(), product);
            return ResponseStatus.OK.toString();
        } else {
            return "No such product!";
        }
    }

    public Integer returnCoin() {
        if (!this.vendingMachine.getCoins().isEmpty()) {

            int coin = this.vendingMachine.getCoins().remove(this.vendingMachine.getCoins().size() - 1);
            this.vendingMachine.setCoinsSum(this.vendingMachine.getCoinsSum() - coin);
            return coin;

        }
        return null;
    }

    public Product getProduct(String productName) {
        return this.vendingMachine.checkIfProductExists(productName) ? this.vendingMachine.getProducts().get(productName) : null;
    }

    private List<Integer> getChange(List<Integer> coins, Integer amount) {
        List<Integer> selectedCoins = new ArrayList<>();
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return null; // It's not possible to make up the target amount using the given coins.
        } else {
            int remaining = amount;
            while (remaining > 0) {
                for (int coin : coins) {
                    if (remaining >= coin && dp[remaining] == dp[remaining - coin] + 1) {
                        selectedCoins.add(coin);
                        remaining -= coin;
                        break;
                    }
                }
            }
            return selectedCoins;
        }

    }

    public Integer getInsertedCoins() {
        return this.vendingMachine.getCoinsSum();
    }

}
