package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("create table if not exists users" +
                                         "(id  bigint auto_increment primary key,"  +
                                         "name     varchar(30)  null,"  +
                                         "lastName varchar(30)  null," +
                                         "age tinyint null)").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("drop table if exists users").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        try {
           User user = new User();
           user.setId(id);
           session.beginTransaction();
           session.delete(user);
           session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            List<User> users = session.createQuery("select u from User u",
                    User.class).list();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE users").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
