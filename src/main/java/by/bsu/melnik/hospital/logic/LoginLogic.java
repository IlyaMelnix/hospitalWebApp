package by.bsu.melnik.hospital.logic;

import by.bsu.melnik.hospital.dao.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;
import by.bsu.melnik.hospital.model.User;

public class LoginLogic {

    private static UserDAO userDAO = new MySQLUserDAO();

    public static User checkLogin(String enterLogin, String enterPass){
        // Если ползователь был создан, возвращаем его, в противном случае вернёт false
        User CurrentUser = userDAO.searchUserByUsernameAndPassword(enterLogin,enterPass);
        if (CurrentUser != null) return CurrentUser;
        else return null;
    }
}
