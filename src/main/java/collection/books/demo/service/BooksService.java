package collection.books.demo.service;

import collection.books.demo.domain.Book;
import collection.books.demo.domain.Collection;
import collection.books.demo.repository.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BooksService implements IBooksService{

    private final BooksRepository rep;

    @Override
    public List<Book> findAll() {
        return (List<Book>) rep.findAll();
    }

    @Override
    public Book findByCollection(Collection collection) {
        return (Book) rep.findAllByCollection(collection);
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
}
