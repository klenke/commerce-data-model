package io.pivotal.commercedatamodel.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Table(name = "order_line_item")
@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"name","description","image", "price"})
    private Product product;

    @Column
    private int quantity;

    @Column
    private double price;

    @Column
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    @JsonIgnoreProperties({"orderLineItems", "address", "account", "shipped", "delivered"})
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "order_number")
    @JsonIgnoreProperties({"account", "orderDate", "address", "orderLineItems", "price"})
    private Order order;

    public OrderLineItem(){}

    public OrderLineItem(Product product, int quantity, Shipment shipment, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.shipment = shipment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.product.getPrice();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
