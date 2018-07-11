package io.pivotal.commercedatamodel.repository;

import io.pivotal.commercedatamodel.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
