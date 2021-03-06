package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.manager.ConfigurationManager;
import by.bsu.melnik.hospital.manager.MessageManager;
import by.bsu.melnik.hospital.dao.DrugDAO;
import by.bsu.melnik.hospital.dao.mysql.MySQLDrugDAO;
import by.bsu.melnik.hospital.dao.mysql.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class DeleteDrugCommand implements ActionCommand {

    private static UserDAO userDAO = new MySQLUserDAO();
    private static DrugDAO drugDAO = new MySQLDrugDAO();
    private static final String ID = "id";

    @Override
    public String execute(HttpServletRequest request) {

        // Извлечение ID из запроса
        int id = Integer.parseInt(request.getParameter(ID));

        // Попытка удаления лекарства
        drugDAO.delete(id);

        // Обновление списка пользователей
        request.getSession().setAttribute("dischargedPatients", userDAO.findAllUsersByStatus(0));
        request.getSession().setAttribute("patients", userDAO.findAllUsersByStatus(1));
        request.getSession().setAttribute("nurses", userDAO.findAllUsersByStatus(2));
        request.getSession().setAttribute("doctors", userDAO.findAllUsersByStatus(3));
        // Сообщение об удалении пользователя
        request.setAttribute("toastContent", MessageManager.getProperty("message.drugdeleted"));
        // Перенаправление на страницу админа
        return ConfigurationManager.getProperty("path.page.admin");
    }
}
