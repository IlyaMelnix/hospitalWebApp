package by.bsu.melnik.hospital.dao;

/**
 *  A simple interface for UserDAO
 *  that encapsulates all the data access by the web application.
 */

import by.bsu.melnik.hospital.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAllUsersByStatus(int idstatus);
    public User findUserByID(int iduser);
    public User searchUserByUsernameAndPassword(String username, String password);
    public User createNewUser(String username, String password, String name, String surname, String patronymic, String diagnosis, int status);
    public User update (User user, int id);
    public void dischargeUser(int iduser);
    public void delete (int iduser);

}
