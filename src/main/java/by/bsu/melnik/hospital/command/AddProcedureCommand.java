package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.manager.ConfigurationManager;
import by.bsu.melnik.hospital.manager.MessageManager;
import by.bsu.melnik.hospital.dao.mysql.MySQLProcedureDAO;
import by.bsu.melnik.hospital.dao.mysql.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.ProcedureDAO;
import by.bsu.melnik.hospital.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class AddProcedureCommand implements ActionCommand {

    private static final String ID_USER = "iduser";
    private static final String PROCEDURE_NAME = "procedureName";
    private static final String PROCEDURE_DESC = "procedureDesc";
    private static final String PROCEDURE_DURATION = "procedureDuration";
    private static final String PROCEDURE_START_DATE = "procedureStartDate";
    private static final String PROCEDURE_HOW_MANY_TIMES = "procedureHowManyTimes";

    private static ProcedureDAO procedureDAO = new MySQLProcedureDAO();
    private static UserDAO userDAO = new MySQLUserDAO();

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.admin");

        // Извлечение из запроса параметров для добавления
        String procedureName = request.getParameter(PROCEDURE_NAME);
        String procedureDesc = request.getParameter(PROCEDURE_DESC);

        if (request.getParameter(ID_USER) == null ||
                procedureName == null || procedureName.equals("") ||
                procedureDesc == null || procedureDesc.equals("")) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.procedurenotadded"));
            return page;
        }

        int procedureDuration = Integer.parseInt(request.getParameter(PROCEDURE_DURATION));
        int procedureHowManyTimes = Integer.parseInt(request.getParameter(PROCEDURE_HOW_MANY_TIMES));
        int iduser = Integer.parseInt(request.getParameter(ID_USER));

        String procedureStartDate = request.getParameter(PROCEDURE_START_DATE);
//
//        // Попытка получить значение даты
//        Date procedureStartDate = null;
//        String startDateStr = request.getParameter(PROCEDURE_START_DATE);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            procedureStartDate = sdf.parse(startDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            request.setAttribute("toastContent", MessageManager.getProperty("message.wrongdate"));
//            return page;
//        }

        // Попытка добавления лекарства
        if (procedureDAO.addProcedure(procedureName, procedureDesc, procedureDuration, procedureStartDate, procedureHowManyTimes, iduser)) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.procedureadded"));
            // Обновление списка пользователей
            request.getSession().setAttribute("dischargedPatients", userDAO.findAllUsersByStatus(0));
            request.getSession().setAttribute("patients", userDAO.findAllUsersByStatus(1));
            request.getSession().setAttribute("nurses", userDAO.findAllUsersByStatus(2));
            request.getSession().setAttribute("doctors", userDAO.findAllUsersByStatus(3));

        } else {
            request.setAttribute("toastContent", MessageManager.getProperty("message.procedurenotadded"));
        }

        // Перенаправление на страницу админа
        return page;
    }
}
