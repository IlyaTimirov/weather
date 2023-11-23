package service;

import dao.SessionDao;
import model.Session;
import model.User;

import java.util.Optional;

public class SessionService {
    private final SessionDao sessionDao = new SessionDao();
    public Session findByIdSession1(User user){
        return sessionDao.findByIdSession1(user);
    }
    public void save(Session session){
        sessionDao.save(session);
    }
    public Optional<Session> findByUserIdSession(int id){
        if(sessionDao.findByUserIdSession(id).isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(sessionDao.findByUserIdSession(id).get(0));
        }
    }
    public Session findByIdSession(String id){
        return sessionDao.findByIdSession(id);
    }
}
