package application.controller;

import application.database.DBEngine;
import application.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static application.models.Access.USER;

class UserControllerTest {

    DBEngine dbEngine = new DBEngine();
    List<User> userListTest = dbEngine.listAllUsers();

    @Test
    void writeCommentTest() {

        List<BlogComment> commentList = UserController.writeComment(userListTest.get(1),
                userListTest.get(0).getBlogsList().get(0).getEntryList().get(0),
                "Hát ezt nyem hiszjem el!");

        Assertions.assertEquals(1, commentList.size());
    }

    @Test
    void entryDeleteTest() {

        List<BlogEntry> actualList = UserController.entryDelete(userListTest.get(1),
                userListTest.get(0).getBlogsList().get(0),
                userListTest.get(0).getBlogsList().get(0).getEntryList().get(0));

        Assertions.assertEquals(0, actualList.size());
    }

//   @Test
//    void entryEditingTest() {
//
//        BlogEntry expectedEntry = new BlogEntry(2,"f*ck off!", SHARED,1);
//        expectedEntry.setBlogCommentList(new ArrayList<>());
//
//        Assertions.assertEquals(expectedEntry, UserController.entryEditing(userListTest.get(1),
//                userListTest.get(0).getBlogsList().get(0).getEntryList().get(0),
//                "f*ck off!"));
//    }

    @Test
    void entryReadingNotSharedTest(){

        Assertions.assertNull(UserController.entryReading(userListTest.get(5).getBlogsList().get(0)
                .getEntryList().get(0)));
    }

    @Test
    void editUserDataAdminAccessTest(){

        User exceptedUser = new User(5,"blablabla", "kr", "k.frigyes@gmail.com", USER);

        Assertions.assertEquals(exceptedUser,UserController.editUserData(userListTest.get(1),
                userListTest.get(4),
                "blablabla","kr","k.frigyes@gmail.com"));
    }

    @Test
    void getUserDataAccessTest(){

        Assertions.assertNotNull(UserController.getUserData(userListTest.get(1),userListTest.get(3),"andersen17"));
    }
}
