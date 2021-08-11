package collection.books.demo.controller;

import collection.books.demo.domain.Book;
import collection.books.demo.domain.Collection;
import collection.books.demo.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class MainController {

    private final BooksService booksService;

    @GetMapping
    public ResponseEntity<CollectionModel<Book>> showAll() {
        List<Book> bookList = booksService.findAll();
        return ResponseEntity.ok(CollectionModel.of(bookList));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Book>> showById(@PathVariable Long id) {
        Book book = booksService.findById(id);
        if (book == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EntityModel.of(book));
    }

    @GetMapping("collection")
    public ResponseEntity<CollectionModel<Collection>> showAllCollection() {
        List<Collection> collectionList = booksService.findAllCollection();
        return ResponseEntity.ok(CollectionModel.of(collectionList));
    }

    @GetMapping("collection/{id}")
    public ResponseEntity<EntityModel<Collection>> showCollection(@PathVariable Long id) {
        Collection collection = booksService.findCollectionById(id);
        if (collection == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EntityModel.of(collection));
    }

    @GetMapping("collection/{id}/book")
    public ResponseEntity<CollectionModel<Book>> showAllBooksFromCollection(@PathVariable Long id) {
        List<Book> bookList = booksService.findByCollection(booksService.findCollectionById(id));
        return ResponseEntity.ok(CollectionModel.of(bookList));
    }

}
