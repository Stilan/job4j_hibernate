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

            ModelAuto one = ModelAuto.of("LADA Granta");
            session.save(one);
            ModelAuto two = ModelAuto.of("LADA Vesta");
            session.save(two);
            ModelAuto tree = ModelAuto.of("LADA Granta");
            session.save(tree);
            ModelAuto four = ModelAuto.of("LADA XRAY");
            session.save(four);
            ModelAuto five = ModelAuto.of("LADA Largus");
            session.save(five);


            MarkAuto markAuto = MarkAuto.of("Lada");
            markAuto.addUser(session.load(ModelAuto.class, 1));
            markAuto.addUser(session.load(ModelAuto.class, 2));
            markAuto.addUser(session.load(ModelAuto.class, 3));
            markAuto.addUser(session.load(ModelAuto.class, 4));
            markAuto.addUser(session.load(ModelAuto.class, 5));
            session.save(markAuto);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
