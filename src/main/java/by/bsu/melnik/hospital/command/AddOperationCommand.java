package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.ConfigurationManager;
import by.bsu.melnik.hospital.MessageManager;
import by.bsu.melnik.hospital.dao.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilyah on 17.12.2016.
 */
public class AddOperationCommand implements ActionCommand {

    private static final String ID_USER = "iduser";
    private static final String OPERATION_NAME = "operationName";
    private static final String OPERATION_DESC = "operationDesc";
    private static final String OPERATION_DATE = "operationDate";

    private static OperationDAO operationDAO = new MySQLOperationDAO();
    private static UserDAO userDAO = new MySQLUserDAO();

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.admin");

        // Извлечение из запроса параметров для добавления
        String operationName = request.getParameter(OPERATION_NAME);
        String operationDesc = request.getParameter(OPERATION_DESC);

        if (request.getParameter(ID_USER) == null ||
                operationName == null || operationName.equals("") ||
                operationDesc == null || operationDesc.equals("")) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.procedurenotadded"));
            return page;
        }

        int iduser = Integer.parseInt(request.getParameter(ID_USER));

        String operationDate = request.getParameter(OPERATION_DATE);
//
//        // Попытка получить значение даты
//        Date operationDate = null;
//        String startDateStr = request.getParameter(PROCEDURE_START_DATE);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            operationDate = sdf.parse(startDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            request.setAttribute("toastContent", MessageManager.getProperty("message.wrongdate"));
//            return page;
//        }

        // Попытка добавления лекарства
        if (operationDAO.addOperation(operationName, operationDesc, operationDate, iduser)) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.operationadded"));
            // Обновление списка пользователей
            request.getSession().setAttribute("dischargedPatients", userDAO.findAllUsersByStatus(0));
            request.getSession().setAttribute("patients", userDAO.findAllUsersByStatus(1));
            request.getSession().setAttribute("nurses", userDAO.findAllUsersByStatus(2));
            request.getSession().setAttribute("doctors", userDAO.findAllUsersByStatus(3));

        } else {
            request.setAttribute("toastContent", MessageManager.getProperty("message.operationnotadded"));
        }

        // Перенаправление на страницу админа
        return page;
    }
}
