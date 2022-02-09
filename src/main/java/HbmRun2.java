import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun2 {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Author one = Author.of("Джошуа Блох");
            Author two = Author.of("Кэти Сьерра");
            Author tree = Author.of("Берт Бейтс");

            Book book1 = Book.of("Java. Эффективное программирование");
            book1.getAddAuthor().add(one);

            Book book2 = Book.of("Изучаем Java");
            book2.getAddAuthor().add(two);
            book2.getAddAuthor().add(tree);

            session.persist(book1);
            session.persist(book2);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
