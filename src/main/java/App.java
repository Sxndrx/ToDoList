import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistence");
        EntityManager em = entityManagerFactory.createEntityManager();
//        String[] names = {"Josh", "Lisa", "Portia", "Greg"};
//        for(String name : names){
//            em.getTransaction().begin();
//            Employee employee = new Employee();
//            employee.setName(name);
//            em.persist(employee);
//            em.getTransaction().commit();
//        }
        em.getTransaction().begin();
//        String query = "db.Employee.find({})";
        String query = "{$and : [{name : 'Josh'}]}";
        List<Employee> employees = em.createNativeQuery(query, Employee.class).getResultList();
        em.close();
        for(Employee employee : employees ){
            System.err.println(employee.toString());
        }
        entityManagerFactory.close();
    }
}
