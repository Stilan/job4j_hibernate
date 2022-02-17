package hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "DBVacancies")
public class DBVacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancyList = new ArrayList<>();

    public static DBVacancies of() {
        DBVacancies dbVacancies = new DBVacancies();
        return dbVacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        this.vacancyList.add(vacancy);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void setBooks(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DBVacancies that = (DBVacancies) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DBVacancies{"
                + "id=" + id
                + ", vacancyList=" + vacancyList
                + '}';
    }
}
