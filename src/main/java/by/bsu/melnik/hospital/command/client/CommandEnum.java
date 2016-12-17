package by.bsu.melnik.hospital.command.client;

import by.bsu.melnik.hospital.command.*;

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
    },
    ADDDRUG{
        {
            this.command = new AddDrugCommand();
        }
    },
    DELETEDRUG{
        {
            this.command = new DeleteDrugCommand();
        }
    },
    ADDPROCEDURE{
        {
            this.command = new AddProcedureCommand();
        }
    },
    DELETEPROCEDURE{
        {
            this.command = new DeleteProcedureCommand();
        }
    },
    ADDOPERATION{
        {
            this.command = new AddOperationCommand();
        }
    },
    DELETEOPERATION{
        {
            this.command = new DeleteOperationCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
