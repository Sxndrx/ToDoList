package toDoApp;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory;
    private static final String persistenceName = "ToDoPersistence";

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);
        } catch (Exception e) {
            System.err.println("Initial EntityManagerFactory creation failed." + e);
            e.printStackTrace();
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}