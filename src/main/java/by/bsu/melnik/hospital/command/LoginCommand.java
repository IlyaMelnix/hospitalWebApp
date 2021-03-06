package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.manager.ConfigurationManager;
import by.bsu.melnik.hospital.manager.MessageManager;
import by.bsu.melnik.hospital.dao.mysql.MySQLUserDAO;
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
        User currentUser = null;

        // Если пользователь ещё не в системе
        if (request.getSession().getAttribute("currentUser") == null){

            // Извлечение из запроса логина и пароля
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);

            // Попытка создания текущего пользователя
            currentUser = userDAO.searchUserByUsernameAndPassword(login,password);
            if (currentUser!=null) {
                request.getSession().setAttribute("currentUser",currentUser);
            }
            else {

                request.setAttribute("toastContent", MessageManager.getProperty("message.loginerror"));
                return ConfigurationManager.getProperty("path.page.login");
            }
        }
        else {
            currentUser = (User) (request.getSession().getAttribute("currentUser"));
        }

        // Определение пути
        if (currentUser.getIdstatus() < 2)
            page = ConfigurationManager.getProperty("path.page.main");
        else {

            // Получить списки пользователей
            request.getSession().setAttribute("dischargedPatients", userDAO.findAllUsersByStatus(0));
            request.getSession().setAttribute("patients", userDAO.findAllUsersByStatus(1));
            request.getSession().setAttribute("nurses", userDAO.findAllUsersByStatus(2));
            request.getSession().setAttribute("doctors", userDAO.findAllUsersByStatus(3));
            page = ConfigurationManager.getProperty("path.page.admin");
        }

        return page;
    }
}
