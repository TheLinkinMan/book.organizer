package collection.books.demo.repository;

import collection.books.demo.domain.Collection;
import org.springframework.data.repository.CrudRepository;

public interface CollectionRepository extends CrudRepository<Collection, Long> {
}
