package lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "j_markAuto")
public class MarkAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;

    @OneToMany(mappedBy = "markAuto")
    private List<ModelAuto> modelAutoArrayList = new ArrayList<>();


    public static MarkAuto of(String name) {
        MarkAuto markAuto = new MarkAuto();
        markAuto.name = name;
        return markAuto;
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

    public List<ModelAuto> getModelAutoArrayList() {
        return modelAutoArrayList;
    }

    public void setModelAutoArrayList(List<ModelAuto> modelAutoArrayList) {
        this.modelAutoArrayList = modelAutoArrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarkAuto markAuto = (MarkAuto) o;
        return id == markAuto.id && Objects.equals(name, markAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MarkAuto{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
