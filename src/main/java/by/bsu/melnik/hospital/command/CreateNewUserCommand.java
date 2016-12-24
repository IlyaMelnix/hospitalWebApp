package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.manager.ConfigurationManager;
import by.bsu.melnik.hospital.manager.MessageManager;
import by.bsu.melnik.hospital.dao.mysql.MySQLUserDAO;
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

        User user = new User();

        boolean wrongField = false;

        // Проверка и сохранение полей в user при возможности
        if(username != null     && !username.equals(""))    { user.setUsername(username); }                 else { wrongField = true; }
        if(password != null     && !password.equals(""))    { user.setPassword(password); }                 else { wrongField = true; }
        if(name != null         && !name.equals(""))        { user.setName(name); }                         else { wrongField = true; }
        if(surname != null      && !surname.equals(""))     { user.setSurname(surname); }                   else { wrongField = true; }
        if(patronymic != null   && !patronymic.equals(""))  { user.setPatronymic(patronymic); }             else { wrongField = true; }
        if(diagnosis != null    && !diagnosis.equals(""))   { user.setDiagnosis(diagnosis); }               else { wrongField = true; }
        if(statusStr != null    && !statusStr.equals(""))   { user.setIdstatus(Integer.parseInt(statusStr));}else{ wrongField = true; }

        // Передача user в запрос, чтобы сохранить уже заполненные поля
        request.setAttribute("user",user);

        // Проверка полей
        if (wrongField) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.wrondfield"));
            return page;
        }

        // Проверка совпадения паролей
        if (!user.getPassword().equals(passwordCheck)){
            request.setAttribute("toastContent", MessageManager.getProperty("message.wrongpassword"));
            request.setAttribute("user", user);
            return page;
        }

        // Попытка создания пользователя
        user = userDAO.createNewUser(username, password, name, surname, patronymic, diagnosis, Integer.parseInt(statusStr));

        // Проверка создания пользователя
        if (user != null){


            // Сообщение о создании пользователя
            request.setAttribute("toastContent", MessageManager.getProperty("message.usercreated"));

            // Обновление списка пользователей
            request.getSession().setAttribute("dischargedPatients", userDAO.findAllUsersByStatus(0));
            request.getSession().setAttribute("patients", userDAO.findAllUsersByStatus(1));
            request.getSession().setAttribute("nurses", userDAO.findAllUsersByStatus(2));
            request.getSession().setAttribute("doctors", userDAO.findAllUsersByStatus(3));
            page = ConfigurationManager.getProperty("path.page.admin");

        } else {

            request.setAttribute("toastContent", MessageManager.getProperty("message.userisnotcreated"));

        }
        return page;
    }
}
