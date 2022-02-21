package integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("postgres");
        pool.setPassword("QWE1243re");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("bd/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    @Ignore
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    @Ignore
    public void updateTest() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.get(0).getDescription(), is("description1"));

       store.update(new Order(1, "name2", "description2", new Timestamp(4)));

       all = (List<Order>) store.findAll();

       assertThat(all.get(0).getDescription(), is("description2"));

    }

    @Test
    @Ignore
    public void  findByNameTest() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));
        store.save(Order.of("name3", "description3"));

        assertThat(store.findByName("name2").getDescription(), is("description2"));
    }


    @Test
    @Ignore
    public void findByIdTest() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));
        store.save(Order.of("name2", "description2"));
        store.save(Order.of("name3", "description3"));

        assertThat(store.findById(2).getId(), is(2));
        assertThat(store.findById(2).getName(), is("name2"));
    }
}