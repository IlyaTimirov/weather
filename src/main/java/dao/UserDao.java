package dao;

import dbutil.HibernateUtil;
import exception.already.UserAlreadyExistsException;
import exception.notfound.UserNotFoundException;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class UserDao{
    public User getById(int id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public void save(User user) throws UserAlreadyExistsException {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }catch (Exception e) {
            throw new UserAlreadyExistsException();
        }
    }
    public User findByLogin(String login) throws UserNotFoundException {
        Transaction transaction;
        User user;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.createQuery("from User where login= :login", User.class)
                            .setParameter("login", login)
                                    .getSingleResult();
            transaction.commit();
        }catch (Exception e) {
            throw new UserNotFoundException();
        }
        return user;
    }

}
