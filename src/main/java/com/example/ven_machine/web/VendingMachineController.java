package com.example.ven_machine.web;

import com.example.ven_machine.constants.ResponseStatus;
import com.example.ven_machine.models.Product;
import com.example.ven_machine.models.dtos.Change;
import com.example.ven_machine.models.dtos.InsertProductDto;
import com.example.ven_machine.services.VendingMachineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.ven_machine.constants.CoinsConstants.COINS;

@RestController
public class VendingMachineController {

    private VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }


    @GetMapping("/all/products")
    public Map<String, Product> getAllProducts(){

        return vendingMachineService.getProducts();
    }

    @PostMapping( "/add/product")
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        String status = vendingMachineService.addNewProduct(product);
        if (status.equals(ResponseStatus.OK.toString())){
            return new ResponseEntity<String>("Product added!",HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insert/product")
    public ResponseEntity<String> insertProduct(@RequestBody InsertProductDto insertProductDto){
        System.out.println(insertProductDto);
        String status = vendingMachineService.insertProduct(insertProductDto.getProductName(), insertProductDto.getQuantity());
        if (status.equals(ResponseStatus.OK.toString())){
            return new ResponseEntity<String>("Product inserted!",HttpStatus.OK);
        }else {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/insert/coin",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> insertCoin(@RequestBody Integer coinValue){
        if(COINS.contains(coinValue)){

            this.vendingMachineService.insertCoin(coinValue);
            return new ResponseEntity<>("Coin inserted!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid coin, Valid coins are: 10, 20, 50, 100, 200", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/buy")
    public ResponseEntity<Change> buy(@RequestBody String productName){
        List<Integer> listProductMap = this.vendingMachineService.buyProduct(productName);
        Change change = new Change(listProductMap);
        if (listProductMap != null){
            return new ResponseEntity(change, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/return/coin")
    public ResponseEntity<String> returnCoin(){
        Integer coin = this.vendingMachineService.returnCoin();
        return  coin != null ? new ResponseEntity<>(String.valueOf(coin), HttpStatus.OK) :
                new ResponseEntity<>("No coins inserted!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update/product")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        return this.vendingMachineService.updateProduct(product).equals(ResponseStatus.OK.toString()) ?
                new ResponseEntity<>("Product updated!", HttpStatus.OK) :
                new ResponseEntity<>("No such Product!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/remove/product")
    public ResponseEntity<String> removeProduct(@RequestBody String productName){
        return this.vendingMachineService.removeProduct(productName).equals(ResponseStatus.OK.toString()) ?
                new ResponseEntity<>("Product removed!", HttpStatus.OK)
                : new ResponseEntity<>("No such product!", HttpStatus.BAD_REQUEST);
    }
}
