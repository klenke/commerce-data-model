package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
}
