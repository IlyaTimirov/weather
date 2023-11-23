package dao;

import dbutil.HibernateUtil;
import exception.already.AlreadyExistsForecastException;
import model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LocationDao {
    public void save(Location location){
        Transaction transaction;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
                transaction = session.beginTransaction();
                session.persist(location);
                transaction.commit();
        }catch (Exception e) {
            throw new AlreadyExistsForecastException();
        }
    }

    public void remove(Location location){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.remove(location);
            session.flush();
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Location> findUserId(int id){
        List<Location> locations = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            locations = session.createQuery("from Location where userId.id = :id", Location.class)
                    .setParameter("id", id)
                            .getResultList();
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return locations;
    }

    public Location findByCityAndUserId(String name, int id){
        Location location = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            location = session.createQuery("from Location where name = :name and userId.id = :id ", Location.class)
                    .setParameter("name", name)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return location;
    }
}
