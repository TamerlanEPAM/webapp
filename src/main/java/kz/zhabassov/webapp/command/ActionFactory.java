package kz.zhabassov.webapp.command;

import kz.zhabassov.webapp.command.impl.*;

public class ActionFactory {
    public static ActionCommand defineCommand(String action){

        ActionCommand current = null;

        if(action==null || action.isEmpty()){
            return new EmptyCommand();
        }

        switch (action.toUpperCase()){
                case "REGISTRATION":
                    current = new RegisterCommand();
                    break;
                case "LOGOUT":
                    current = new LogoutCommand();
                    break;
                case "LOGIN":
                    current = new LoginCommand();
                    break;
                case "CREATE_TEST":
                    current = new CreateTestCommand();
                    break;
                case "PUBLISH_TEST":
                    current = new PublishTestCommand();
                    break;
                case "GET_TESTS":
                    current = new GetTestsCommand();
                    break;
                case "TAKE":
                    current = new TakeTestCommand();
                    break;
                case "PASS_TEST":
                    current = new PassTestCommand();
                    break;
                case "UPDATE_PROFILE":
                    current = new UpdateProfileCommand();
                    break;
                case "DELETE_PROFILE":
                    current = new DeleteProfileCommand();
                    break;
                case "CHANGE_LANGUAGE":
                    current = new ChangeLanguageCommand();
                    break;
                default:
                    throw new IllegalArgumentException("This command doesn't exist");
        }

        return current;
    }
}
