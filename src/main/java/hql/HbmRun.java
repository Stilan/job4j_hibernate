package hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
 /**
            Candidate candidate1 = Candidate.of("Вася", 2, 1000);
            Candidate candidate2 = Candidate.of("Петя", 4, 700);
            Candidate candidate3 = Candidate.of("Толя", 5, 1200);

            session.save(candidate1);
            session.save(candidate2);
            session.save(candidate3);
  **/

            Query query = session.createQuery("from Candidate ");
            for (Object st : query.list()) {
                System.out.println(st);
            }

            query = session.createQuery("from Candidate s where s.id = :fId");
            query.setParameter("fId", 1);
            System.out.println(query.uniqueResult());

            query = session.createQuery("from Candidate s where s.name = :nameCan");
            query.setParameter("nameCan", "Вася");
            System.out.println(query.uniqueResult());

            query = session.createQuery(
                    "update Candidate s set s.name = :nameCan, s.experience = :experienceCan, s.salary = :salaryCan where s.id = :fId"
            );
            query.setParameter("nameCan", "Дима");
            query.setParameter("experienceCan", 6);
            query.setParameter("salaryCan", 2400);
            query.setParameter("fId", 1);
            query.executeUpdate();

            query = session.createQuery("delete from Candidate where id = :fId");
            query.setParameter("fId", 2);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
