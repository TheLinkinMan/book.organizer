package collection.books.demo.controller;

import collection.books.demo.domain.Book;
import collection.books.demo.domain.Collection;
import collection.books.demo.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book currentBook = booksService.add(book);
        if (currentBook == null)
            return ResponseEntity.badRequest().build();
        Link link = linkTo(methodOn(MainController.class).showById(currentBook.getId())).withSelfRel();
        return ResponseEntity.created(link.toUri()).build();
    }

    @PostMapping("collection/")
    public ResponseEntity<Collection> addCollection(@RequestBody Collection collection) {
        Collection currentCollection = booksService.addCollection(collection);
        if (currentCollection == null)
            return ResponseEntity.badRequest().build();
        Link link = linkTo(methodOn(MainController.class).showCollection(currentCollection.getId())).withSelfRel();
        return ResponseEntity.created(link.toUri()).build();
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book currentBook = booksService.findById(book.getId());
        if (currentBook == null)
            return ResponseEntity.badRequest().build();
        booksService.add(book);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("collection/")
    public ResponseEntity<Book> updateCollection(@RequestBody Collection collection) {
        Collection currentCollection = booksService.findCollectionById(collection.getId());
        if (currentCollection == null)
            return ResponseEntity.badRequest().build();
        booksService.addCollection(collection);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteBook(@RequestBody Book book) {
        Book currentBook = booksService.findById(book.getId());
        if (currentBook == null)
            return ResponseEntity.badRequest().build();
        booksService.delete(book);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("collection/")
    public ResponseEntity<Object> deleteCollection(@RequestBody Collection collection) {
        Collection currentCollection = booksService.findCollectionById(collection.getId());
        if (currentCollection == null)
            return ResponseEntity.badRequest().build();
        booksService.deleteCollection(collection);
        return ResponseEntity.accepted().build();
    }
}
