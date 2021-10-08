import application.controller.UserController;
import application.database.DBEngine;
import application.models.User;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DBEngine dbEngine = new DBEngine();
        List<User> userList = dbEngine.listAllUsers();

        UserController.editUserData(userList.get(1),
                userList.get(4),
                "blablabla","kr","k.frigyes@gmail.com");

    }
}
