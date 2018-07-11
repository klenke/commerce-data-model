package io.pivotal.commercedatamodel.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "shipment")
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"addresses", "firstName", "lastName", "email"})
    private Account account;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"account"})
    private Address address;

    @OneToMany
    @JoinColumn(name="shipment_id")
    private Set<OrderLineItem> orderLineItems;

    @Column
    private Date shipped;

    @Column
    private Date delivered;

    public Shipment(){}

    public Shipment(Account account, Address address, Date shipped, Date delivered) {
        this.account = account;
        this.address = address;
        this.shipped = shipped;
        this.delivered = delivered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(Set<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }
}
