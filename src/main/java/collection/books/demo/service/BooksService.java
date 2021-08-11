package collection.books.demo.service;

import collection.books.demo.domain.Book;
import collection.books.demo.domain.Collection;
import collection.books.demo.repository.BooksRepository;
import collection.books.demo.repository.CollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BooksService implements IBooksService{

    private final BooksRepository rep;

    private final CollectionRepository repCollection;

    @Override
    public List<Book> findAll() {
        return (List<Book>) rep.findAll();
    }

    @Override
    public Book findById(Long id) {
        return rep.findById(id).orElse(null);
    }

    @Override
    public List<Book> findByCollection(Collection collection) {
        return (List<Book>) rep.findAllByCollection(collection);
    }

    @Override
    public Book add(Book book) {
        return rep.save(book);
    }

    @Override
    public void delete(Book book) {
        if (book != null) {
            rep.delete(book);
        }
    }

    @Override
    public List<Collection> findAllCollection() {
        return (List<Collection>) repCollection.findAll();
    }

    @Override
    public Collection findCollectionById(Long id) {
        return repCollection.findById(id).orElse(null);
    }

    @Override
    public Collection addCollection(Collection collection) {
        return repCollection.save(collection);
    }

    @Override
    public void deleteCollection(Collection collection) {
        repCollection.delete(collection);
    }
}
