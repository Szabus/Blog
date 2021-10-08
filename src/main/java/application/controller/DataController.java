package application.controller;

import application.models.*;

import java.util.ArrayList;
import java.util.List;

public class DataController {

    public static List<User> getAccessAllData(List<User> userList, Access access) {

        List<User> accessList = new ArrayList<>();

        for (User user : userList) {
            if (access.equals(user.getAccess())) {
                accessList.add(user);
            }
        }
        return accessList;
    }

    public static User getUserAllData(String userName, List<User> userList) {

        for (User user : userList) {
            if (userName.equals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

    public static List<Blog> getUserAllBlogs(String userName, List<User> userList) {

        for (User user : userList) {
            if (userName.equals(user.getUserName())) {
                if (user.getBlogsList().size() != 0 && user.getBlogsList() != null) {
                    System.out.println(user.getBlogsList());
                    return user.getBlogsList();
                } else
                    return null;
            }
        }
        return null;
    }

    public static List<BlogEntry> getUserAllEntry(String userName, List<User> userList) {

        List<BlogEntry> blogEntryList = new ArrayList<>();

        for (User user : userList) {
            if (userName.equals(user.getUserName())) {
                for (Blog blog : user.getBlogsList()) {
                    if (user.getBlogsList().size() != 0 && user.getBlogsList() != null) {
                        for (BlogEntry blogEntry : blog.getEntryList()) {
                            blogEntryList.add(blogEntry);
                        }
                    } else {
                        return null;
                    }
                }
            }
        }
        return blogEntryList;
    }

    public static List<BlogEntry> getBlogAllEntry(int blogId, List<User> userList) {

        for (User user : userList) {
            for (Blog blog : user.getBlogsList()) {
                if (blogId == blog.getBlogId()) {
                    return blog.getEntryList();
                }
            }
        }
        return null;
    }

    public static List<BlogComment> getAllCommentOfEntry(int entryId, List<User> userList) {

        for (User user : userList) {
            if (user.getBlogsList().size() != 0 && user.getBlogsList() != null) {
                for (Blog blog : user.getBlogsList()) {
                    for (BlogEntry entry : blog.getEntryList()) {
                        if (entry.getId() == entryId) {
                            return entry.getBlogCommentList();
                        }
                    }
                }
            }
        }
        return null;
    }
}
