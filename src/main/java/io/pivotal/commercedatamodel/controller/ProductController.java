package io.pivotal.commercedatamodel.controller;


import io.pivotal.commercedatamodel.domain.Product;
import io.pivotal.commercedatamodel.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public List<Product> product(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable("id") Long id){
        return productRepository.findOne(id);
    }

    @PostMapping()
    public Product create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price
    ){
        return productRepository.save(new Product(name, description, price));
    }

    @PutMapping()
    public Product update(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price){

        Product product = productRepository.findOne(id);
        if(!name.isEmpty()){
            product.setName(name);
        }
        if(!description.isEmpty()){
            product.setDescription(description);
        }
        product.setPrice(price);

        return productRepository.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        try{
            productRepository.delete(id);
        } catch (Exception e){
            return "Error deleting product";
        }
        return "Successfully deleted product";
    }
}
