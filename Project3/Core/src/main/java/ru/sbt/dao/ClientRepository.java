package ru.sbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.data.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
    Client findByName(String name);
}
