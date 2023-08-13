package com.example.ven_machine;

import com.example.ven_machine.models.Product;
import com.example.ven_machine.services.VendingMachineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VendingMachineServiceTest {

    private VendingMachineService vendingMachineService = new VendingMachineService();



    @Test
    public void testAddProductAndGetProduct() {
        Product testProduct = new Product("Cake", 200, 5);
        vendingMachineService.addNewProduct(testProduct);
        Assertions.assertEquals(vendingMachineService.getProduct("Cake"), testProduct);
    }



    @Test
    public void testInsertProduct() {
        vendingMachineService.insertProduct("ProteinBar", 2);
        System.out.println(vendingMachineService.getProduct("Cola").getQuantity());
        Assertions.assertEquals(vendingMachineService.getProduct("ProteinBar").getQuantity(), 8);
    }

    @Test
    public void testInsertCoin() {
        vendingMachineService.insertCoin(20);
        Assertions.assertEquals(vendingMachineService.getInsertedCoins(), 20);
    }

    @Test
    public void testRemoveProduct() {
        Product testProduct = new Product("Cake", 200, 5);
        vendingMachineService.addNewProduct(testProduct);

        Assertions.assertEquals(vendingMachineService.getProduct("Cake"), testProduct);

        vendingMachineService.removeProduct("Cake");
        Assertions.assertEquals(vendingMachineService.getProduct("Cake"),null);
    }

    @Test
    public void testBuy() {
        vendingMachineService.insertCoin(200);
        List<Integer> change = vendingMachineService.buyProduct("Cola");
        Assertions.assertEquals(vendingMachineService.getProduct("Cola").getQuantity(), 4);
        Assertions.assertArrayEquals(change.toArray(), new Integer[] {20,20,50});
    }

    @Test
    public void testUpdateProductWithInvalidData() {
        String message = vendingMachineService.updateProduct(new Product("Coca-Cola", 100, 5));
        Assertions.assertEquals(message, "No such product!");
    }

    @BeforeAll
    public void init() {
        Product testProduct = new Product("Cola", 110, 5);
        Product testProduct2 = new Product("ProteinBar", 150, 6);
        vendingMachineService.addNewProduct(testProduct);
        vendingMachineService.addNewProduct(testProduct2);
    }

}
