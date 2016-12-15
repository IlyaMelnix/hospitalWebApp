package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.ConfigurationManager;
import by.bsu.melnik.hospital.MessageManager;
import by.bsu.melnik.hospital.dao.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;
import by.bsu.melnik.hospital.model.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static UserDAO userDAO = new MySQLUserDAO();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        // Извлечение из запроса логина и пароля
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        // Попытка создания текущего пользователя
        User CurrentUser = userDAO.searchUserByUsernameAndPassword(login,password);
        // Проверка логина и пароля
        if (CurrentUser!=null){

            request.setAttribute("user",login);
            request.getSession().setAttribute("currentUser",CurrentUser);

            // Определение пути
            if (CurrentUser.getIdstatus() < 2)
                page = ConfigurationManager.getProperty("path.page.main");
            else {
                request.getSession().setAttribute("users", userDAO.findAllUsers());
                page = ConfigurationManager.getProperty("path.page.admin");
            }
        } else {

            request.setAttribute("errorLoginOrPassMessage", MessageManager.getProperty("message.loginerror"));

            //page = "/WEB-INF/jsp/error.jsp";
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
