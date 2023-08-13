Vending machine application.

The application contains Models, Services, RestController and Constants.

Main file: com.example.ven_machine.VenMachineApplication

You can start the application with the following command: mvn spring-boot:run

In the start of the application the following products will be added:

name: "Coca-Cola", price: 110, quantity: 5
name: "Pepsi", price: 100, quantity: 9
name: "Waffle", price:  80, quantity: 1
name: "7-days", price:  130,quantity:  3

Rest endpoints:

@PostMapping( "/add/product") - add new product in the vending machine, you must add product body to the request
expample: {
    "name": "Cake",
    "price": 3,
    "quantity": 3
}

@PostMapping("/insert/product") - insert product in the vending machine, you must provide the body like the example:

{
"productName": "Coca-Cola",
"quantity": 6
}

@PostMapping(value = "/insert/coin") - insert coin, you must provide int value in the body of the request
true values are: 10, 20 50, 100, 200

@PostMapping("/buy") - buy product, you must specify the productName in the body

@GetMapping("/return/coin") - returns the last inserted coin

@PostMapping("/update/product") - updating existing product, you must provide product body to the request

@PostMapping("/update/product") - deletes existing product, you must provide productName in the body of the request.