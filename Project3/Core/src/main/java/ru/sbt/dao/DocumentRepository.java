package ru.sbt.dao;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.data.Document;

public interface DocumentRepository extends CrudRepository<Document, Long>{
}
