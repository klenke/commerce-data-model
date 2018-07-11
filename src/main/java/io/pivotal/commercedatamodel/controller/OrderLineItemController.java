package io.pivotal.commercedatamodel.controller;


import io.pivotal.commercedatamodel.domain.Order;
import io.pivotal.commercedatamodel.domain.OrderLineItem;
import io.pivotal.commercedatamodel.domain.Product;
import io.pivotal.commercedatamodel.domain.Shipment;
import io.pivotal.commercedatamodel.repository.OrderLineItemRepository;
import io.pivotal.commercedatamodel.repository.OrderRepository;
import io.pivotal.commercedatamodel.repository.ProductRepository;
import io.pivotal.commercedatamodel.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LineItems")
public class OrderLineItemController {

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping("/{id}")
    public OrderLineItem orderLineItem(@PathVariable("id") Long id){
        return orderLineItemRepository.findOne(id);
    }

    @PostMapping()
    public OrderLineItem create(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity,
            @RequestParam("shipmentId") Long shipmentId,
            @RequestParam("orderNumber") Long orderNumber){

        Product p = productRepository.findOne(productId);
        Shipment s = shipmentRepository.findOne(shipmentId);
        Order o = orderRepository.findOne(orderNumber);

        o.setPrice(o.getPrice() + p.getPrice() * quantity);

        return orderLineItemRepository.save(new OrderLineItem(p, quantity, s, o));

    }

    @PutMapping
    public OrderLineItem update(
            @RequestParam("id") Long id,
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity,
            @RequestParam("shipmentId") Long shipmentId,
            @RequestParam("orderNumber") Long orderNumber
    ){
        OrderLineItem orderLineItem = orderLineItemRepository.findOne(id);
        orderLineItem.setProduct(productRepository.findOne(productId));
        orderLineItem.setShipment(shipmentRepository.findOne(shipmentId));
        orderLineItem.setOrder(orderRepository.findOne(orderNumber));
        orderLineItem.setQuantity(quantity);
        orderLineItem.setTotalPrice(quantity * orderLineItem.getPrice());

        return orderLineItemRepository.save(orderLineItem);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        try{
            orderLineItemRepository.delete(id);
        } catch (Exception e){
            return "Error deleting Order Line Item";
        }
        return "Order Line Item deleted";
    }
}
