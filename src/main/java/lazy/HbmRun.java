package lazy;

import lazy.MarkAuto;
import lazy.ModelAuto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            MarkAuto markAuto = MarkAuto.of("Lada");

            ModelAuto one = ModelAuto.of("LADA Granta", markAuto);
            ModelAuto two = ModelAuto.of("LADA Vesta", markAuto);
            ModelAuto tree = ModelAuto.of("LADA Granta", markAuto);
            ModelAuto four = ModelAuto.of("LADA XRAY", markAuto);
            ModelAuto five = ModelAuto.of("LADA Largus", markAuto);

            session.persist(one);
            session.persist(two);
            session.persist(tree);
            session.persist(four);
            session.persist(five);

            markAuto.getModelAutoArrayList().add(one);
            markAuto.getModelAutoArrayList().add(two);
            markAuto.getModelAutoArrayList().add(tree);
            markAuto.getModelAutoArrayList().add(four);
            markAuto.getModelAutoArrayList().add(five);

            session.persist(markAuto);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
