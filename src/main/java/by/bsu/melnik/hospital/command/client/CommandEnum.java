package by.bsu.melnik.hospital.command.client;

import by.bsu.melnik.hospital.command.ActionCommand;
import by.bsu.melnik.hospital.command.CreateNewUserCommand;
import by.bsu.melnik.hospital.command.LoginCommand;
import by.bsu.melnik.hospital.command.LogoutCommand;
import by.bsu.melnik.hospital.command.DeleteUserCommand;

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
    },
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
