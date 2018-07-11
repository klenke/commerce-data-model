package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    public List<Order> findByAccountIdOrderByOrderDateAsc(Long account_id);

    public Order findByAccountIdAndOrderNumber(Long account_id, Long order_number);

}
