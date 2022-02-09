import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "j_modelAuto")
public class ModelAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;

    public static ModelAuto of(String name) {
        ModelAuto modelAuto = new ModelAuto();
        modelAuto.name = name;
        return modelAuto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelAuto modelAuto = (ModelAuto) o;
        return id == modelAuto.id && Objects.equals(name, modelAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
