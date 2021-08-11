package collection.books.demo.service;

import collection.books.demo.domain.Book;
import collection.books.demo.domain.Collection;

import java.util.List;

public interface IBooksService {

    List<Book> findAll();

    Book findById(Long id);

    List<Book> findByCollection(Collection collection);

    Book add(Book book);

    void delete(Book book);

    List<Collection> findAllCollection();

    Collection findCollectionById(Long id);
}
