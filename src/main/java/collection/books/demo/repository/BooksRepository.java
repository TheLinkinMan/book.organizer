package collection.books.demo.repository;

import collection.books.demo.domain.Book;
import collection.books.demo.domain.Collection;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findAllByCollection(Collection collection);
}
