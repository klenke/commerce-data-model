package io.pivotal.commercedatamodel.controller;


import io.pivotal.commercedatamodel.domain.Account;
import io.pivotal.commercedatamodel.domain.Address;
import io.pivotal.commercedatamodel.domain.Order;
import io.pivotal.commercedatamodel.repository.AccountRepository;
import io.pivotal.commercedatamodel.repository.AddressRepository;
import io.pivotal.commercedatamodel.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/{orderNumber}")
    public Order order(@PathVariable("orderNumber") Long orderNumber){
        return orderRepository.findOne(orderNumber);
    }

    @PostMapping()
    public Order create(
            @RequestParam("accountId") Long accountId,
            @RequestParam("addressId") Long addressId){

        Account account = accountRepository.findOne(accountId);
        Address address = addressRepository.findOne(addressId);

        return orderRepository.save(new Order(account, new Date(), address));

    }

    @PutMapping()
    public Order update(
            @RequestParam("orderNumber") Long orderNumber,
            @RequestParam("accountId") Long accountId,
            @RequestParam("addressId") Long addressId){

        Order order = orderRepository.findOne(orderNumber);
        order.setAccount(accountRepository.findOne(accountId));
        order.setAddress(addressRepository.findOne(addressId));

        return orderRepository.save(order);
    }

    @DeleteMapping("/delete/{orderNumber}")
    public String delete(@PathVariable("orderNumber") Long orderNumber){
        try{
            orderRepository.delete(orderNumber);
        } catch (Exception e){
            return "Error deleting order";
        }
        return "Order deleted";
    }



}
