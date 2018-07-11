package io.pivotal.commercedatamodel.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNumber;
    //private Long id;

   // @Column
   // private int orderNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"addresses", "firstName", "lastName", "email"})
    private Account account;

    @Column
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"account"})
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_number")
    private Set<OrderLineItem> orderLineItems;

    @Column
    private double price;

    public Order(){}

    public Order(Account account, Date orderDate, Address address) {
        this.account = account;
        this.orderDate = orderDate;
        this.address = address;
        this.price = 0.0;
    }

    /*public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }*/

    public Long getOrderNumber(){
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber){
        this.orderNumber = orderNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public double getPrice() {
        return price;
        /*double p = 0.0;
        if(this.orderLineItems.isEmpty()){
            return p;
        }
        for (OrderLineItem o: this.orderLineItems) {
            p += o.getTotalPrice();
        }
        return p;*/
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
