package dao;

import dbutil.HibernateUtil;
import model.User;
import org.hibernate.Transaction;
import model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionDao {
    public void save(Session sessionEntity){
        Transaction transaction = null;
        try(org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(sessionEntity);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Session> findByUserIdSession(int id){
        Transaction transaction = null;
        List<Session> sessions = new ArrayList<>();
        try(org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            sessions = session.createQuery("from Session where user.id= :id", Session.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sessions;
    }

    public Session findByIdSession(String id){
        Transaction transaction = null;
        Session sessionEntity = null;
        try(org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            sessionEntity = session.find(Session.class, id);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sessionEntity;
    }
    public Session findByIdSession1(User user){
        Transaction transaction = null;
        Session sessionEntity = null;
        try(org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            sessionEntity = session.find(Session.class, user);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sessionEntity;
    }
}
