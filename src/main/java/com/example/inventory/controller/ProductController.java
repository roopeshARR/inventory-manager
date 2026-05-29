package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.model.History;
import com.example.inventory.service.ProductService;
import com.example.inventory.repository.HistoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService service;


    /* ================= PRODUCTS ================= */

    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getAll();
    }


    /* ================= ADD ================= */

    @PostMapping("/add")
    public String add(@RequestBody Product p){

        System.out.println("🔥 ADD API CALLED");

        return service.addProduct(p);
    }


    /* ================= SELL ================= */

    @PostMapping("/sell")
    public String sell(@RequestParam int id,@RequestParam int qty){

        System.out.println("🔥 SELL API CALLED");

        return service.sell(id,qty);
    }


    /* ================= RESTOCK ================= */

    @PostMapping("/restock")
    public String restock(@RequestParam int id,@RequestParam int qty){

        System.out.println("🔥 RESTOCK API CALLED");

        return service.restock(id,qty);
    }


    /* ================= DELETE ================= */

    @PostMapping("/delete-variant")
    public void deleteVariant(@RequestParam int id){

        System.out.println("🔥 DELETE VARIANT API CALLED");

        service.deleteVariant(id);
    }


    @PostMapping("/delete-brand")
    public void deleteBrand(@RequestParam String name,@RequestParam String brand){

        System.out.println("🔥 DELETE BRAND API CALLED");

        service.deleteBrand(name,brand);
    }


    @PostMapping("/delete-item")
    public void deleteItem(@RequestParam String name){

        System.out.println("🔥 DELETE ITEM API CALLED");

        service.deleteItem(name);
    }


    /* ================= HISTORY ================= */
    @GetMapping("/")
    public String home() {
        return "Inventory Manager Backend Running Successfully";
    }
    
}