package collection.books.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    private Long id;

    private String name;

    private Integer countPage;

    @ManyToOne(targetEntity = Collection.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collection;
}
