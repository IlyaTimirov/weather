package service;

import dao.UserDao;
import exception.already.UserAlreadyExistsException;
import exception.notfound.UserNotFoundException;
import model.User;
public class UserService {
    private final UserDao userDao = new UserDao();

    public void save(User user) throws UserAlreadyExistsException {
        userDao.save(user);
    }
    public User getById(int id) {
        return userDao.getById(id);
    }
    public User findByLogin(String login) throws UserNotFoundException {
        return userDao.findByLogin(login);
    }
}
