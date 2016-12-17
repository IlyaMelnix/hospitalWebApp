package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.ConfigurationManager;
import by.bsu.melnik.hospital.MessageManager;
import by.bsu.melnik.hospital.dao.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;
import by.bsu.melnik.hospital.model.User;

import javax.servlet.http.HttpServletRequest;

public class CreateNewUserCommand implements ActionCommand {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_CHECK = "passwordCheck";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String DIAGNOSIS = "diagnosis";
    private static final String STATUS = "status";

    private static final String ID = "id";
//
//    private static final int FINISH = 0;
//    private static final int CREATE = 1;
//    private static final int UPDATE = 2;
//    private static final int UPDATE_CURRENT = 3;

    private static UserDAO userDAO = new MySQLUserDAO();

    @Override
    public String execute(HttpServletRequest request) {


        request.setAttribute("type", "Регистрация нового пользователя");

        String page = ConfigurationManager.getProperty("path.page.create-new-user");

        // Извлечение из запроса параметров для регистрации
        String username     = request.getParameter(USERNAME);
        String password     = request.getParameter(PASSWORD);
        String passwordCheck= request.getParameter(PASSWORD_CHECK);
        String name         = request.getParameter(NAME);
        String surname      = request.getParameter(SURNAME);
        String patronymic   = request.getParameter(PATRONYMIC);
        String diagnosis    = request.getParameter(DIAGNOSIS);
        String statusStr    = request.getParameter(STATUS);


        // Проверка полей
        // TODO: Проверить поля + username на уникальность + совпадение паролей
        // TODO: Добавить сохранение содержимого полей
        if (username == null || username.equals("") ||
                password == null || password.equals("") )
            return page;

        int status = Integer.parseInt(statusStr);

        // Попытка создания пользователя
        User NewUser = userDAO.createNewUser(username, password, name, surname, patronymic, diagnosis, status);

        // Проверка создания пользователя
        if (NewUser != null){

            // Сообщение о создании пользователя
            request.setAttribute("toastContent", MessageManager.getProperty("message.usercreated"));

            // Обновление списка пользователей
            request.getSession().setAttribute("users", userDAO.findAllUsers());
            page = ConfigurationManager.getProperty("path.page.admin");

        } else {

            request.setAttribute("toastContent", MessageManager.getProperty("message.userisnotcreated"));

        }
        return page;
    }
}
