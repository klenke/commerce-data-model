package io.pivotal.commercedatamodel.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int orderNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderLineItem> orderLineItems;

    @Column
    private Long price;

    public Order(){}

    public Order(int orderNumber, Account account, Date orderDate, Address address, Set<OrderLineItem> orderLineItems, Long price) {
        this.orderNumber = orderNumber;
        this.account = account;
        this.orderDate = orderDate;
        this.address = address;
        this.orderLineItems = orderLineItems;
        this.price = price;
    }

    public Long getId(){
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
