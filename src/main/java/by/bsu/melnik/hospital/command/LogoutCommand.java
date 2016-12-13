package by.bsu.melnik.hospital.command;
import by.bsu.melnik.hospital.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.index");

        // Уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}
