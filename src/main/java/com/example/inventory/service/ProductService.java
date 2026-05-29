package com.example.inventory.service;

import com.example.inventory.model.Product;
import com.example.inventory.model.History;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.HistoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    @Autowired
    HistoryRepo historyRepo;


    /* ================= GET ALL ================= */

    public List<Product> getAll(){
        return repo.findAll();
    }


    /* ================= ADD ================= */

    public String addProduct(Product p){

        Product existing =
        repo.findByNameAndBrandAndVariant(
            p.getName(),
            p.getBrand(),
            p.getVariant()
        );

        if(existing != null){
            return "Item already exists. Please restock.";
        }

        repo.save(p);

        saveHistory("ADD", p, p.getQuantity());

        return "Item added successfully";
    }


    /* ================= SELL ================= */

    public String sell(int id,int qty){

        Product p = repo.findById(id).orElse(null);

        if(p==null) return "Variant not found";

        if(qty > p.getQuantity())
            return "Insufficient stock";

        p.setQuantity(p.getQuantity() - qty);
        repo.save(p);

        saveHistory("SELL", p, qty);

        return "Stock reduced";
    }


    /* ================= RESTOCK ================= */

    public String restock(int id,int qty){

        Product p = repo.findById(id).orElse(null);

        if(p==null) return "Variant not found";

        p.setQuantity(p.getQuantity() + qty);
        repo.save(p);

        saveHistory("RESTOCK", p, qty);

        return "Stock increased";
    }


    /* ================= DELETE VARIANT ================= */

    public void deleteVariant(int id){

        Product p = repo.findById(id).orElse(null);

        if(p != null){
            saveHistory("DELETE", p, p.getQuantity());
            repo.deleteById(id);
        }
    }


    /* ================= DELETE BRAND ================= */

    public void deleteBrand(String name,String brand){

        List<Product> list = repo.findAll();

        for(Product p : list){
            if(p.getName().equals(name) && p.getBrand().equals(brand)){

                saveHistory("DELETE", p, p.getQuantity());
                repo.deleteById(p.getId());
            }
        }
    }


    /* ================= DELETE ITEM ================= */

    public void deleteItem(String name){

        List<Product> list = repo.findAll();

        for(Product p : list){
            if(p.getName().equals(name)){

                saveHistory("DELETE", p, p.getQuantity());
                repo.deleteById(p.getId());
            }
        }
    }


    /* ================= HISTORY ================= */

    private void saveHistory(String action, Product p, int qty){

        System.out.println("🔥 HISTORY FUNCTION CALLED: " + action);

        History h = new History();

        h.setAction(action);
        h.setItemName(p.getName());
        h.setBrand(p.getBrand());
        h.setVariant(p.getVariant());
        h.setQuantity(qty);
        h.setTime(java.time.LocalDateTime.now());

        historyRepo.save(h);

        System.out.println("✅ SAVED TO DB");
    }
}