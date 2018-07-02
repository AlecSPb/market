package ru.tsystem.javaschool.ordinaalena.DAO;



import ru.tsystem.javaschool.ordinaalena.SessionFactorySingleton;
import ru.tsystem.javaschool.ordinaalena.models.UsertEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UsertDAOImpl implements UsertDAO{

    private static SessionFactory sessionFactory;
    private static UsertDAOImpl instance;

    public UsertDAOImpl(SessionFactory sessionFactory) {

        UsertDAOImpl.sessionFactory = sessionFactory;
    }
    public static UsertDAO getUsertDAOInstance(){
        if (instance == null){
            synchronized (UsertDAOImpl.class){
                instance = new UsertDAOImpl(SessionFactorySingleton.getSessionFactoryInstance());
            }
        }
        return instance;
    }


    @Override
    public UsertEntity create(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = new UsertEntity ();
        u.setUsername(username);
        u.setPassword(password);
        session.persist(u);
        transaction.commit();
        if (session.isOpen()) session.close();
        return u;
    }

    @Override
    public UsertEntity getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = session.get(UsertEntity.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return u;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = getById(id);
        session.delete(u);
        transaction.commit();
        if (session.isOpen()) session.close();

    }

    @Override
    public UsertEntity update(int id,String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = session.get(UsertEntity.class, id);
        u.setUsername(username);
        u.setPassword(password);
        session.saveOrUpdate(u);
        transaction.commit();
        if (session.isOpen()) session.close();
        return u;
    }
}

