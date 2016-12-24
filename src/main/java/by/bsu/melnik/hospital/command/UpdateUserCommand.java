package by.bsu.melnik.hospital.command;


import by.bsu.melnik.hospital.manager.ConfigurationManager;
import by.bsu.melnik.hospital.manager.MessageManager;
import by.bsu.melnik.hospital.dao.mysql.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;
import by.bsu.melnik.hospital.model.User;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements ActionCommand{

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_CHECK = "passwordCheck";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String DIAGNOSIS = "diagnosis";
    private static final String STATUS = "status";

    private static final String ID = "id";

    private static UserDAO userDAO = new MySQLUserDAO();

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.create-new-user");

        // Проверка на получение ID
        if(request.getParameter(ID) == null) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.idnull"));
            return "/controller?command=login";
        }

        int id = Integer.parseInt(request.getParameter(ID));
        User user = userDAO.findUserByID(id);

        if(user == null){
            request.setAttribute("toastContent", MessageManager.getProperty("message.idnull"));
            return "/controller?command=login";
        }

        // Проверка на редактирование самого себя
        User currentUser = (User)(request.getSession().getAttribute("currentUser"));

        if(id == currentUser.getIduser()){
            request.setAttribute("type", "Редактирование вашего профиля");
        }
        else {
            request.setAttribute("type", "Редактирование пользователя");
        }

        if (request.getParameter(USERNAME) == null || request.getParameter(USERNAME).equals("")) {
            request.setAttribute("user", user);
            return page;
        }

        // Извлечение из запроса параметров для регистрации
        String username     = request.getParameter(USERNAME);
        String password     = request.getParameter(PASSWORD);
        String passwordCheck= request.getParameter(PASSWORD_CHECK);
        String name         = request.getParameter(NAME);
        String surname      = request.getParameter(SURNAME);
        String patronymic   = request.getParameter(PATRONYMIC);
        String diagnosis    = request.getParameter(DIAGNOSIS);
        String statusStr    = request.getParameter(STATUS);

        // Обновление полей user при возможности
        if(username != null     && !username.equals(""))    { user.setUsername(username); }
        if(password != null     && !password.equals(""))    { user.setPassword(password); }
        if(name != null         && !name.equals(""))        { user.setName(name); }
        if(surname != null      && !surname.equals(""))     { user.setSurname(surname); }
        if(patronymic != null   && !patronymic.equals(""))  { user.setPatronymic(patronymic); }
        if(diagnosis != null    && !diagnosis.equals(""))   { user.setDiagnosis(diagnosis); }
        if(statusStr != null    && !statusStr.equals(""))   { user.setIdstatus(Integer.parseInt(statusStr)); }

        if (!user.getPassword().equals(passwordCheck)){
            request.setAttribute("toastContent", MessageManager.getProperty("message.wrongpassword"));
            request.setAttribute("user", user);
            return page;
        }

        // Обновление пользователя
        user = userDAO.update(user, id);
        if(id == currentUser.getIduser()) {
            request.getSession().setAttribute("currentUser",user);
        }

        request.setAttribute("toastContent", MessageManager.getProperty("message.updatesuccess"));
        return "/controller?command=login";
    }
}
