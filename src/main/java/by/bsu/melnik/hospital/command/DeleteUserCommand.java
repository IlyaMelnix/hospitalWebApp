    package by.bsu.melnik.hospital.command;

    import by.bsu.melnik.hospital.ConfigurationManager;
    import by.bsu.melnik.hospital.dao.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

    public class DeleteUserCommand implements ActionCommand{

    private static UserDAO userDAO = new MySQLUserDAO();

    private static final String ID = "id";

    @Override
    public String execute(HttpServletRequest request) {

        // Извлечение ID из запроса
        int id = Integer.parseInt(request.getParameter(ID));

        // Попытка удаления пользователя
        userDAO.delete(id);

        // Обновление списка пользователей
        request.getSession().setAttribute("users", userDAO.findAllUsers());

        // Перенаправление на страницу админа
        return ConfigurationManager.getProperty("path.page.admin");
    }

}
