package io.pivotal.commercedatamodel.controller;


import io.pivotal.commercedatamodel.domain.Account;
import io.pivotal.commercedatamodel.domain.Address;
import io.pivotal.commercedatamodel.domain.Shipment;
import io.pivotal.commercedatamodel.repository.AccountRepository;
import io.pivotal.commercedatamodel.repository.AddressRepository;
import io.pivotal.commercedatamodel.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping()
    public List<Shipment> shipments(){
        List<Shipment> shipments = new ArrayList<>();
        shipmentRepository.findAll().forEach(shipments::add);
        return shipments;
    }

    @GetMapping("/{id}")
    public Shipment shipment(@PathVariable("id") Long id){
        return shipmentRepository.findOne(id);
    }

    @PostMapping()
    public Shipment create(
            @RequestParam("accountId") Long accountId,
            @RequestParam("addressId") Long addressId
    ){
        Account account = accountRepository.findOne(accountId);
        Address address = addressRepository.findOne(addressId);

        return shipmentRepository.save(new Shipment(account, address, new Date(), new Date()));
    }

    @PutMapping()
    public Shipment update(
            @RequestParam("id") Long id,
            @RequestParam("accountId") Long accountId,
            @RequestParam("addressId") Long addressId
    ){
        Shipment shipment = shipmentRepository.findOne(id);
        shipment.setAccount(accountRepository.findOne(accountId));
        shipment.setAddress(addressRepository.findOne(addressId));
        return shipmentRepository.save(shipment);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        try{
            shipmentRepository.delete(id);
        } catch (Exception e) {
            return "Error deleting shipment";
        }
        return "Shipment successfully deleted";
    }

}
