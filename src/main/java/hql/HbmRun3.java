package hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun3 {
    public static void main(String[] args) {
        Candidate rsl2 = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
/**
            Vacancy vacancy1 = Vacancy.of("java junior");
            Vacancy vacancy2 = Vacancy.of("java middle");
            session.persist(vacancy1);
            session.persist(vacancy2);

            DBVacancies dbVacancies = DBVacancies.of();
            dbVacancies.addVacancy(vacancy1);
            dbVacancies.addVacancy(vacancy2);
            session.persist(dbVacancies);

            Candidate candidate1 = Candidate.of("Вася", 2, 1000);
            candidate1.setVacancy(dbVacancies);
            session.persist(candidate1);
**/



            rsl2 = session.createQuery(
                    "select distinct st from Candidate st "
                            + "join fetch st.dbVacancies a "
                            + "join fetch a.vacancyList b "
                            + "where st.id = :sId", Candidate.class
            ).setParameter("sId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl2);
    }
}
