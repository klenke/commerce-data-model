package io.pivotal.commercedatamodel.controller;


import io.pivotal.commercedatamodel.domain.Account;
import io.pivotal.commercedatamodel.domain.Address;
import io.pivotal.commercedatamodel.domain.Order;
import io.pivotal.commercedatamodel.domain.Shipment;
import io.pivotal.commercedatamodel.repository.AccountRepository;
import io.pivotal.commercedatamodel.repository.OrderRepository;
import io.pivotal.commercedatamodel.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @PostMapping()
    public Account create(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email){

        return accountRepository.save(new Account(firstName, lastName, email));
    }

    @PostMapping("/withaddress")
    public Account createWithAddress(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("street") String street,
            @RequestParam("apt")String apt,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("zip") String zip,
            @RequestParam("country") String country){

        if(!street.isEmpty()){
            Set<Address> addresses = new HashSet<>();
            addresses.add(new Address(street, apt, city, state, zip, country));
            return accountRepository.save(new Account(firstName, lastName, email, addresses));
        } else {
            return accountRepository.save(new Account(firstName, lastName, email));
        }
    }

    @PutMapping()
    public Account update(
            @RequestParam("id") Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email){

        Account account = accountRepository.findOne(id);
        if(!firstName.isEmpty()){
            account.setFirstName(firstName);
        }
        if(!lastName.isEmpty()){
            account.setLastName(lastName);
        }
        if(!email.isEmpty()){
            account.setEmail(email);
        }
        return accountRepository.save(account);
    }

    @GetMapping("/{id}")
    public Account account(@PathVariable("id") Long id){
        //Account account = accountRepository.findOne(id);
        return accountRepository.findOne(id);
    }

    @GetMapping("/{id}/orders")
    public List<Order> listAccountOrders(@PathVariable("id") Long id){
        return orderRepository.findByAccountIdOrderByOrderDateAsc(id);
    }

    @GetMapping("/{id}/orders/{orderNumber}")
    public Order orderDetail(@PathVariable("id") Long id, @PathVariable("orderNumber") Long orderNumber){
        return orderRepository.findByAccountIdAndOrderNumber(id, orderNumber);
    }

    @GetMapping("/{id}/shipments")
    public List<Shipment> listShipments(@PathVariable("id") Long id){
        return shipmentRepository.findByAccountIdOrderByDeliveredAsc(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") String id){
        try{
            accountRepository.delete(Long.parseLong(id));
        } catch (Exception e){
            return "Error";
        }
        return "Done";
    }

    @GetMapping()
    public List<Account> accounts(){
        //Iterable<Account> repoAccounts = accountRepository.findAll();
        List<Account> accounts = new ArrayList<>();
        //repoAccounts.forEach(accounts::add);
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }
}
