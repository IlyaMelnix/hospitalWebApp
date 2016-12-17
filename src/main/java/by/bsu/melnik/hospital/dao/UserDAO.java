package by.bsu.melnik.hospital.dao;

/**
 *  A simple interface for UserDAO
 *  that encapsulates all the data access by the web application.
 */

import by.bsu.melnik.hospital.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAllUsers();
    public User searchUserByUsernameAndPassword(String username, String password);
    public User createNewUser(String username, String password, String name, String surname, String patronymic, String diagnosis, int status);
    public void dischargeUser(int iduser);
    public void update (User user);
    public void delete (int iduser);

}
