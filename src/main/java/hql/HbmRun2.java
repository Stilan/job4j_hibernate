package hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun2 {
    public static void main(String[] args) {
        Student rsl = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
/**
            Book book1 = Book.of("Двенадцать стульев", "АСТ");
            Book book2 = Book.of("Одноитажная америка", "Текст");

            session.persist(book1);
            session.persist(book2);

            Account account = Account.of("root");
            account.addBook(book1);
            account.addBook(book2);

            session.persist(account);

            Student student = Student.of("Иванов Иван", 20, "Москва");
            student.setAccount(account);
            session.persist(student);
 **/
            rsl = session.createQuery(
                    "select distinct st from Student st "
                            + "join fetch st.account a "
                            + "join fetch a.books b "
                            + "where st.id = :sId", Student.class
            ).setParameter("sId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}
