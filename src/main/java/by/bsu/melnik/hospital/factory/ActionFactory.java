package by.bsu.melnik.hospital.factory;


import by.bsu.melnik.hospital.command.ActionCommand;
import by.bsu.melnik.hospital.command.EmptyCommand;
import by.bsu.melnik.hospital.command.client.CommandEnum;
import by.bsu.melnik.hospital.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current = new EmptyCommand();

        // Извлечение имени команды из запроса
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()){

            // Если команда не задана в текущем запросе
            return current;
        }

        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException ex) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
