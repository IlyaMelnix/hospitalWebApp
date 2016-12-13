package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.logic.LoginLogic;
import by.bsu.melnik.hospital.resource.ConfigurationManager;
import by.bsu.melnik.hospital.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
public class LoginCommand implements ActionCommand {
//    private static final String ID = "id";
//    private static final String FIO = "fio";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
//    private static final String IS_ADMIN = "isAdmin";
//    private static final String IS_SIGNED = "isSignedIn";

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        // Извлечение из запроса логина и пароля
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        // Проверка логина и пароля
        if (LoginLogic.checkLogin(login,password)){

            request.setAttribute("user",login);
            // Определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");

        } else {

            request.setAttribute("errorLoginOrPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
