package by.bsu.melnik.hospital.command.client;

import by.bsu.melnik.hospital.command.ActionCommand;
import by.bsu.melnik.hospital.command.CreateNewUserCommand;
import by.bsu.melnik.hospital.command.LoginCommand;
import by.bsu.melnik.hospital.command.LogoutCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CREATENEWUSER {
        {
            this.command = new CreateNewUserCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
