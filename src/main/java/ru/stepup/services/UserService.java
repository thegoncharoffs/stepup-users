package ru.stepup.services;

import org.springframework.stereotype.Component;
import ru.stepup.entities.User;
import ru.stepup.daos.UserDao;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public Optional<User> getUserById(Long id) {
        return userDao.getEntityById(id);
    }

    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    public boolean deleteUser(Long id) {
        return userDao.delete(id);
    }

    public boolean createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userDao=" + userDao +
                '}';
    }
}
