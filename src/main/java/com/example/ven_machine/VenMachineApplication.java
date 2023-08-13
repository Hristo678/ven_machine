package com.example.ven_machine;

import com.example.ven_machine.models.Product;
import com.example.ven_machine.services.VendingMachineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VenMachineApplication implements CommandLineRunner {

    private VendingMachineService vendingMachineService;

    public VenMachineApplication(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    public static void main(String[] args) {
        SpringApplication.run(VenMachineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        vendingMachineService.addNewProduct(new Product("Coca-Cola", 110, 5));
        vendingMachineService.addNewProduct(new Product("Pepsi", 100, 9));
        vendingMachineService.addNewProduct(new Product("Waffle", 80, 1));
        vendingMachineService.addNewProduct(new Product("7-days", 130, 3));
    }
}
