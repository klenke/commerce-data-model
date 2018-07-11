package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
