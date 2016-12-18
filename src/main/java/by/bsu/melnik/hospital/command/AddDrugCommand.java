package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.ConfigurationManager;
import by.bsu.melnik.hospital.MessageManager;
import by.bsu.melnik.hospital.dao.DrugDAO;
import by.bsu.melnik.hospital.dao.MySQLDrugDAO;
import by.bsu.melnik.hospital.dao.MySQLUserDAO;
import by.bsu.melnik.hospital.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

public class AddDrugCommand implements ActionCommand{

    private static final String DRUG_NAME = "drugname";
    private static final String DRUG_DESC = "drugdesc";
    private static final String DRUG_DOSING = "drugdosing";
    private static final String ID_USER = "iduser";

    private static DrugDAO drugDAO = new MySQLDrugDAO();
    private static UserDAO userDAO = new MySQLUserDAO();

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.admin");
        // Извлечение из запроса параметров для добавления
        String drugname     = request.getParameter(DRUG_NAME);
        String drugdesc     = request.getParameter(DRUG_DESC);
        String drugdosing   = request.getParameter(DRUG_DOSING);

        if (request.getParameter(ID_USER) == null ||
                drugname == null || drugname.equals("") ||
                drugdesc == null || drugdesc.equals("") ||
                drugdosing == null || drugdosing.equals("")) {
            request.setAttribute("toastContent", MessageManager.getProperty("message.drugnotadded"));
            return page;
        }

        int iduser = Integer.parseInt(request.getParameter(ID_USER));

        // Попытка добавления лекарства
        if (drugDAO.AddDrug(drugname, drugdesc, drugdosing, iduser)){
            request.setAttribute("toastContent", MessageManager.getProperty("message.drugadded"));
            // Обновление списка пользователей
            request.getSession().setAttribute("dischargedPatients", userDAO.findAllUsersByStatus(0));
            request.getSession().setAttribute("patients", userDAO.findAllUsersByStatus(1));
            request.getSession().setAttribute("nurses", userDAO.findAllUsersByStatus(2));
            request.getSession().setAttribute("doctors", userDAO.findAllUsersByStatus(3));

        }
        else{
            request.setAttribute("toastContent", MessageManager.getProperty("message.drugnotadded"));
        }

        // Перенаправление на страницу админа
        return page;
    }
}
