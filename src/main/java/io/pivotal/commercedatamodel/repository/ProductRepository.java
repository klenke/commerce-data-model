package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
