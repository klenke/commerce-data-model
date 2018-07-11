package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.Shipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
    public List<Shipment> findByAccountIdOrderByDeliveredAsc(Long account_id);
}
