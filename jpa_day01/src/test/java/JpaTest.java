import com.domain.Customer;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class JpaTest {
    @Test
    public void demo1(){
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("myJpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("test");
        customer.setCustIndustry("教育");
        //添加
/*        entityManager.persist(customer);*/
        //查询
        Customer customer1 = entityManager.find(Customer.class, 1l);
        //update
        customer1.setCustName("new test");
        entityManager.merge(customer1);
        //删除
/*        Customer customer2 = entityManager.getReference(customer.getClass(), 2l);
        customer2.setCustId(2l);*/
/*        entityManager.remove(customer2);*/
        //
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void test1(){
        System.out.println("sd");
    }
    @Test
    public void test2(){
        EntityManagerFactory myJpa = Persistence.createEntityManagerFactory("myJpa");
        EntityManager entityManager = myJpa.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            String jpql="from Customer";
            Query query = entityManager.createQuery(jpql);
            Object resultList = query.getResultList();
            System.out.println(resultList);
        }catch (Exception e){
            transaction.rollback();
        }
    }
}
