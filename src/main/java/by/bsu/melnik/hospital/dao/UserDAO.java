package by.bsu.melnik.hospital.dao;

/**
 *  A simple interface for UserDAO
 *  that encapsulates all the data access by the web application.
 */

import by.bsu.melnik.hospital.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAllUsers();
    public List<User> searchUserByKeyword(String keyword);
    public List<User> findAllByStatus(int status);

    public void insert (User user);
    public void update (User user);
    public void delete (int iduser);

}
