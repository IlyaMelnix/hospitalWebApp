package by.bsu.melnik.hospital.command;

import by.bsu.melnik.hospital.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {

        // В случае ошибки или прямого обращения к контроллеру
        // переадресация на страницу ввода логина
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
