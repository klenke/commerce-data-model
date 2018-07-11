package io.pivotal.commercedatamodel.controller;


import io.pivotal.commercedatamodel.domain.Account;
import io.pivotal.commercedatamodel.domain.Address;
import io.pivotal.commercedatamodel.repository.AccountRepository;
import io.pivotal.commercedatamodel.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping()
    public List<Address> address(){
        List<Address> addresses = new ArrayList<>();
        addressRepository.findAll().forEach(addresses::add);
        return addresses;
    }

    @GetMapping("/{id}")
    public Address address(@PathVariable("id") Long id){
        return addressRepository.findOne(id);
    }

    @PostMapping()
    public Address create(
            @RequestParam("street") String street,
            @RequestParam("apt")String apt,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("zip") String zip,
            @RequestParam("country") String country,
            @RequestParam("account_id") Long accountId){


        Account account = accountRepository.findOne(accountId);

        /*Set<Address> addresses = account.getAddresses();
        addresses.add(new Address(street, apt, city, state, zip, country, account));
        account.setAddresses(addresses);*/

        return addressRepository.save(new Address(street, apt, city, state, zip, country, account));

    }

    @PutMapping()
    public Address update(
            @RequestParam("address_id") Long addressId,
            @RequestParam("street") String street,
            @RequestParam("apt")String apt,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("zip") String zip,
            @RequestParam("country") String country,
            @RequestParam("account_id") Long accountId){

        Address address = addressRepository.findOne(addressId);
        if(!street.isEmpty()){
            address.setStreet(street);
        }
        if(!apt.isEmpty()){
            address.setApt(apt);
        }
        if(!city.isEmpty()) {
            address.setCity(city);
        }
        if(!state.isEmpty()) {
            address.setState(state);
        }
        if(!zip.isEmpty()) {
            address.setZip(zip);
        }
        if(!country.isEmpty()){
            address.setCountry(country);
        }
        if(accountId != null) {
            address.setAccount(accountRepository.findOne(accountId));
        }
        return addressRepository.save(address);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id){
        try{
            addressRepository.delete(id);
        } catch (Exception e){
            return "Error deleting address";
        }

        return "Address Deleted";
    }
}
