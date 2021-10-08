package application.controller;

import application.models.*;

import java.util.List;

public class UserController {

    public static List<BlogComment> writeComment(User user, BlogEntry entry, String comment) {
        BlogComment newComment;

        if (user.getBlogsList().size() != 0 && user.getBlogsList() != null) {
            for (Blog blog : user.getBlogsList()) {
                if (blog.getEntryList().size() != 0 && user.getBlogsList() != null) {
                    for (BlogEntry ent : blog.getEntryList()) {
                        if (entry.getId() == ent.getId() && ent.getStatus().equals(EntryStatus.SHARED)) {
                            newComment = new BlogComment(comment);
                            ent.getBlogCommentList().add(newComment);
                            return ent.getBlogCommentList();
                        }
                    }
                }
            }
        } else if (entry.getStatus().equals(EntryStatus.SHARED)) {
            newComment = new BlogComment(comment);
            entry.getBlogCommentList().add(newComment);
            return entry.getBlogCommentList();
        }
        return null;
    }


    public static List<BlogEntry> entryDelete(User user, Blog blog, BlogEntry entry) {

        if (user.getBlogsList().size() != 0 && user.getBlogsList() != null) {
            for (Blog b : user.getBlogsList()) {
                if (b.getEntryList().size() != 0 && user.getBlogsList() != null) {
                    for (BlogEntry e : b.getEntryList()) {
                        if (e.equals(entry) && e.getStatus().equals(EntryStatus.SHARED)) {
                            b.getEntryList().remove(entry);
                            return b.getEntryList();
                        }
                    }
                }
            }
        } else if (user.getAccess().equals(Access.MODERATOR) || user.getAccess().equals(Access.ADMIN)) {
            blog.getEntryList().remove(entry);
            return blog.getEntryList();
        }
        return null;
    }

    public static BlogEntry entryEditing(User user, BlogEntry entry, String editedEntry) {

        if (user.getBlogsList().size() != 0 && user.getBlogsList() != null) {
            for (Blog blog : user.getBlogsList()) {
                if (blog.getEntryList().size() != 0 && user.getBlogsList() != null) {
                    for (BlogEntry e : blog.getEntryList()) {
                        if (e.equals(entry))
                            e.setEntry(editedEntry);
                        return entry;
                    }
                }
            }
        } else if (user.getAccess().equals(Access.ADMIN) || user.getAccess().equals(Access.MODERATOR)) {
            entry.setEntry(editedEntry);
            return entry;
        }
        return null;
    }

    public static String entryReading(BlogEntry entry) {
        if (entry.getStatus().equals(EntryStatus.SHARED)) {
            return entry.getEntry();
        }
        return null;
    }

    public static User editUserData(User editingUser, User user, String name,
                                    String password, String eMail) {

        if (user.getUserName().equals(editingUser.getUserName()) && user.getPassword().equals(password)) {
            user.setUserName(name);
            user.setPassword(password);
            user.setEMail(eMail);
        } else if (editingUser.getAccess().equals(Access.ADMIN)) {
            user.setUserName(name);
            user.setPassword(password);
            user.setEMail(eMail);
        }
        return user;
    }

    public static User getUserData(User reader, User user, String password) {

        if (user.getUserName().equals(reader.getUserName()) && user.getPassword().equals(password)) {
            return user;
        } else if (reader.getAccess().equals(Access.ADMIN)) {
            return user;
        }
        return null;
    }
}



