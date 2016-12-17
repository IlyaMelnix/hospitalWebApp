package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.ConfigurationManager;
import by.bsu.melnik.hospital.MessageManager;
import by.bsu.melnik.hospital.dao.*;

import javax.servlet.http.HttpServletRequest;

public class DeleteOperationCommand implements ActionCommand {

    private static UserDAO userDAO = new MySQLUserDAO();
    private static OperationDAO operationDAO = new MySQLOperationDAO();

    private static final String ID = "id";

    @Override
    public String execute(HttpServletRequest request) {

        // Извлечение ID из запроса
        int id = Integer.parseInt(request.getParameter(ID));

        // Попытка удаления лекарства
        operationDAO.delete(id);

        // Обновление списка пользователей
        request.getSession().setAttribute("users", userDAO.findAllUsers());

        // Сообщение об удалении пользователя
        request.setAttribute("toastContent", MessageManager.getProperty("message.operationdeleted"));
        // Перенаправление на страницу админа
        return ConfigurationManager.getProperty("path.page.admin");
    }
}
