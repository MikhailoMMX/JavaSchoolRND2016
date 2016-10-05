package ru.sbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.data.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccNum(String accNum);
}
