package application.controller;

import application.database.DBEngine;
import application.models.Access;
import application.models.BlogEntry;
import application.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


class DataControllerTest{

    DBEngine dbEngine = new DBEngine();
    List<User> userListTest = dbEngine.listAllUsers();

    @Test
    void getAccessAllDataTest(){

        List<User> accessList = new ArrayList<>();
        accessList.add(userListTest.get(1));

        Assertions.assertEquals(accessList,DataController.getAccessAllData(userListTest, Access.ADMIN));
    }

    @Test
    void getUserAllDataTest() {

        Assertions.assertEquals(userListTest.get(0),
                DataController.getUserAllData("p_sanyi",userListTest));
    }

    @Test
    void getUserAllBlogsReturnNullTest(){

        Assertions.assertEquals(null, DataController.getUserAllBlogs
                ("arany_jános",userListTest));
    }

    @Test
    void getUserAllBlogsTest(){

        Assertions.assertEquals(userListTest.get(3).getBlogsList(),
                DataController.getUserAllBlogs("júlia",userListTest));
    }

    @Test
    void getUserAllEntryTest(){

        List<BlogEntry> blogEntryList = new ArrayList<>();
        blogEntryList.add(userListTest.get(3).getBlogsList().get(0).getEntryList().get(0));
        blogEntryList.add(userListTest.get(3).getBlogsList().get(1).getEntryList().get(0));

        Assertions.assertEquals(blogEntryList,
                DataController.getUserAllEntry("júlia",userListTest));
    }

    @Test
    void getUserAllEntryNullTest(){

        Assertions.assertEquals(new ArrayList<>(),DataController.getUserAllEntry("arany_jános",userListTest));
    }

    @Test
    void getBlogAllEntryTest(){

        Assertions.assertEquals(userListTest.get(0).getBlogsList().get(0).getEntryList(),
                DataController.getBlogAllEntry(1,userListTest));
    }

    @Test
    void getAllCommentOfEntryTest(){

        Assertions.assertEquals(userListTest.get(3).getBlogsList().get(0).getEntryList().get(0).getBlogCommentList(),
                DataController.getAllCommentOfEntry(1,userListTest));
    }

    @Test
    void getAllCommentOfEntryNullTest(){

        Assertions.assertEquals(null,
                DataController.getAllCommentOfEntry(6,userListTest));
    }


}