package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.OrderLineItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {
}
