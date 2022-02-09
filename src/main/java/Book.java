import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Author> addAuthor = new HashSet<>();

    public static Book of(String name) {
        Book book = new Book();
        book.name = name;
        return book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAddAuthor() {
        return addAuthor;
    }

    public void setAddAuthor(Set<Author> addAuthor) {
        this.addAuthor = addAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(addAuthor, book.addAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addAuthor);
    }
}
