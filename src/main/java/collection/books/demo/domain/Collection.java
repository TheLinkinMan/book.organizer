package collection.books.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Collection {
    @Id
    private Long id;

    private String name;
}
